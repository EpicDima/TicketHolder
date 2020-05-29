package com.dima.ticketholder.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dima.ticketholder.databinding.ItemTicketBinding
import com.dima.ticketholder.model.Ticket

class TicketListAdapter(private val listener: OnItemClickListener)
    : ListAdapter<Ticket, TicketListAdapter.TicketViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTicketBinding.inflate(inflater, parent, false)
        return TicketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }


    class TicketViewHolder(private val binding: ItemTicketBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(ticket: Ticket, listener: OnItemClickListener) {
            binding.ticket = ticket
            binding.listener = listener
            binding.executePendingBindings()
        }
    }


    interface OnItemClickListener {
        fun onItemClicked(ticket: Ticket)
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Ticket>() {

            override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean
                    = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Ticket, newItem: Ticket): Boolean
                    = oldItem == newItem
        }
    }
}