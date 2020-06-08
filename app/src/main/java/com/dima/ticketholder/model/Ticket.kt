package com.dima.ticketholder.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tickets")
data class Ticket(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var mark: String = "",
    var number: Int = 0,
    var usages: Int = 1
) {

    private val nonEmptyPartWithSize: Triple<Array<BooleanArray>, Int, Int>
        get() = getNonEmptyPart(splitArrayTo2dArray(markStringToArray(mark)))

    companion object {
        const val ROWS = 3
        const val COLS = 4

        private const val CHECKED = "#"
        private const val UNCHECKED = "0"

        private fun arrayToMarkString(array: BooleanArray): String
                = array.joinToString("") { if (it) CHECKED else UNCHECKED }

        fun createTicketFromArray(array: BooleanArray): Ticket
                = Ticket(mark = arrayToMarkString(array))

        fun markStringToArray(mark: String): BooleanArray
                = BooleanArray(ROWS * COLS) { mark[it].toString() == CHECKED}

        fun splitArrayTo2dArray(array: BooleanArray) = Array(ROWS) { index ->
            array.sliceArray((index * COLS) until ((index + 1) * COLS))
        }

        fun getNonEmptyPart(array: Array<BooleanArray>): Triple<Array<BooleanArray>, Int, Int> {
            var firstRow = -1
            var lastRow = -1
            var firstColumn = -1
            var lastColumn = -1
            array.forEachIndexed { rowIndex, booleans ->
                if (booleans.any { it }) {
                    firstRow = if (firstRow == -1) rowIndex else firstRow
                    lastRow = rowIndex
                    booleans.forEachIndexed { colIndex, b ->
                        if (b) {
                            firstColumn = if (firstColumn == -1 || firstColumn > colIndex) colIndex else firstColumn
                            lastColumn = if (lastColumn < colIndex) colIndex else lastColumn
                        }
                    }
                }
            }
            val result = array.sliceArray(firstRow..lastRow)
                .map { it.sliceArray(firstColumn..lastColumn) }
                .toTypedArray()
            return Triple(result, lastRow - firstRow, lastColumn - firstColumn)
        }

        fun rotate(array: Array<BooleanArray>) = Array(array.first().size) { index ->
            array.map { it[index] }.toBooleanArray()
        }

        fun horizontalReflection(array: Array<BooleanArray>) = array.reversedArray()

        fun verticalReflection(array: Array<BooleanArray>)
                = array.map { it.reversedArray() }.toTypedArray()
    }

    fun markToString(): String = Array(ROWS) { index ->
        mark.substring((index * COLS) until ((index + 1) * COLS))
            .toList()
            .joinToString(" ")
    }.joinToString("\n")

    fun doCompare(part: Array<BooleanArray>, anotherPart: Array<BooleanArray>): Boolean  {
        val h = horizontalReflection(part)
        val v = verticalReflection(part)
        val hv = verticalReflection(h)
        return part.contentDeepEquals(anotherPart)
                || h.contentDeepEquals(anotherPart)
                || v.contentDeepEquals(anotherPart)
                || hv.contentDeepEquals(anotherPart)
    }

    fun compare(another: Ticket): Boolean {
        val (anotherPart, anotherWidth, anotherHeight) = another.nonEmptyPartWithSize
        val (thisPart, thisWidth, thisHeight) = nonEmptyPartWithSize

        var part = thisPart
        val sameSize = thisWidth == anotherWidth && thisHeight == anotherHeight
        val sameSizeAfterRotate = thisWidth == anotherHeight && thisHeight == anotherWidth
        var result = false
        
        if (sameSize || sameSizeAfterRotate) {
            result = doCompare(part, anotherPart)
            if (!result && sameSizeAfterRotate) {
                part = rotate(part)
                result = doCompare(part, anotherPart)
            }
        }
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Ticket

        if (mark != other.mark) return false
        if (id != other.id) return false
        if (number != other.number) return false
        if (usages != other.usages) return false

        return true
    }

    override fun hashCode(): Int = id
}
