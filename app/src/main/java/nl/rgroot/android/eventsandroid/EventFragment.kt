package nl.rgroot.android.eventsandroid

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [EventFragment.OnListFragmentInteractionListener] interface.
 */
class EventFragment : Fragment() {

    private var events: ArrayList<Event> = arrayListOf<Event>()

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        events.add(Event("test 1", "Descr test 1"))
        events.add(Event("test 2", "Descr test 2"))

        arguments?.let {
            events = it.getParcelableArrayList(PAR_EVENTS) ?: arrayListOf()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_event_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = EventRecyclerViewAdapter(events, listener)
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener") as Throwable
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
        const val PAR_EVENTS = "events_list"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance() =
            EventFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(PAR_EVENTS, events)
                }
            }
    }
}
