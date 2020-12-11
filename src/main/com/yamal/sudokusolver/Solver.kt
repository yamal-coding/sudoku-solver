package com.yamal.sudokusolver

import com.yamal.sudokusolver.common.FULL_SET_OF_CELLS
import com.yamal.sudokusolver.common.calculateQuadrantByRowAndColumn
import com.yamal.sudokusolver.common.getInitialRowAndColumnOfQuadrant
import com.yamal.sudokusolver.common.processActionOn9x9Board
import com.yamal.sudokusolver.model.Board
import com.yamal.sudokusolver.model.CellValue
import com.yamal.sudokusolver.model.Point

class Solver(
    private val sudokuBoard: Board,
    private val useBruteForceOnly: Boolean = false
) {

    private val rows: MutableMap<Int, MutableSet<CellValue>> by lazy {
        mutableMapOfMutableSetsOfLengthNine<CellValue>()
    }

    private val columns: MutableMap<Int, MutableSet<CellValue>> by lazy {
        mutableMapOfMutableSetsOfLengthNine<CellValue>()
    }

    private val quadrants: MutableMap<Int, MutableSet<CellValue>> by lazy {
        mutableMapOfMutableSetsOfLengthNine<CellValue>()
    }

    private val emptyCellsByQuadrant: MutableMap<Int, MutableSet<Point>> by lazy {
        mutableMapOfMutableSetsOfLengthNine<Point>()
    }

    private fun <T> mutableMapOfMutableSetsOfLengthNine(): MutableMap<Int, MutableSet<T>> =
        mutableMapOf<Int, MutableSet<T>>().apply {
            for (i in 0..8) {
                this[i] = mutableSetOf()
            }
        }

    fun solve(): Board {
        if (useBruteForceOnly) {
            preloadExistingCellsAndReturnNumberOfFilledCells()
            goWithTheBruteForceBaby()
        } else {
            preloadExistingCellsAndReturnNumberOfFilledCells()
            calculateCandidates()
            if (candidates.isNotEmpty()) {
                reduceCandidates()
            }
        }

        return sudokuBoard
    }

    private fun preloadExistingCellsAndReturnNumberOfFilledCells(): Int {
        var filledCells = 0

        processActionOn9x9Board { row, column ->
            val cellValue = sudokuBoard[row, column]

            if (cellValue == CellValue.EMPTY) {
                emptyCellsByQuadrant[calculateQuadrantByRowAndColumn(row, column)]?.add(Point(row, column))
            } else {
                rows[row]?.add(cellValue)
                columns[column]?.add(cellValue)
                quadrants[calculateQuadrantByRowAndColumn(row, column)]?.add(cellValue)
                filledCells++
            }
        }

        return filledCells
    }

    private val candidates: MutableMap<Point, MutableSet<CellValue>> = mutableMapOf()

    private fun calculateCandidates() {
        for (quadrant in 0..8) {
            (FULL_SET_OF_CELLS - quadrants[quadrant]!!).forEach { potentialValue ->
                emptyCellsByQuadrant[quadrant]!!.forEach {
                    val (row, column) = it

                    if (isNotInRow(row, potentialValue) && isNotInColumn(column, potentialValue)) {
                        if (candidates[Point(row, column)] == null) {
                            candidates[Point(row, column)] = mutableSetOf(potentialValue)
                        } else {
                            candidates[Point(row, column)]?.add(potentialValue)
                        }
                    }
                }
            }
        }
    }

    private fun reduceCandidates() {
        var reduceVerticallyOrHorizontally: Boolean
        var reduceByConfirmedCells: Boolean
        var reduceIfSingleInRowColumnOrQuadrant: Boolean
        var reduceSingleCandidates: Boolean

        do {
            reduceVerticallyOrHorizontally = reduceByCandidatesThatAreOnlyVerticallyOrHorizontallyInTheirQuadrants()
            reduceByConfirmedCells = reduceByConfirmedCells()
            reduceIfSingleInRowColumnOrQuadrant = reduceIfSingleInRowColumnOrQuadrant()
            reduceSingleCandidates = reduceSingleCandidates()
        } while (reduceVerticallyOrHorizontally  || reduceByConfirmedCells || reduceIfSingleInRowColumnOrQuadrant || reduceSingleCandidates)
    }

    private fun reduceByConfirmedCells(): Boolean {
        // Detect cells in the same column or row which have the same candidates and their size is the same
        // as the number of occurrences of that type of cell in the same column or row. Then delete those candidates
        // in the other cells of that column or row that does't satisfy that premise.

        var reduced = false

        // Reduce by rows
        for (row in 0..8) {
            val columnsToOmit = mutableSetOf<Int>()

            for (column in 0..8) {
                if (columnsToOmit.contains(column))
                    continue
                candidates[Point(row, column)]?.let { cellCandidates ->
                    var i = 1
                    var identicalCells = 1
                    val columnsToReduce = mutableListOf<Int>()
                    while (column + i <= 8) {
                        if (!columnsToOmit.contains(column + i) && cellCandidates == candidates[Point(row, column + i)]) {
                            identicalCells++
                            columnsToOmit.add(column + i)
                            columnsToReduce.add(column + i)
                        }
                        i++
                    }
                    if (cellCandidates.size == identicalCells) {
                        columnsToOmit.add(column)
                        for (columnToReduce in 0..8) {
                            if (columnsToOmit.contains(columnToReduce))
                                continue
                            cellCandidates.forEach {
                                if (candidates[Point(row, columnToReduce)]?.remove(it) == true) {
                                    reduced = true
                                }
                            }
                        }
                    }
                }
            }
        }

        // By columns
        for (column in 0..8) {
            val rowsToOmit = mutableSetOf<Int>()

            for (row in 0..8) {
                if (rowsToOmit.contains(row))
                    continue
                candidates[Point(row, column)]?.let { cellCandidates ->
                    var i = 1
                    var identicalCells = 1
                    val rowsToReduce = mutableListOf<Int>()
                    while (row + i <= 8) {
                        if (!rowsToOmit.contains(row + i) && cellCandidates == candidates[Point(row + i, column)]) {
                            identicalCells++
                            rowsToOmit.add(row + i)
                            rowsToReduce.add(row + i)
                        }
                        i++
                    }
                    if (cellCandidates.size == identicalCells) {
                        rowsToOmit.add(row)
                        for (rowToReduce in 0..8) {
                            if (rowsToOmit.contains(rowToReduce))
                                continue
                            cellCandidates.forEach {
                                if (candidates[Point(rowToReduce, column)]?.remove(it) == true) {
                                    reduced = true
                                }
                            }
                        }
                    }
                }
            }
        }

        // TODO add here if the identical cells are in the same quadrant discard also the rest of candidates of the cells in the same quadrant

        return reduced
    }

    private fun reduceByCandidatesThatAreOnlyVerticallyOrHorizontallyInTheirQuadrants(): Boolean {
        // If a candidate can only be in vertical or horizontal in its quadrant, then we can
        // confirm that in the rest of the column of row of the other quadrants it can't be a candidate

        var candidatesReduced = false
        var isReducible = true

        while (isReducible) {
            isReducible = false

            val cells = candidates.keys

            for (point in cells) {
                val cellCandidates = candidates[point]!!

                cellCandidates.forEach { candidate ->
                    val (onlyHorizontally, onlyVertically) = isOnlyVerticallyOrHorizontallyInQuadrant(
                        point.row,
                        point.column,
                        candidate
                    )

                    val currentQuadrant = calculateQuadrantByRowAndColumn(point.row, point.column)

                    if (((onlyVertically && reduceColumnCandidatesInOtherQuadrants(
                            point.column,
                            currentQuadrant,
                            candidate
                        ))
                                || (onlyHorizontally && reduceRowCandidatesInOtherQuadrants(
                            point.row,
                            currentQuadrant,
                            candidate
                        ))) && !isReducible && !candidatesReduced) {
                        isReducible = true
                        candidatesReduced = true
                    }
                }
            }
        }

        return candidatesReduced
    }

    private fun reduceColumnCandidatesInOtherQuadrants(column: Int, currentQuadrant: Int, candidate: CellValue): Boolean {
        var candidatesReduced = false

        for (row in 0..8) {
            val cellQuadrant = calculateQuadrantByRowAndColumn(row, column)
            if (cellQuadrant != currentQuadrant && candidates[Point(row, column)]?.remove(candidate) == true && !candidatesReduced) {
                candidatesReduced = true
            }
        }

        return candidatesReduced
    }

    private fun reduceRowCandidatesInOtherQuadrants(row: Int, currentQuadrant: Int, candidate: CellValue): Boolean {
        var candidatesReduced = false

        for (column in 0..8) {
            val cellQuadrant = calculateQuadrantByRowAndColumn(row, column)
            if (cellQuadrant != currentQuadrant && candidates[Point(row, column)]?.remove(candidate) == true && !candidatesReduced) {
                candidatesReduced = true
            }
        }

        return candidatesReduced
    }

    private fun isOnlyVerticallyOrHorizontallyInQuadrant(
        row: Int,
        column: Int,
        cellValue: CellValue
    ): Pair<Boolean, Boolean> {

        val potentialCells = emptyCellsByQuadrant[calculateQuadrantByRowAndColumn(row, column)]!!
            .filter { it != Point(row, column) && candidates[it]!!.contains(cellValue) }

        val differentRowAndColumn = potentialCells.any { row != it.row && column != it.column }

        if (differentRowAndColumn) {
            return Pair(first = false, second = false)
        }

        val sameRow = potentialCells.any { row == it.row }
        val sameColumn = potentialCells.any { column == it.column }

        return Pair(sameRow && !sameColumn, sameColumn && !sameRow)
    }

    private fun reduceIfSingleInRowColumnOrQuadrant(): Boolean {
        var candidatesReduced = false
        val cellsToRemove = mutableMapOf<Point, CellValue>()

        // By row
        candidates.forEach {
            val cell = it.key
            val cellCandidates = it.value
            var found = false

            cellCandidates.asSequence()
                .takeWhile { !found }
                .forEach { candidate ->
                    var isTheOnlyCandidateInTheRow = true

                    for (column in 0..8) {
                        if (column == cell.column)
                            continue

                        val otherCellCandidates = candidates[Point(cell.row, column)]
                        if (otherCellCandidates?.contains(candidate) == true) {
                            isTheOnlyCandidateInTheRow = false
                            break
                        }
                    }

                    if (isTheOnlyCandidateInTheRow) {
                        emptyCellsByQuadrant[calculateQuadrantByRowAndColumn(cell.row, cell.column)]?.remove(cell)
                        cellsToRemove[cell] = candidate
                        found = true
                        setValueInBoard(cell.row, cell.column, candidate)

                        if (!candidatesReduced) {
                            candidatesReduced = true
                        }
                    }
                }
        }

        cellsToRemove.forEach {
            val cell = it.key
            val candidate = it.value
            for (row in 0..8) {
                candidates[Point(row, cell.column)]?.remove(candidate)
            }
            val (initialRowOfQuadrant, initialColumnOfQuadrant) =
                getInitialRowAndColumnOfQuadrant(calculateQuadrantByRowAndColumn(cell.row, cell.column))
            for (row in initialRowOfQuadrant..initialRowOfQuadrant + 2) {
                for (column in initialColumnOfQuadrant..initialColumnOfQuadrant + 2) {
                    candidates[Point(row, column)]?.remove(candidate)
                }
            }
        }
        cellsToRemove.forEach { candidates.remove(it.key) }
        cellsToRemove.clear()

        // By column
        candidates.forEach {
            val cell = it.key
            val cellCandidates = it.value
            var found = false

            cellCandidates.asSequence()
                .takeWhile { !found }
                .forEach { candidate ->
                    var isTheOnlyCandidateInTheColumn = true

                    for (row in 0..8) {
                        if (row == cell.row)
                            continue
                        val otherCellCandidates = candidates[Point(row, cell.column)]

                        if (otherCellCandidates?.contains(candidate) == true) {
                            isTheOnlyCandidateInTheColumn = false
                            break
                        }
                    }

                    if (isTheOnlyCandidateInTheColumn) {
                        // set cell and reduce candidates from row column and quadrant
                        val quadrant = calculateQuadrantByRowAndColumn(cell.row, cell.column)
                        emptyCellsByQuadrant[quadrant]?.remove(cell)
                        found = true
                        cellsToRemove[cell] = candidate
                        setValueInBoard(cell.row, cell.column, candidate)

                        if (!candidatesReduced) {
                            candidatesReduced = true
                        }
                    }
                }
        }

        cellsToRemove.forEach {
            val cell = it.key
            val candidate = it.value
            for (column in 0..8) {
                candidates[Point(cell.row, column)]?.remove(candidate)
            }
            val (initialRowOfQuadrant, initialColumnOfQuadrant) =
                getInitialRowAndColumnOfQuadrant(calculateQuadrantByRowAndColumn(cell.row, cell.column))
            for (row in initialRowOfQuadrant..initialRowOfQuadrant + 2) {
                for (column in initialColumnOfQuadrant..initialColumnOfQuadrant + 2) {
                    candidates[Point(row, column)]?.remove(candidate)
                }
            }
        }
        cellsToRemove.forEach { candidates.remove(it.key) }
        cellsToRemove.clear()

        // By quadrant
        candidates.forEach {
            val cell = it.key
            val cellCandidates = it.value
            var found = false

            cellCandidates.asSequence()
                .takeWhile { !found }
                .forEach { candidate ->
                    val quadrant = calculateQuadrantByRowAndColumn(cell.row, cell.column)
                    val (initialRowOfQuadrant, initialColumnOfQuadrant) = getInitialRowAndColumnOfQuadrant(quadrant)
                    var isTheOnlyCandidateInTheQuadrant = true

                    for (row in initialRowOfQuadrant..initialRowOfQuadrant + 2) {
                        for (column in initialColumnOfQuadrant..initialColumnOfQuadrant + 2) {
                            val otherCandidates = candidates[Point(row, column)]

                            if (otherCandidates?.contains(candidate) == true) {
                                isTheOnlyCandidateInTheQuadrant = false
                                break
                            }
                        }
                        if (!isTheOnlyCandidateInTheQuadrant) {
                            break
                        }
                    }

                    if (isTheOnlyCandidateInTheQuadrant) {
                        // set cell and reduce candidates from row column and quadrant
                        emptyCellsByQuadrant[quadrant]?.remove(cell)
                        found = true
                        cellsToRemove[cell] = candidate
                        setValueInBoard(cell.row, cell.column, candidate)

                        if (!candidatesReduced) {
                            candidatesReduced = true
                        }
                    }
                }
        }

        cellsToRemove.forEach {
            val cell = it.key
            val candidate = it.value

            for (row in 0..8) {
                candidates[Point(row, cell.column)]?.remove(candidate)
            }
            for (column in 0..8) {
                candidates[Point(cell.row, column)]?.remove(candidate)
            }
        }
        cellsToRemove.forEach { candidates.remove(it.key) }
        cellsToRemove.clear()

        return candidatesReduced
    }

    private fun reduceSingleCandidates(): Boolean {
        var candidatesReduced = false

        val singleCandidates = candidates.filter { it.value.size == 1 }
        singleCandidates.forEach {
            val point = it.key
            // In this point we are sure there is only one element/one iteration in the loop
            it.value.forEach { cellValue ->
                if (!candidatesReduced) {
                    candidatesReduced = true
                }
                candidates.remove(point)
                val quadrant = calculateQuadrantByRowAndColumn(point.row, point.column)
                emptyCellsByQuadrant[quadrant]?.remove(point)
                setValueInBoard(point.row, point.column, cellValue)

                // remove candidate from row, column and quadrant
                for (row in 0..8) {
                    candidates[Point(row, point.column)]?.remove(cellValue)
                }
                for (column in 0..8) {
                    candidates[Point(point.row, column)]?.remove(cellValue)
                }
                val (initialRowOfQuadrant, initialColumnOfQuadrant) = getInitialRowAndColumnOfQuadrant(quadrant)
                for (row in initialRowOfQuadrant..initialRowOfQuadrant + 2) {
                    for (column in initialColumnOfQuadrant..initialColumnOfQuadrant + 2) {
                        candidates[Point(row, column)]?.remove(cellValue)
                    }
                }
            }
        }

        return candidatesReduced
    }

    private fun setValueInBoard(row: Int, column: Int, cellValue: CellValue) {
        sudokuBoard[row, column] = cellValue
        rows[row]?.add(cellValue)
        columns[column]?.add(cellValue)
        quadrants[calculateQuadrantByRowAndColumn(row, column)]?.add(cellValue)
    }

    private fun unsetValueInBoard(row: Int, column: Int, cellValue: CellValue) {
        sudokuBoard[row, column] = CellValue.EMPTY
        rows[row]?.remove(cellValue)
        columns[column]?.remove(cellValue)
        quadrants[calculateQuadrantByRowAndColumn(row, column)]?.remove(cellValue)
    }

    private fun isNotInQuadrant(row: Int, column: Int, cellValue: CellValue): Boolean =
        quadrants[calculateQuadrantByRowAndColumn(row, column)]?.contains(cellValue) == false

    private fun isNotInRow(row: Int, cellValue: CellValue): Boolean =
        rows[row]?.contains(cellValue) == false

    private fun isNotInColumn(column: Int, cellValue: CellValue): Boolean =
        columns[column]?.contains(cellValue) == false

    private fun goWithTheBruteForceBaby() {
        fillBoard(emptyCellsByQuadrant.flatMap { it.value }.toSet())
    }

    private fun fillBoard(emptyCells: Set<Point>): Boolean {
        if (emptyCells.isEmpty()) {
            return true
        }

        emptyCells.forEach {
            val (row, column) = it

            for (cellValue in CellValue.VALUES) {
                if (canAssignValue(row, column, cellValue)) {
                    setValueInBoard(row, column, cellValue)
                    if (fillBoard(emptyCells - Point(row, column))) {
                        return true
                    } else {
                        unsetValueInBoard(row, column, cellValue)
                    }
                }
            }
        }

        return false
    }

    private fun canAssignValue(row: Int, column: Int, cellValue: CellValue): Boolean =
        isNotInQuadrant(row, column, cellValue) && isNotInRow(row, cellValue) && isNotInColumn(column, cellValue)
}