package nl.rgroot.android.eventsandroid

import android.arch.persistence.room.Room
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import kotlinx.android.synthetic.main.activity_events.*

class EventsActivity : FragmentActivity(),
    EventFragment.OnListFragmentInteractionListener,
    BlankFragment.OnFragmentInteractionListener
{
    companion object {
        var db: AppDatabase? = null
    }

    // Fragments in this activity
    val eventsFragment: Fragment = EventFragment()
    val tmpFragment: Fragment = BlankFragment()

    val fm: FragmentManager = supportFragmentManager
    var currentFragment: Fragment =  eventsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Database init
        db = Room.databaseBuilder(this, AppDatabase::class.java, "app_db").build()

        // Layout
        setContentView(R.layout.activity_events)

        // Fragments
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
