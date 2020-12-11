package com.yamal.sudokusolver.model

class ArrayOfArraysMatrix<T>(private val arrayOfArrays: Array<Array<T>>) : Matrix<T> {

    private val matrixDimensionRange: IntRange by lazy { 0..arrayOfArrays.size }

    init {
        require(matrixIsNxN()) { "The provided matrix is not a N x N matrix." }
    }

    private fun matrixIsNxN(): Boolean {
        val width = arrayOfArrays.size
        var isNxN = true

        arrayOfArrays.asSequence()
            .takeWhile { isNxN }
            .forEach { row ->
                if (row.size != width) {
                    isNxN = false
                }
            }

        return isNxN
    }

    override fun getDimension(): Int = arrayOfArrays.size

    override fun get(row: Int, column: Int): T =
        assertThatRowAndColumnAreInRangeAndOperateOverMatrix(row, column) {
            arrayOfArrays[row][column]
        }

    override fun set(row: Int, column: Int, value: T) {
        assertThatRowAndColumnAreInRangeAndOperateOverMatrix(row, column) {
            arrayOfArrays[row][column] = value
        }
    }

    private inline fun <T> assertThatRowAndColumnAreInRangeAndOperateOverMatrix(
        row: Int,
        column: Int,
        operation: () -> T
    ): T {
        require(rowAndColumnAreInRange(row, column)) { "Invalid matrix coordinates: $row and $column out of bounds!" }

        return operation()
    }

    private fun rowAndColumnAreInRange(row: Int, column: Int): Boolean =
        row in matrixDimensionRange && column in matrixDimensionRange

}