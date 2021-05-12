package com.motivation.team3.motivateme

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.github.clans.fab.FloatingActionButton
import com.github.clans.fab.FloatingActionMenu
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.navigation.NavigationView
import com.motivation.team3.motivateme.activity.AboutActivity
import com.motivation.team3.motivateme.activity.HelpActivity
import com.motivation.team3.motivateme.fragment.*
import com.motivation.team3.motivateme.listener.FABOnClickListener
import com.readystatesoftware.systembartint.SystemBarTintManager

class MainActivity : AppCompatActivity() {
    private var collapsingToolbar: CollapsingToolbarLayout? = null
    private lateinit var floatingActionButtonList: Array<Any?>
    private var navigationView: NavigationView? = null
    private var drawer: DrawerLayout? = null
    private var navHeader: View? = null
    private var txtName: TextView? = null
    private var toolbar: Toolbar? = null
    private lateinit var activityTitles: Array<String>
    private var mHandler: Handler? = null
    private var doubleBackToExitPressedOnce = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setInstance(this)
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        floatingActionButtonList = arrayOfNulls(5)
        for (i in floatingActionButtonList.indices) {
            floatingActionButtonList[i] = null
        }
        initCollapsingToolbar()
        val tintManager = SystemBarTintManager(this)
        tintManager.isStatusBarTintEnabled = true
        tintManager.setNavigationBarTintEnabled(true)
        tintManager.setTintColor(resources.getColor(R.color.colorPrimaryDark))
        mHandler = Handler()
        drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        navigationView = findViewById(R.id.nav_view) as NavigationView
        navHeader = navigationView!!.getHeaderView(0)
        txtName = navHeader?.findViewById<View>(R.id.name) as TextView
        activityTitles = resources.getStringArray(R.array.nav_item_activity_titles)
        loadNavHeader()
        setUpNavigationView()
        if (savedInstanceState == null) {
            navItemIndex = 0
            CURRENT_TAG = TAG_HOME
            loadHomeFragment()
        }
        val note_fab = findViewById(R.id.floating_note) as FloatingActionButton
        note_fab.setBackgroundColor(Color.parseColor("#448AFF"))
        note_fab.setOnClickListener(FABOnClickListener())
        val fab = findViewById(R.id.floating_todolist) as FloatingActionButton
        fab.setOnClickListener(FABOnClickListener())
        val floating_menu_home = findViewById(R.id.floating_menu_home) as FloatingActionMenu
        val floatingActionButton = findViewById(R.id.floating_addnote) as FloatingActionButton
        val floatingNoteActionButton =
            findViewById(R.id.floating_addtodolist) as FloatingActionButton
        floatingActionButton.setOnClickListener(FABOnClickListener())
        floatingNoteActionButton.setOnClickListener(FABOnClickListener())
        floatingActionButtonList[0] = floating_menu_home
        floatingActionButtonList[2] = note_fab
        floatingActionButtonList[1] = fab
        setupPageDetectableFAB()
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Press one more time to exit", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    private fun initCollapsingToolbar() {
        collapsingToolbar = findViewById(R.id.collapsing_toolbar) as CollapsingToolbarLayout
        collapsingToolbar!!.title = " "
        val appBarLayout = findViewById(R.id.appbar) as AppBarLayout
        appBarLayout.setExpanded(true)
        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                collapsingToolbar!!.title = activityTitles[navItemIndex]
            }
        })
    }

    private fun loadNavHeader() {
        txtName!!.text = "Motivate Me"
    }

    private fun loadHomeFragment() {
        selectNavMenu()
        setToolbarTitle()
        val mPendingRunnable = Runnable {
            val fragment = homeFragment
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG)
            fragmentTransaction.commit()
        }
        if (mPendingRunnable != null) {
            mHandler!!.post(mPendingRunnable)
        }
        drawer!!.closeDrawers()
        invalidateOptionsMenu()
    }

    private val homeFragment: Fragment
        private get() = when (navItemIndex) {
            0 -> {
                HomeFragment()
            }
            1 -> {
                ToDoListFragment()
            }
            2 -> {
                NotesFragment()
            }
            3 -> {
                QuotesFragment()
            }
            4 -> {
                SongFragment()
            }
            5 -> {
                TellBeadsFragment()
            }
            else -> HomeFragment()
        }

    fun setupPageDetectableFAB() {
        for (i in floatingActionButtonList.indices) {
            if (floatingActionButtonList[i] != null && i != navItemIndex) {
                if (floatingActionButtonList[i] is FloatingActionButton) (floatingActionButtonList[i] as FloatingActionButton?)!!.hide(
                    true
                ) else if (floatingActionButtonList[i] is FloatingActionMenu) (floatingActionButtonList[i] as FloatingActionMenu?)!!.hideMenu(
                    true
                )
            } else if (floatingActionButtonList[i] != null && i == navItemIndex) {
                if (floatingActionButtonList[i] is FloatingActionButton) (floatingActionButtonList[i] as FloatingActionButton?)!!.show(
                    true
                ) else if (floatingActionButtonList[i] is FloatingActionMenu) (floatingActionButtonList[i] as FloatingActionMenu?)!!.showMenu(
                    true
                )
            }
        }
    }

    private fun setToolbarTitle() {
        Log.i("Title : ", activityTitles[navItemIndex])
        supportActionBar!!.title = activityTitles[navItemIndex]
    }

    private fun selectNavMenu() {
        navigationView!!.menu.getItem(navItemIndex).isChecked = true
    }

    private fun setUpNavigationView() {
        navigationView!!.setNavigationItemSelectedListener { menuItem ->

            // This method will trigger on item Click of navigation menu
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    navItemIndex = 0
                    CURRENT_TAG = TAG_HOME
                }
                R.id.nav_todolist -> {
                    navItemIndex = 1
                    CURRENT_TAG = TAG_TODOLIST
                }
                R.id.nav_notes -> {
                    supportActionBar!!.title = "Notes"
                    navItemIndex = 2
                    CURRENT_TAG = TAG_NOTES
                }
                R.id.nav_quote -> {
                    navItemIndex = 3
                    CURRENT_TAG = TAG_QUOTES
                }
                R.id.nav_song -> {
                    navItemIndex = 4
                    CURRENT_TAG = TAG_SONG
                }
                R.id.nav_breath -> {
                    navItemIndex = 5
                    CURRENT_TAG = TAG_BREATH
                }
                else -> navItemIndex = 0
            }
            setupPageDetectableFAB()
            if (menuItem.isChecked) {
                menuItem.isChecked = false
            } else {
                menuItem.isChecked = true
            }
            menuItem.isChecked = true
            loadHomeFragment()
            true
        }
        val actionBarDrawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.openDrawer,
            R.string.closeDrawer
        ) {
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
            }
        }
        drawer!!.setDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (item.title == "Contact Us") {
            try {
                val intent =
                    Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.facebook.com/motivatemeappmyanmar/"))
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    this,
                    "No application can handle this request. Please in stall a web browser.  ",
                    Toast.LENGTH_LONG
                ).show()
                e.printStackTrace()
            }
        } else if (item.title == "Settings") {
            Toast.makeText(applicationContext, "Next Feature", Toast.LENGTH_SHORT).show()
        } else if (item.title == "About") {
            val inAbout = Intent(this, AboutActivity::class.java)
            startActivity(inAbout)
        } else if (item.title == "Help") {
            val inhelp = Intent(this, HelpActivity::class.java)
            startActivity(inhelp)
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        var instance: MainActivity? = null

        @JvmName("setInstance1")
        private fun setInstance(instance: MainActivity) {
            Companion.instance = instance
        }

        var navItemIndex = 0
        private const val TAG_HOME = "Home"
        private const val TAG_NOTES = "notes"
        private const val TAG_TODOLIST = "todolist"
        private const val TAG_QUOTES = "quotes"
        private const val TAG_SONG = "song"
        private const val TAG_BREATH = "breath"
        var CURRENT_TAG = TAG_HOME
    }
}