package com.yamal.sudokusolver.model

abstract class Board {

    abstract operator fun get(row: Int, column: Int): CellValue

    abstract operator fun set(row: Int, column: Int, cellValue: CellValue)

    override fun equals(other: Any?): Boolean =
        if (other is Board) {
            var row = 0
            var column: Int
            var areEquals = true
            while (row < 9 && areEquals) {
                column = 0
                while (column < 9 && areEquals) {
                    if (other[row, column] != this[row, column]) {
                        areEquals = false
                    }
                    column++
                }
                row++
            }
            areEquals
        } else {
            super.equals(other)
        }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String {
        val stringBuffer = StringBuffer("")

        for (row in 0..8) {
            stringBuffer.append("|")
            for (column in 0..2) {
                stringBuffer.append(this[row, column])
            }
            stringBuffer.append("|")
            for (column in 3..5) {
                stringBuffer.append(this[row, column])
            }
            stringBuffer.append("|")
            for (column in 6..8) {
                stringBuffer.append(this[row, column])
            }
            stringBuffer.append("|")
            stringBuffer.append("\n")
            if (row == 2 || row == 5) {

                for (i in 0..12) {
                    stringBuffer.append("-")
                }
                stringBuffer.append("\n")
            }
        }

        return stringBuffer.toString()
    }
}
