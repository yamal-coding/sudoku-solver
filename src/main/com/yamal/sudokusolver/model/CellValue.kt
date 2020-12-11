package com.yamal.sudokusolver.model

enum class CellValue(private val stringValue: String) {
    EMPTY(" "),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9");

    companion object {
        val VALUES = listOf(ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE)
    }

    override fun toString(): String = stringValue
}