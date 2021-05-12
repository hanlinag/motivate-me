package com.motivation.team3.motivateme.fragment;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.motivation.team3.motivateme.MainActivity;
import com.motivation.team3.motivateme.R;
import com.motivation.team3.motivateme.adapter.CustomHomeAdapter;
import com.motivation.team3.motivateme.listener.RecyclerItemClickListener;
import com.motivation.team3.motivateme.model.Home;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends android.support.v4.app.Fragment
{
    private String[] activityTitles;
    private CollapsingToolbarLayout collapsingToolbar;
    private RecyclerView recyclerView;
    private CustomHomeAdapter adapter;
    private List<Home> list;
    private static final String TAG_HOME = "Home";

    public HomeFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.song_layout,container,false);

        recyclerView=(RecyclerView)view.findViewById(R.id.my_cycler_view);

        list=new ArrayList<>();
        prepareHome();

        adapter=new CustomHomeAdapter(getContext(),list);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position)
            {
                collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
                if(position==0)
                {
                    ToDoListFragment toDoListFragment = new ToDoListFragment();
                    fragmentTransition(toDoListFragment,position);
                }
                else if(position==1)
                {
                    NotesFragment notesFragment=new NotesFragment();
                    fragmentTransition(notesFragment,position);
                }
                else if(position==2)
                {
                    QuotesFragment quotesFragment=new QuotesFragment();
                    fragmentTransition(quotesFragment,position);
                }
                else if(position==3)
                {
                    SongFragment songFragment=new SongFragment();
                    fragmentTransition(songFragment,position);
                }
                else if(position==4)
                {
                    TellBeadsFragment tellBeadsFragment=new TellBeadsFragment();
                    fragmentTransition(tellBeadsFragment,position);
                }

            }
        }));
        return view;
    }

    public void fragmentTransition(android.support.v4.app.Fragment fragment,int position)
    {
        MainActivity.getInstance().getSupportActionBar().setTitle(activityTitles[position+1]);
        MainActivity.getInstance().navItemIndex = position+1;
        MainActivity.getInstance().setupPageDetectableFAB();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        final FragmentTransaction replace = fragmentTransaction.replace(R.id.frame, fragment, TAG_HOME);
        replace.commit();
    }

    public void prepareHome()
    {
        Integer[] drawableArray =
                {
                      R.drawable.todolist, R.drawable.notes,R.drawable.quotes,R.drawable.songs,R.drawable.tellbeads
                };

        String [] titleHome={"To Do List", "Notes", "Motivational Quotes", "Motivational Songs", "Tell Beads"};

        Home h = null;
        for (int i =0; i<drawableArray.length && i<titleHome.length; i++)
        {
            h=new Home(titleHome[i],drawableArray[i]);
            list.add(h);
        }
    }
}
