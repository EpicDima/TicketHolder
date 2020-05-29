package com.dima.ticketholder.ui.fragments

import androidx.lifecycle.*
import com.dima.ticketholder.managers.TicketManager
import com.dima.ticketholder.model.Ticket
import com.dima.ticketholder.model.Ticket.Companion.COLS
import com.dima.ticketholder.model.Ticket.Companion.ROWS
import com.dima.ticketholder.model.Ticket.Companion.createTicketFromArray
import com.dima.ticketholder.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val ticketManager: TicketManager) : ViewModel() {

    val tickets: LiveData<List<Ticket>> by lazy { ticketManager.tickets }

    private val _inputState: MutableLiveData<BooleanArray> = MutableLiveData(BooleanArray(ROWS * COLS))

    val inputState: LiveData<BooleanArray>
        get() = _inputState

    private val _inputAllowedEvent = MutableLiveData(false)
    private val _updateInputEvent = MutableLiveData<Unit>()
    private val _createTicketEvent = MutableLiveData<Event<Ticket>>()
    private val _foundTicketEvent = MutableLiveData<Event<Ticket>>()

    val inputAllowedEvent: LiveData<Boolean>
        get() = _inputAllowedEvent

    val updateInputEvent: LiveData<Unit>
        get() = _updateInputEvent

    val createTicketEvent: LiveData<Event<Ticket>>
        get() = _createTicketEvent

    val foundTicketEvent: LiveData<Event<Ticket>>
        get() = _foundTicketEvent

    fun changeState(index: Int, checked: Boolean) {
        _inputState.value?.set(index, checked)
        _inputAllowedEvent.postValue(_inputState.value?.any { it } ?: false)
    }

    private fun insertTicket(ticket: Ticket) = viewModelScope.launch(Dispatchers.IO) {
        ticketManager.insert(ticket)
    }

    private fun updateTicket(ticket: Ticket) = viewModelScope.launch(Dispatchers.IO) {
        ticketManager.update(ticket)
    }

    private fun updateTickets(vararg tickets: Ticket) = viewModelScope.launch(Dispatchers.IO) {
        ticketManager.update(*tickets)
    }

    private fun deleteTicket(ticket: Ticket) = viewModelScope.launch(Dispatchers.IO) {
        ticketManager.delete(ticket)
    }

    fun search() = viewModelScope.launch(Dispatchers.Default) {
        val inputTicket = createTicketFromArray(_inputState.value!!)
        val foundTicket = ticketManager.compare(inputTicket)
        if (foundTicket.id == 0) {
            _createTicketEvent.postValue(Event(inputTicket))
        } else {
            foundTicket.usages += 1
            updateTicket(foundTicket)
            _foundTicketEvent.postValue(Event(foundTicket))
        }
    }

    fun insert(ticket: Ticket) = viewModelScope.launch(Dispatchers.Default) {
        tickets.value?.let { list ->
            ticket.number = (list.maxBy { it.number }?.number ?: 0) + 1
        }
        insertTicket(ticket).join()
        reset()
    }

    fun reset() {
        _inputState.value!!.fill(false)
        _updateInputEvent.postValue(Unit)
    }

    fun delete(ticket: Ticket) = viewModelScope.launch(Dispatchers.IO) {
        deleteTicket(ticket).join()
        updateTickets(*(ticketManager.select().mapIndexed { index, t ->
            t.number = index + 1
            t
        }.toTypedArray()))
    }
}
