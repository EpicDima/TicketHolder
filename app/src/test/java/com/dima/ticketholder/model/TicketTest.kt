package com.dima.ticketholder.model

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldNotBeNull
import org.junit.Test

class TicketTest {

    companion object {
        const val TEST_CASES_FILE = "com.dima.ticketholder/tickets.json"

        const val EQUALS_CASES_KEY = "equals"
        const val NOT_EQUALS_CASES_KEY = "not_equals"

        const val FIRST_MARK_KEY = "first"
        const val SECOND_MARK_KEY = "second"

        const val LENGTH = Ticket.ROWS * Ticket.COLS
    }

    @Test
    fun compareEqualsTickets() = compareTickets(EQUALS_CASES_KEY) { a, b ->
        a.compare(b).shouldBeTrue()
    }

    @Test
    fun compareNotEqualsTickets() = compareTickets(NOT_EQUALS_CASES_KEY) { a, b ->
        a.compare(b).shouldBeFalse()
    }

    private fun compareTickets(casesKey: String, check: (a: Ticket, b: Ticket) -> Unit) {
        val testCases = javaClass.classLoader?.getResourceAsStream(TEST_CASES_FILE)?.let {
            val rootObject = Parser.default().parse(it) as JsonObject
            rootObject[casesKey] as JsonArray<*>
        }

        testCases?.forEach { testCase ->
            val firstTestMark = getMarkFromTestCase(testCase as JsonObject, FIRST_MARK_KEY)
            val secondTestMark = getMarkFromTestCase(testCase, SECOND_MARK_KEY)

            val a = ticketFromTestMark(firstTestMark)
            val b = ticketFromTestMark(secondTestMark)

            check(a, b)
        }

        testCases.shouldNotBeNull()
    }

    private fun getMarkFromTestCase(testCase: JsonObject, key: String): String =
        testCase.string(key)!!.split("\n").joinToString("")

    private fun ticketFromTestMark(mark: String): Ticket =
        Ticket.createTicketFromArray(BooleanArray(LENGTH) { mark[it] == '1' })
}