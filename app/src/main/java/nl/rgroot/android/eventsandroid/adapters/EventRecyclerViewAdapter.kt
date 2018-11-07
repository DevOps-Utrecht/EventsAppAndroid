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
    private val inflater         : LayoutInflater,
    var events                   : List<Event>
) : RecyclerView.Adapter<EventRecyclerViewAdapter.EventViewHolder>() {

    private val onClickListener: View.OnClickListener

    constructor(fragment_listener: OnListFragmentInteractionListener?, inflater: LayoutInflater) :
            this(fragment_listener, inflater, emptyList())

    init {
        onClickListener = View.OnClickListener { v ->
            // Notify the active callbacks interface
            val event = v.tag as Event
            fragment_listener?.onListFragmentInteraction(event)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = inflater.inflate(R.layout.fragment_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event               = events[position]
        holder.idView.text      = event.title
        holder.contentView.text = event.descr

        holder.view.run {
            tag = event
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount(): Int = events.size

    /**
     * [RecyclerView.ViewHolder] for the [EventRecyclerViewAdapter]. Couples the layout ids to properties.
     */
    inner class EventViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView      = view.item_number
        val contentView: TextView = view.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}
