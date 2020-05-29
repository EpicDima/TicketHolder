package com.dima.ticketholder.model

import org.junit.Assert.assertTrue
import org.junit.Test

class TicketTest {

    companion object {
        const val LENGTH = Ticket.ROWS * Ticket.COLS
    }

    @Test
    fun compareCompletedTickets() {
        val a = Ticket.createTicketFromArray(BooleanArray(LENGTH) { true })
        val b = Ticket.createTicketFromArray(BooleanArray(LENGTH) { true })
        assertTrue(a.compare(b))
    }
}