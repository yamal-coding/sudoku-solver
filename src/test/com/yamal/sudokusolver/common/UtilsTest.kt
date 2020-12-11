package com.yamal.sudokusolver.common

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UtilsTest {

    @Test
    fun `x in (0, 2) and y in (0, 2) coordinates are in quadrant 0`() {
        for (x in 0..2) {
            for (y in 0..2) {
                assertEquals(0, calculateQuadrantByRowAndColumn(x, y))
            }
        }
    }

    @Test
    fun `x in (0, 2) and y in (3, 5) coordinates are in quadrant 1`() {
        for (x in 0..2) {
            for (y in 3..5) {
                assertEquals(1, calculateQuadrantByRowAndColumn(x, y))
            }
        }
    }

    @Test
    fun `x in (0, 2) and y in (6, 8) coordinates are in quadrant 2`() {
        for (x in 0..2) {
            for (y in 6..8) {
                assertEquals(2, calculateQuadrantByRowAndColumn(x, y))
            }
        }
    }

    @Test
    fun `x in (3, 5) and y in (0, 2) coordinates are in quadrant 3`() {
        for (x in 3..5) {
            for (y in 0..2) {
                assertEquals(3, calculateQuadrantByRowAndColumn(x, y))
            }
        }
    }

    @Test
    fun `x in (3, 5) and y in (3, 5) coordinates are in quadrant 4`() {
        for (x in 3..5) {
            for (y in 3..5) {
                assertEquals(4, calculateQuadrantByRowAndColumn(x, y))
            }
        }
    }

    @Test
    fun `x in (3, 5) and y in (6, 8) coordinates are in quadrant 5`() {
        for (x in 3..5) {
            for (y in 6..8) {
                assertEquals(5, calculateQuadrantByRowAndColumn(x, y))
            }
        }
    }

    @Test
    fun `x in (6, 8) and y in (0, 2) coordinates are in quadrant 6`() {
        for (x in 6..8) {
            for (y in 0..2) {
                assertEquals(6, calculateQuadrantByRowAndColumn(x, y))
            }
        }
    }

    @Test
    fun `x in (6, 8) and y in (3, 5) coordinates are in quadrant 7`() {
        for (x in 6..8) {
            for (y in 3..5) {
                assertEquals(7, calculateQuadrantByRowAndColumn(x, y))
            }
        }
    }

    @Test
    fun `x in (6, 8) and y in (6, 8) coordinates are in quadrant 8`() {
        for (x in 6..8) {
            for (y in 6..8) {
                assertEquals(8, calculateQuadrantByRowAndColumn(x, y))
            }
        }
    }
}