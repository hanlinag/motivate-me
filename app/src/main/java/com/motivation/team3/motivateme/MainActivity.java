package com.motivation.team3.motivateme;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.motivation.team3.motivateme.activity.AboutActivity;
import com.motivation.team3.motivateme.activity.HelpActivity;
import com.motivation.team3.motivateme.fragment.HomeFragment;
import com.motivation.team3.motivateme.fragment.NotesFragment;
import com.motivation.team3.motivateme.fragment.QuotesFragment;
import com.motivation.team3.motivateme.fragment.SongFragment;
import com.motivation.team3.motivateme.fragment.TellBeadsFragment;
import com.motivation.team3.motivateme.fragment.ToDoListFragment;
import com.motivation.team3.motivateme.listener.FABOnClickListener;
import com.readystatesoftware.systembartint.SystemBarTintManager;


public class MainActivity extends AppCompatActivity {
    private static MainActivity ourInstance;

    private static void setInstance(MainActivity instance) {
        ourInstance = instance;
    }

    public static MainActivity getInstance() {
        return ourInstance;
    }

    private CollapsingToolbarLayout collapsingToolbar;
    public Object[] floatingActionButtonList;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private TextView txtName;
    private Toolbar toolbar;
    public static int navItemIndex = 0;
    private static final String TAG_HOME = "Home";
    private static final String TAG_NOTES = "notes";
    private static final String TAG_TODOLIST= "todolist";
    private static final String TAG_QUOTES = "quotes";
    private static final String TAG_SONG = "song";
    private static final String TAG_BREATH = "breath";
    public static String CURRENT_TAG = TAG_HOME;
    private String[] activityTitles;
    private Handler mHandler;

    private boolean doubleBackToExitPressedOnce = false;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInstance(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        floatingActionButtonList = new Object[5];
        for (int i = 0; i < floatingActionButtonList.length; i++) {
            floatingActionButtonList[i] = null;
        }
        initCollapsingToolbar();

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintColor(getResources().getColor(R.color.colorPrimaryDark));

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);

        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        loadNavHeader();

        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }

        com.github.clans.fab.FloatingActionButton note_fab = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_note);
        note_fab.setBackgroundColor(Color.parseColor("#448AFF"));
        note_fab.setOnClickListener(new FABOnClickListener());

        com.github.clans.fab.FloatingActionButton fab = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_todolist);
        fab.setOnClickListener(new FABOnClickListener());

        com.github.clans.fab.FloatingActionMenu floating_menu_home = (com.github.clans.fab.FloatingActionMenu) findViewById(R.id.floating_menu_home);
        com.github.clans.fab.FloatingActionButton floatingActionButton = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_addnote);
        com.github.clans.fab.FloatingActionButton floatingNoteActionButton = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.floating_addtodolist);
        floatingActionButton.setOnClickListener(new FABOnClickListener());
        floatingNoteActionButton.setOnClickListener(new FABOnClickListener());

        floatingActionButtonList[0] = floating_menu_home;
        floatingActionButtonList[2] = note_fab;
        floatingActionButtonList[1] = fab;
        setupPageDetectableFAB();
    }
    public void onBackPressed()
    {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press one more time to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    private void initCollapsingToolbar() {
        collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener()
        {
            boolean isShow = false;
            int scrollRange = -1;

            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset)
            {
                collapsingToolbar.setTitle(activityTitles[navItemIndex]);

            }
        });

    }
    private void loadNavHeader() {
        txtName.setText("Motivate Me");
    }
    private void loadHomeFragment() {

        selectNavMenu();
        setToolbarTitle();

        Runnable mPendingRunnable = new Runnable() {

            public void run() {
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commit();
            }
        };

        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
        drawer.closeDrawers();
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                ToDoListFragment todolistFragment = new ToDoListFragment();
                return todolistFragment;
            case 2:
                NotesFragment noteFragment = new NotesFragment();
                return noteFragment;
            case 3:
                QuotesFragment quotesFragment=new QuotesFragment();
                return  quotesFragment;
            case 4:
                SongFragment songFragment=new SongFragment();
                return songFragment;
            case 5:
                TellBeadsFragment breathFragment=new TellBeadsFragment();
                return breathFragment;
            default:
                return new HomeFragment();
        }
    }

    public void setupPageDetectableFAB() {
        for (int i = 0; i < floatingActionButtonList.length; i++) {
            if (floatingActionButtonList[i] != null && i != navItemIndex) {
                if (floatingActionButtonList[i] instanceof com.github.clans.fab.FloatingActionButton)
                    ((com.github.clans.fab.FloatingActionButton) floatingActionButtonList[i]).hide(true);
                else if(floatingActionButtonList[i] instanceof  com.github.clans.fab.FloatingActionMenu)
                    ((com.github.clans.fab.FloatingActionMenu) floatingActionButtonList[i]).hideMenu(true);
            } else if(floatingActionButtonList[i] != null && i == navItemIndex) {
                if (floatingActionButtonList[i] instanceof com.github.clans.fab.FloatingActionButton)
                    ((com.github.clans.fab.FloatingActionButton) floatingActionButtonList[i]).show(true);
                else if(floatingActionButtonList[i] instanceof  com.github.clans.fab.FloatingActionMenu)
                    ((com.github.clans.fab.FloatingActionMenu) floatingActionButtonList[i]).showMenu(true);
            }
        }
    }
    private void setToolbarTitle() {
        Log.i("Title : ",activityTitles[navItemIndex]);
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_todolist:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_TODOLIST;
                        break;
                    case R.id.nav_notes:
                        getSupportActionBar().setTitle("Notes");
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_NOTES;
                        break;
                    case R.id.nav_quote:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_QUOTES;
                        break;
                    case R.id.nav_song:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_SONG;
                        break;
                    case R.id.nav_breath:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_BREATH;
                        break;
                    default:
                        navItemIndex = 0;
                }

                setupPageDetectableFAB();
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
            }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(item.getTitle().equals("Contact Us"))
        {
            try{
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.facebook.com/motivatemeappmyanmar/"));
                startActivity(intent);
            }
            catch (ActivityNotFoundException e)
            {
                Toast.makeText(this,"No application can handle this request. Please in stall a web browser.  " , Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
        else if (item.getTitle().equals("Settings"))
        {
            Toast.makeText(getApplicationContext(),"Next Feature",Toast.LENGTH_SHORT).show();
        }
        else if (item.getTitle().equals("About"))
        {
            Intent inAbout = new Intent(this, AboutActivity.class);
            startActivity(inAbout);
        }
        else if(item.getTitle().equals("Help")){
            Intent inhelp = new Intent(this, HelpActivity.class);
            startActivity(inhelp);
        }
        return super.onOptionsItemSelected(item);
    }
}