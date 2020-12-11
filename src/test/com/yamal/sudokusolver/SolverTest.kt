package com.yamal.sudokusolver

import com.yamal.sudokusolver.model.Board
import com.yamal.utils.sudokuBoardFromIntArrayOfArrays
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis
import kotlin.test.assertEquals

class SolverTest {

    @Test
    fun `almost done`() {
        measureAndPrintTime {
            assertSudokuResolution(
                sudokuBoardFromIntArrayOfArrays(
                    arrayOf(
                        arrayOf(0, 0, 0, 8, 5, 4, 2, 6, 3),
                        arrayOf(0, 0, 0, 2, 1, 7, 5, 8, 9),
                        arrayOf(0, 0, 0, 9, 3, 6, 1, 7, 4),
                        arrayOf(8, 1, 9, 4, 7, 5, 6, 3, 0),
                        arrayOf(7, 2, 3, 6, 9, 8, 4, 5, 1),
                        arrayOf(4, 6, 5, 1, 2, 3, 8, 9, 7),
                        arrayOf(2, 9, 0, 7, 6, 1, 3, 4, 5),
                        arrayOf(1, 0, 4, 5, 8, 9, 7, 2, 6),
                        arrayOf(6, 5, 7, 3, 4, 2, 9, 1, 8)
                    )
                ), sudokuBoardFromIntArrayOfArrays(
                    arrayOf(
                        arrayOf(9, 7, 1, 8, 5, 4, 2, 6, 3),
                        arrayOf(3, 4, 6, 2, 1, 7, 5, 8, 9),
                        arrayOf(5, 8, 2, 9, 3, 6, 1, 7, 4),
                        arrayOf(8, 1, 9, 4, 7, 5, 6, 3, 2),
                        arrayOf(7, 2, 3, 6, 9, 8, 4, 5, 1),
                        arrayOf(4, 6, 5, 1, 2, 3, 8, 9, 7),
                        arrayOf(2, 9, 8, 7, 6, 1, 3, 4, 5),
                        arrayOf(1, 3, 4, 5, 8, 9, 7, 2, 6),
                        arrayOf(6, 5, 7, 3, 4, 2, 9, 1, 8)
                    )
                )
            )
        }
    }

    @Test
    fun `1 of 4 stars`() {
        measureAndPrintTime {
            assertSudokuResolution(
                sudokuBoardFromIntArrayOfArrays(
                    arrayOf(
                        arrayOf(0, 0, 0, 8, 0, 0, 0, 0, 0),
                        arrayOf(3, 4, 0, 0, 0, 0, 5, 0, 9),
                        arrayOf(0, 0, 0, 0, 0, 6, 1, 0, 0),
                        arrayOf(0, 0, 9, 4, 0, 0, 6, 0, 0),
                        arrayOf(0, 2, 0, 0, 0, 0, 0, 5, 0),
                        arrayOf(4, 0, 5, 1, 0, 3, 0, 0, 7),
                        arrayOf(0, 0, 8, 0, 6, 1, 3, 0, 0),
                        arrayOf(0, 3, 4, 5, 8, 0, 7, 0, 6),
                        arrayOf(6, 0, 0, 0, 0, 2, 0, 0, 0)
                    )
                ), sudokuBoardFromIntArrayOfArrays(
                    arrayOf(
                        arrayOf(9, 7, 1, 8, 5, 4, 2, 6, 3),
                        arrayOf(3, 4, 6, 2, 1, 7, 5, 8, 9),
                        arrayOf(5, 8, 2, 9, 3, 6, 1, 7, 4),
                        arrayOf(8, 1, 9, 4, 7, 5, 6, 3, 2),
                        arrayOf(7, 2, 3, 6, 9, 8, 4, 5, 1),
                        arrayOf(4, 6, 5, 1, 2, 3, 8, 9, 7),
                        arrayOf(2, 9, 8, 7, 6, 1, 3, 4, 5),
                        arrayOf(1, 3, 4, 5, 8, 9, 7, 2, 6),
                        arrayOf(6, 5, 7, 3, 4, 2, 9, 1, 8)
                    )
                )
            )
        }
    }

