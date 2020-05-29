package com.dima.ticketholder.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dima.ticketholder.R
import com.dima.ticketholder.databinding.MainFragmentBinding
import com.dima.ticketholder.model.Ticket
import com.dima.ticketholder.ui.adapters.TicketListAdapter
import com.dima.ticketholder.ui.widgets.RadioGridLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class MainFragment : Fragment(), TicketListAdapter.OnItemClickListener {
    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    private lateinit var ticketListAdapter: TicketListAdapter

    private lateinit var ticketInputGridLayout: RadioGridLayout

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.lifecycleOwner = this

        ticketInputGridLayout = RadioGridLayout(requireContext(),
            viewModel.inputState, viewModel::changeState, Ticket.ROWS, Ticket.COLS)
        binding.inputLayout.addView(ticketInputGridLayout.layout)
        ticketInputGridLayout.update()

        binding.scrollView.fullScroll(View.FOCUS_UP)

        createAdapters()
        setListeners()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setObservers()
    }

    private fun createAdapters() {
        ticketListAdapter = TicketListAdapter(this)
        binding.ticketsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ticketListAdapter
        }
    }

    private fun setListeners() {
        binding.searchButton.setOnClickListener {
            viewModel.search()
        }

        binding.resetButton.setOnClickListener {
            viewModel.reset()
        }
    }

    private fun setObservers() {
        viewModel.tickets.observe(viewLifecycleOwner, Observer { list ->
            ticketListAdapter.submitList(list.map { it.copy() })
        })

        viewModel.createTicketEvent.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { ticket ->
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(getString(R.string.dialog_add_ticket))
                    .setMessage(getString(R.string.dialog_really_add_ticket, ticket.markToString()))
                    .setPositiveButton(getString(R.string.yes)) { _, _ -> viewModel.insert(ticket) }
                    .setNegativeButton(getString(R.string.no)) { dialog, _ ->  dialog.cancel() }
                    .show()
            }
        })

        viewModel.foundTicketEvent.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { ticket ->
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(getString(R.string.dialog_found_ticket))
                    .setMessage(getString(R.string.dialog_found_ticket_detailed, ticket.number, ticket.markToString()))
                    .setPositiveButton(getString(R.string.ok)) { dialog, _ -> dialog.cancel() }
                    .setOnCancelListener { viewModel.reset() }
                    .create()
                    .show()
            }
        })

        viewModel.updateInputEvent.observe(viewLifecycleOwner, Observer {
            ticketInputGridLayout.update()
        })

        viewModel.inputAllowedEvent.observe(viewLifecycleOwner, Observer {
            binding.searchButton.isEnabled = it
            binding.resetButton.isEnabled = it
        })
    }

    override fun onItemClicked(ticket: Ticket) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.dialog_delete_ticket))
            .setMessage(getString(R.string.dialog_really_delete_ticket, ticket.number, ticket.markToString()))
            .setPositiveButton(getString(R.string.yes)) { _, _ -> viewModel.delete(ticket) }
            .setNegativeButton(getString(R.string.no)) { dialog, _ ->  dialog.cancel() }
            .create()
            .show()
    }
}