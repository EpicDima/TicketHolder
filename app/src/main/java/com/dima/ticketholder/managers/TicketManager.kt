package com.dima.ticketholder.managers

import androidx.lifecycle.LiveData
import com.dima.ticketholder.model.Ticket
import dao.TicketDao
import javax.inject.Inject

class TicketManager @Inject constructor(
    private val ticketDao: TicketDao
) {

    val tickets: LiveData<List<Ticket>> by lazy { ticketDao.selectLiveData() }

    suspend fun select() = ticketDao.select()

    suspend fun insert(vararg tickets: Ticket) = ticketDao.insert(*tickets)

    suspend fun update(vararg tickets: Ticket) = ticketDao.update(*tickets)

    suspend fun delete(vararg tickets: Ticket) = ticketDao.delete(*tickets)

    fun compare(ticket: Ticket): Ticket {
        tickets.value?.forEach {
            if (ticket.compare(it)) {
                return it
            }
        }
        return Ticket()
    }


//    fun getByNumber(): LiveData<List<Ticket>> = ticketDao.selectByNumber()
//
//    fun getByNumberDescending(): LiveData<List<Ticket>> = ticketDao.selectByNumberDescending()
//
//    fun getByUsages(): LiveData<List<Ticket>> = ticketDao.selectByUsages()
//
//    fun getByUsagesDescending(): LiveData<List<Ticket>> = ticketDao.selectByUsagesDescending()
}