    @Test
    fun `2 of 4 stars`() {
        measureAndPrintTime {
            assertSudokuResolution(
                sudokuBoardFromIntArrayOfArrays(
                    arrayOf(
                        arrayOf(0, 2, 0, 0, 0, 0, 0, 1, 0),
                        arrayOf(4, 0, 3, 0, 0, 0, 0, 8, 5),
                        arrayOf(0, 0, 0, 0, 2, 0, 0, 6, 7),
                        arrayOf(0, 0, 6, 0, 0, 0, 5, 0, 0),
                        arrayOf(7, 5, 0, 0, 0, 9, 0, 0, 6),
                        arrayOf(0, 0, 0, 0, 4, 0, 0, 0, 0),
                        arrayOf(3, 0, 1, 0, 0, 0, 0, 0, 0),
                        arrayOf(0, 0, 8, 0, 9, 5, 0, 0, 0),
                        arrayOf(2, 9, 0, 0, 7, 8, 0, 0, 0)
                    )
                ), sudokuBoardFromIntArrayOfArrays(
                    arrayOf(
                        arrayOf(8, 2, 7, 6, 5, 4, 9, 1, 3),
                        arrayOf(4, 6, 3, 9, 1, 7, 2, 8, 5),
                        arrayOf(5, 1, 9, 8, 2, 3, 4, 6, 7),
                        arrayOf(9, 8, 6, 7, 3, 1, 5, 2, 4),
                        arrayOf(7, 5, 4, 2, 8, 9, 1, 3, 6),
                        arrayOf(1, 3, 2, 5, 4, 6, 7, 9, 8),
                        arrayOf(3, 7, 1, 4, 6, 2, 8, 5, 9),
                        arrayOf(6, 4, 8, 1, 9, 5, 3, 7, 2),
                        arrayOf(2, 9, 5, 3, 7, 8, 6, 4, 1)
                    )
                )
            )
        }
    }

    @Test
    fun `3 of 4 stars`() {
        measureAndPrintTime {
            assertSudokuResolution(
                sudokuBoardFromIntArrayOfArrays(
                    arrayOf(
                        arrayOf(1, 9, 0, 3, 5, 0, 0, 0, 6),
                        arrayOf(0, 0, 3, 0, 8, 0, 0, 0, 0),
                        arrayOf(0, 0, 5, 4, 0, 6, 9, 7, 0),
                        arrayOf(9, 4, 6, 0, 0, 0, 0, 0, 0),
                        arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
                        arrayOf(0, 3, 0, 2, 4, 0, 0, 0, 5),
                        arrayOf(0, 0, 0, 6, 0, 0, 0, 0, 0),
                        arrayOf(0, 0, 0, 0, 0, 0, 0, 5, 7),
                        arrayOf(5, 2, 9, 8, 0, 0, 0, 0, 0)
                    )
                ), sudokuBoardFromIntArrayOfArrays(
                    arrayOf(
                        arrayOf(1, 9, 4, 3, 5, 7, 8, 2, 6),
                        arrayOf(6, 7, 3, 9, 8, 2, 5, 4, 1),
                        arrayOf(2, 8, 5, 4, 1, 6, 9, 7, 3),
                        arrayOf(9, 4, 6, 5, 3, 8, 7, 1, 2),
                        arrayOf(8, 5, 2, 7, 6, 1, 4, 3, 9),
                        arrayOf(7, 3, 1, 2, 4, 9, 6, 8, 5),
                        arrayOf(4, 1, 7, 6, 2, 5, 3, 9, 8),
                        arrayOf(3, 6, 8, 1, 9, 4, 2, 5, 7),
                        arrayOf(5, 2, 9, 8, 7, 3, 1, 6, 4)
                    )
                )
            )
        }
    }

    @Test
    fun `some hard sudoku from internet`() {
        measureAndPrintTime {
            assertSudokuResolution(
                sudokuBoardFromIntArrayOfArrays(
                    arrayOf(
                        arrayOf(0, 2, 0, 6, 0, 8, 0, 0, 0),
                        arrayOf(5, 8, 0, 0, 0, 9, 7, 0, 0),
                        arrayOf(0, 0, 0, 0, 4, 0, 0, 0, 0),
                        arrayOf(3, 7, 0, 0, 0, 0, 5, 0, 0),
                        arrayOf(6, 0, 0, 0, 0, 0, 0, 0, 4),
                        arrayOf(0, 0, 8, 0, 0, 0, 0, 1, 3),
                        arrayOf(0, 0, 0, 0, 2, 0, 0, 0, 0),
                        arrayOf(0, 0, 9, 8, 0, 0, 0, 3, 6),
                        arrayOf(0, 0, 0, 3, 0, 6, 0, 9, 0)
                    )
                ), sudokuBoardFromIntArrayOfArrays(
                    arrayOf(
                        arrayOf(1, 2, 3, 6, 7, 8, 9, 4, 5),
                        arrayOf(5, 8, 4, 2, 3, 9, 7, 6, 1),
                        arrayOf(9, 6, 7, 1, 4, 5, 3, 2, 8),
                        arrayOf(3, 7, 2, 4, 6, 1, 5, 8, 9),
                        arrayOf(6, 9, 1, 5, 8, 3, 2, 7, 4),
                        arrayOf(4, 5, 8, 7, 9, 2, 6, 1, 3),
                        arrayOf(8, 3, 6, 9, 2, 4, 1, 5, 7),
                        arrayOf(2, 1, 9, 8, 5, 7, 4, 3, 6),
                        arrayOf(7, 4, 5, 3, 1, 6, 8, 9, 2)
                    )
                )
            )
        }
    }

