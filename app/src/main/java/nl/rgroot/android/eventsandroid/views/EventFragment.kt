package nl.rgroot.android.eventsandroid.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nl.rgroot.android.eventsandroid.R
import nl.rgroot.android.eventsandroid.adapters.EventRecyclerViewAdapter
import nl.rgroot.android.eventsandroid.models.Event
import nl.rgroot.android.eventsandroid.viewmodels.EventViewModel

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [EventFragment.OnListFragmentInteractionListener] interface.
 */
class EventFragment : Fragment() {
    private var listener               : OnListFragmentInteractionListener? = null
    private lateinit var adapter       : EventRecyclerViewAdapter
    private lateinit var eventViewModel: EventViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Setup recycler view
        adapter = EventRecyclerViewAdapter(listener, LayoutInflater.from(context))

        val view = inflater.inflate(R.layout.fragment_event_list, container, false)
        // Set the view and adapter
        if (view is RecyclerView) {
            view.run {
                layoutManager = LinearLayoutManager(context)
                adapter = this@EventFragment.adapter
            }
        }

        // Setup event view model
        eventViewModel = ViewModelProviders.of(this).get(EventViewModel::class.java)
        eventViewModel.allEvents.observe(this, Observer {
            adapter.events = it!!
        })

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Event?)
    }

    companion object {

        // TODO: Customize parameter argument names
//        const val PAR_EVENTS = "events_list"

        // TODO: Customize parameter initialization
//        @JvmStatic
//        fun newInstance() =
//            EventFragment().apply {
//                arguments = Bundle().apply {
//                    putParcelableArrayList(PAR_EVENTS, events)
//                }
//            }
    }
}
