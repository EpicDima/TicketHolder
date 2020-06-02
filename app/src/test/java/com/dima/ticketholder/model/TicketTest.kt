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

    @Test
    fun compareUniqueTicket1() {
        val uniqueTicketA = Ticket.createTicketFromArray(booleanArrayOf(true,false,true,false,
                                                                        true,true,true,false,
                                                                        true,false,true,false))

        val uniqueTicketB = Ticket.createTicketFromArray(booleanArrayOf(false,true,true,true,
                                                                        false,false,true,false,
                                                                        false,true,true,true))
        assertTrue(uniqueTicketA.compare(uniqueTicketB))
    }

    @Test
    fun compareUniqueTicket2() {
        val uniqueTicketA = Ticket.createTicketFromArray(booleanArrayOf(true,false,true,false,
                                                                        true,true,true,false,
                                                                        true,false,true,false))

        val uniqueTicketB = Ticket.createTicketFromArray(booleanArrayOf(true,false,true,false,
                                                                        true,true,true,false,
                                                                        true,false,true,false))
        assertTrue(uniqueTicketA.compare(uniqueTicketB))
    }


    @Test
    fun compareUniqueTicket3() {
        val uniqueTicketA = Ticket.createTicketFromArray(booleanArrayOf(true,false,true,false,
                                                                        true,false,true,false,
                                                                        true,false,true,false))

        val uniqueTicketB = Ticket.createTicketFromArray(booleanArrayOf(true,false,true,false,
                                                                        true,false,true,false,
                                                                        true,false,true,false))
        assertTrue(uniqueTicketA.compare(uniqueTicketB))
    }
}