    @Test
    fun `print some sudokus from the internet`() {

        // https://m.europapress.es/ciencia/laboratorio/noticia-sudoku-no-puede-resolver-menos-17-cifras-pista-inicio-20120110124823.html
        solveAndPrint(sudokuBoardFromIntArrayOfArrays(
            arrayOf(
                arrayOf(0, 0, 0, 8, 0, 1, 0, 0, 0),
                arrayOf(0, 0, 0, 0, 0, 0, 4, 3, 0),
                arrayOf(5, 0, 0, 0, 0, 0, 0, 0, 0),
                arrayOf(0, 0, 0, 0, 7, 0, 8, 0, 0),
                arrayOf(0, 0, 0, 0, 0, 0, 1, 0, 0),
                arrayOf(0, 2, 0, 0, 3, 0, 0, 0, 0),
                arrayOf(6, 0, 0, 0, 0, 0, 0, 7, 5),
                arrayOf(0, 0, 3, 4, 0, 0, 0, 0, 0),
                arrayOf(0, 0, 0, 2, 0, 0, 6, 0, 0)
            )
        ))

        // https://www.lavanguardia.com/vida/20160526/402063242301/enigma-semana-sudoku.html
        solveAndPrint(sudokuBoardFromIntArrayOfArrays(
            arrayOf(
                arrayOf(8, 0, 0, 0, 0, 0, 0, 0, 0),
                arrayOf(0, 0, 3, 6, 0, 0, 0, 0, 0),
                arrayOf(0, 7, 0, 0, 9, 0, 2, 0, 0),
                arrayOf(0, 5, 0, 0, 0, 7, 0, 0, 0),
                arrayOf(0, 0, 0, 0, 4, 5, 7, 0, 0),
                arrayOf(0, 0, 0, 1, 0, 0, 0, 3, 0),
                arrayOf(0, 0, 1, 0, 0, 0, 0, 6, 8),
                arrayOf(0, 0, 8, 5, 0, 0, 0, 1, 0),
                arrayOf(0, 9, 0, 0, 0, 0, 4, 0, 0)
            )
        ))


    }

    @Test
    fun `normal level from the app`() {
        measureAndPrintTime {
            assertSudokuResolution(
                sudokuBoardFromIntArrayOfArrays(
                    arrayOf(
                        arrayOf(0, 0, 0, 0, 0, 0, 6, 0, 9),
                        arrayOf(1, 0, 0, 0, 0, 4, 0, 0, 0),
                        arrayOf(0, 0, 5, 3, 0, 6, 8, 2, 1),
                        arrayOf(0, 0, 4, 6, 7, 0, 0, 5, 0),
                        arrayOf(0, 0, 7, 0, 0, 0, 9, 0, 0),
                        arrayOf(0, 0, 0, 5, 4, 0, 0, 0, 0),
                        arrayOf(3, 7, 0, 4, 0, 5, 2, 0, 6),
                        arrayOf(0, 0, 0, 0, 0, 0, 5, 1, 0),
                        arrayOf(0, 6, 0, 0, 2, 0, 0, 3, 7)
                    )
                ),
                sudokuBoardFromIntArrayOfArrays(
                    arrayOf(
                        arrayOf(8, 3, 2, 1, 5, 7, 6, 4, 9),
                        arrayOf(1, 9, 6, 2, 8, 4, 3, 7, 5),
                        arrayOf(7, 4, 5, 3, 9, 6, 8, 2, 1),
                        arrayOf(9, 8, 4, 6, 7, 2, 1, 5, 3),
                        arrayOf(2, 5, 7, 8, 3, 1, 9, 6, 4),
                        arrayOf(6, 1, 3, 5, 4, 9, 7, 8, 2),
                        arrayOf(3, 7, 8, 4, 1, 5, 2, 9, 6),
                        arrayOf(4, 2, 9, 7, 6, 3, 5, 1, 8),
                        arrayOf(5, 6, 1, 9, 2, 8, 4, 3, 7)
                    )
                )
            )
        }
    }

