package com.yamal.sudokusolver.model

class BoardMatrixImpl : Board {

    private val board: Matrix<CellValue>

    constructor(board: Matrix<CellValue>) {
        this.board = board

        if (!isValidSudokuMatrix()) {
            throw IllegalArgumentException("The provided matrix is not 9x9.")
        }
    }

    constructor() {
        this.board = ArrayOfArraysMatrix(
            arrayOf(
                arrayOf(
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY,
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY,
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY
                ),
                arrayOf(
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY,
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY,
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY
                ),
                arrayOf(
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY,
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY,
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY
                ),
                arrayOf(
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY,
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY,
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY
                ),
                arrayOf(
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY,
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY,
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY
                ),
                arrayOf(
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY,
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY,
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY
                ),
                arrayOf(
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY,
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY,
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY
                ),
                arrayOf(
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY,
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY,
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY
                ),
                arrayOf(
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY,
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY,
                    CellValue.EMPTY, CellValue.EMPTY, CellValue.EMPTY
                )
            )
        )
    }

    private fun isValidSudokuMatrix(): Boolean =
        board.getDimension() !in SUDOKU_BOARD_DIMENSION_RANGE

    override fun get(row: Int, column: Int): CellValue =
        assertThatRowAndColumnAreInRangeAndOperateOverBoard(row, column) {
            board[row, column]
        }

    override fun set(row: Int, column: Int, cellValue: CellValue) {
        assertThatRowAndColumnAreInRangeAndOperateOverBoard(row, column) {
            board[row, column] = cellValue
        }
    }

    private inline fun <T> assertThatRowAndColumnAreInRangeAndOperateOverBoard(
        row: Int,
        column: Int,
        operation: () -> T
    ): T {
        require(rowAndColumnAreInRange(row, column)) { "Invalid board coordinates: $row and $column out of bounds!" }

        return operation()
    }

    private fun rowAndColumnAreInRange(row: Int, column: Int) =
        row in SUDOKU_BOARD_DIMENSION_RANGE && column in SUDOKU_BOARD_DIMENSION_RANGE

    companion object {
        private val SUDOKU_BOARD_DIMENSION_RANGE: IntRange = 0..8
    }
}