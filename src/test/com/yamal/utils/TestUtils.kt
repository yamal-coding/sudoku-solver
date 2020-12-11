package com.yamal.utils

import com.yamal.sudokusolver.model.Board
import com.yamal.sudokusolver.model.BoardMatrixImpl
import com.yamal.sudokusolver.model.CellValue
import com.yamal.sudokusolver.model.CellValue.ONE
import com.yamal.sudokusolver.model.CellValue.TWO
import com.yamal.sudokusolver.model.CellValue.THREE
import com.yamal.sudokusolver.model.CellValue.FOUR
import com.yamal.sudokusolver.model.CellValue.FIVE
import com.yamal.sudokusolver.model.CellValue.SIX
import com.yamal.sudokusolver.model.CellValue.SEVEN
import com.yamal.sudokusolver.model.CellValue.EIGHT
import com.yamal.sudokusolver.model.CellValue.NINE
import com.yamal.sudokusolver.model.CellValue.EMPTY
import com.yamal.sudokusolver.model.ArrayOfArraysMatrix
import com.yamal.sudokusolver.model.Matrix

fun intMatrixToCellValueMatrix(intMatrix: Matrix<Int>): Matrix<CellValue> {
    val cellValueMatrix = ArrayOfArraysMatrix(
        Array(intMatrix.getDimension()) { Array(intMatrix.getDimension()) { CellValue.EMPTY } }
    )

    for (row in 0 until intMatrix.getDimension()) {
        for (column in 0 until intMatrix.getDimension()) {
            cellValueMatrix[row, column] = when (intMatrix[row, column]) {
                0 -> EMPTY
                1 -> ONE
                2 -> TWO
                3 -> THREE
                4 -> FOUR
                5 -> FIVE
                6 -> SIX
                7 -> SEVEN
                8 -> EIGHT
                9 -> NINE
                else -> throw IllegalArgumentException("Invalid cell int value ${intMatrix[row, column]}.")
            }
        }
    }

    return cellValueMatrix
}

fun sudokuBoardFromIntArrayOfArrays(intArrayOfArrays: Array<Array<Int>>): Board =
    BoardMatrixImpl(intMatrixToCellValueMatrix(ArrayOfArraysMatrix(intArrayOfArrays)))