    @Test
    fun `hard level`() {
        measureAndPrintTime {
            assertSudokuResolution(
                sudokuBoardFromIntArrayOfArrays(
                    arrayOf(
                        arrayOf(0, 0, 0, 0, 6, 8, 0, 3, 0),
                        arrayOf(1, 9, 0, 0, 0, 0, 0, 0, 0),
                        arrayOf(8, 0, 3, 1, 0, 0, 2, 0, 0),
                        arrayOf(4, 0, 0, 0, 5, 1, 0, 6, 0),
                        arrayOf(7, 0, 0, 0, 2, 0, 0, 0, 4),
                        arrayOf(0, 0, 0, 0, 7, 0, 8, 0, 0),
                        arrayOf(0, 1, 0, 0, 0, 5, 0, 0, 7),
                        arrayOf(0, 0, 4, 0, 0, 0, 0, 0, 0),
                        arrayOf(0, 5, 0, 0, 3, 0, 1, 0, 0)
                    )
                ),
                sudokuBoardFromIntArrayOfArrays(
                    arrayOf(
                        arrayOf(2, 4, 7, 5, 6, 8, 9, 3, 1),
                        arrayOf(1, 9, 5, 3, 4, 2, 6, 7, 8),
                        arrayOf(8, 6, 3, 1, 9, 7, 2, 4, 5),
                        arrayOf(4, 3, 9, 8, 5, 1, 7, 6, 2),
                        arrayOf(7, 8, 1, 9, 2, 6, 3, 5, 4),
                        arrayOf(5, 2, 6, 4, 7, 3, 8, 1, 9),
                        arrayOf(3, 1, 2, 6, 8, 5, 4, 9, 7),
                        arrayOf(6, 7, 4, 2, 1, 9, 5, 8, 3),
                        arrayOf(9, 5, 8, 7, 3, 4, 1, 2, 6)
                    )
                )
            )
        }
    }

    @Test
    fun `expert level`() {
        measureAndPrintTime {
            assertSudokuResolution(
                sudokuBoardFromIntArrayOfArrays(
                    arrayOf(
                        arrayOf(0, 0, 1, 0, 0, 0, 0, 6, 0),
                        arrayOf(0, 0, 0, 0, 5, 7, 0, 9, 0),
                        arrayOf(5, 0, 0, 0, 0, 0, 3, 0, 0),
                        arrayOf(6, 0, 0, 8, 0, 0, 0, 0, 1),
                        arrayOf(0, 0, 0, 0, 3, 0, 4, 0, 8),
                        arrayOf(0, 0, 0, 4, 0, 0, 0, 0, 0),
                        arrayOf(0, 3, 0, 0, 0, 2, 0, 0, 0),
                        arrayOf(1, 8, 4, 0, 0, 0, 0, 0, 0),
                        arrayOf(0, 6, 0, 9, 0, 3, 0, 0, 0)
                    )
                ),
                sudokuBoardFromIntArrayOfArrays(
                    arrayOf(
                        arrayOf(4, 7, 1, 3, 9, 8, 5, 6, 2),
                        arrayOf(3, 2, 8, 6, 5, 7, 1, 9, 4),
                        arrayOf(5, 9, 6, 2, 1, 4, 3, 8, 7),
                        arrayOf(6, 4, 3, 8, 7, 9, 2, 5, 1),
                        arrayOf(2, 1, 9, 5, 3, 6, 4, 7, 8),
                        arrayOf(8, 5, 7, 4, 2, 1, 6, 3, 9),
                        arrayOf(9, 3, 5, 1, 8, 2, 7, 4, 6),
                        arrayOf(1, 8, 4, 7, 6, 5, 9, 2, 3),
                        arrayOf(7, 6, 2, 9, 4, 3, 8, 1, 5)
                    )
                )
            )
        }
    }

    private fun measureAndPrintTime(block: () -> Unit) {
        val millis = measureTimeMillis {
            block()
        }

        print("Executed in $millis ms")
    }

    private fun assertSudokuResolution(givenUnresolvedSudoku: Board, expectedResolvedSudoku: Board) {
        val actualResolvedSudoku = Solver(givenUnresolvedSudoku, USE_BRUTE_FORCE_ONLY).solve()
        assertEquals(expectedResolvedSudoku, actualResolvedSudoku)
    }

    private fun solveAndPrint(sudokuBoard: Board) {
        val sudokuResolver = Solver(sudokuBoard, USE_BRUTE_FORCE_ONLY)
        println(sudokuResolver.solve())
    }

    private companion object {
        const val USE_BRUTE_FORCE_ONLY = false
    }
}