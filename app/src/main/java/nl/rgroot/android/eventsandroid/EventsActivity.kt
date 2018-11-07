package nl.rgroot.android.eventsandroid

import android.arch.lifecycle.LiveData
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import kotlinx.android.synthetic.main.activity_events.*
import nl.rgroot.android.eventsandroid.models.Event
import nl.rgroot.android.eventsandroid.database.AppDatabase
import nl.rgroot.android.eventsandroid.views.BlankFragment
import nl.rgroot.android.eventsandroid.views.EventFragment

typealias LiveEventList = LiveData<List<Event>>

class EventsActivity : FragmentActivity(),
    EventFragment.OnListFragmentInteractionListener,
    BlankFragment.OnFragmentInteractionListener
{
    // Fragments in this activity
    val eventsFragment: Fragment = EventFragment()
    val tmpFragment: Fragment    = BlankFragment()

    val fm: FragmentManager       = supportFragmentManager
    var currentFragment: Fragment = eventsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Layout
        setContentView(R.layout.activity_events)

        // Fragments (hide all but last)
        fm.beginTransaction().add(R.id.main_container, tmpFragment, "tmp").hide(tmpFragment).commit()
        fm.beginTransaction().add(R.id.main_container, eventsFragment, "events").commit()

        // Event listeners
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_events -> {
                fm.beginTransaction().hide(currentFragment).show(eventsFragment).commit()
                currentFragment = eventsFragment
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_settings -> {
                fm.beginTransaction().hide(currentFragment).show(tmpFragment).commit()
                currentFragment = tmpFragment
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onListFragmentInteraction(item: Event?) {
        TODO("not implemented")
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented")
    }

}
