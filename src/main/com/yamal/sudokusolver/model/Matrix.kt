package com.yamal.sudokusolver.model

interface Matrix<T> {
    operator fun get(row: Int, column: Int): T

    operator fun set(row: Int, column: Int, value: T)

    fun getDimension(): Int
}
