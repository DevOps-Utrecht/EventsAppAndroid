package nl.rgroot.android.eventsandroid.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_event.view.*
import nl.rgroot.android.eventsandroid.views.EventFragment.OnListFragmentInteractionListener
import nl.rgroot.android.eventsandroid.R
import nl.rgroot.android.eventsandroid.models.Event

/**
 * [RecyclerView.Adapter] that can display a [Event] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class EventRecyclerViewAdapter(
    private val fragment_listener: OnListFragmentInteractionListener?,
    private val inflater: LayoutInflater,
    var events: List<Event>
) : RecyclerView.Adapter<EventRecyclerViewAdapter.EventViewHolder>() {

    private val onClickListener: View.OnClickListener

    constructor(fragment_listener: OnListFragmentInteractionListener?, inflater: LayoutInflater) :
            this(fragment_listener, inflater, emptyList())

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Event
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            fragment_listener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = inflater.inflate(R.layout.fragment_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val item = events[position]
        holder.idView.text = item.title
        holder.contentView.text = item.descr

        with(holder.mView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount(): Int = events.size

    inner class EventViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val idView: TextView = mView.item_number
        val contentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}
