package com.yamal.sudokusolver.common

import com.yamal.sudokusolver.model.CellValue.ONE
import com.yamal.sudokusolver.model.CellValue.TWO
import com.yamal.sudokusolver.model.CellValue.THREE
import com.yamal.sudokusolver.model.CellValue.FOUR
import com.yamal.sudokusolver.model.CellValue.FIVE
import com.yamal.sudokusolver.model.CellValue.SIX
import com.yamal.sudokusolver.model.CellValue.SEVEN
import com.yamal.sudokusolver.model.CellValue.EIGHT
import com.yamal.sudokusolver.model.CellValue.NINE
import com.yamal.sudokusolver.model.Point

val FULL_SET_OF_CELLS = setOf(ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE)

inline fun processActionOn9x9Board(action: (Int, Int) -> Unit) {
    for (row in 0..8) {
        for (column in 0..8) {
            action(row, column)
        }
    }
}

fun calculateQuadrantByRowAndColumn(row: Int, column: Int): Int =
    when(row) {
        in 0..2 -> {
            when (column) {
                in 0..2 -> 0
                in 3..5 -> 1
                in 6..8 -> 2
                else -> throw IllegalArgumentException("Row $row is not in range [0, 8]")
            }
        }
        in 3..5 -> {
            when (column) {
                in 0..2 -> 3
                in 3..5 -> 4
                in 6..8 -> 5
                else -> throw IllegalArgumentException("Row $row is not in range [0, 8]")
            }
        }
        in 6..8 -> {
            when (column) {
                in 0..2 -> 6
                in 3..5 -> 7
                in 6..8 -> 8
                else -> throw IllegalArgumentException("Row $row is not in range [0, 8]")
            }
        }
        else -> throw IllegalArgumentException("Column $column is not in range [0, 8]")
    }

fun getInitialRowAndColumnOfQuadrant(quadrant: Int): Point =
    when (quadrant) {
        0 -> Point(0, 0)
        1 -> Point(0, 3)
        2 -> Point(0, 6)
        3 -> Point(3, 0)
        4 -> Point(3, 3)
        5 -> Point(3, 6)
        6 -> Point(6, 0)
        7 -> Point(6, 3)
        8 -> Point(6, 6)
        else -> throw IllegalArgumentException("Quadrant $quadrant is out of bounds!")
    }
