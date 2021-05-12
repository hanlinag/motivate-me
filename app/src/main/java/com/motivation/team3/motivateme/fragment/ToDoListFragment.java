package com.motivation.team3.motivateme.fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.motivation.team3.motivateme.R;
import com.motivation.team3.motivateme.activity.UpdateToDoList;
import com.motivation.team3.motivateme.adapter.CustomToDoListAdapter;
import com.motivation.team3.motivateme.database.TaskDbHelper;
import com.motivation.team3.motivateme.listener.RecyclerItemClickListener;
import com.motivation.team3.motivateme.model.Contact;
import com.motivation.team3.motivateme.service.AlarmReceiver;

public class ToDoListFragment extends android.support.v4.app.Fragment
{
    private TaskDbHelper db;
    CustomToDoListAdapter customAdapter;
    private RecyclerView recyclerView;
    boolean click=false;
    public ToDoListFragment(){}

    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.todolist_layout, container, false);
        super.onCreate(savedInstanceState);

        db = new TaskDbHelper(getActivity());

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        customAdapter = new CustomToDoListAdapter((getActivity()), db.getAllData());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(customAdapter);

        onResume();
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position)
            {
                Contact contract = db.getData(position);
                Intent in = new Intent(getActivity(),UpdateToDoList.class);
                in.putExtra("ID", String.valueOf(contract.getId()));
                in.putExtra("COUNT",String.valueOf(contract.getCount()));
                in.putExtra("NAME", contract.getTitle().toString());
                in.putExtra("BODY", contract.getBody().toString());
                in.putExtra("TIME",contract.getTime().toString());
                in.putExtra("DATE",contract.getDate().toString());

                startActivity(in);
            }

        }));

        ItemTouchHelper.SimpleCallback simpleItemTouchCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT)
        {
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target)
            {
                final int fromPos=viewHolder.getAdapterPosition();
                final int toPos=viewHolder.getAdapterPosition();
                moveItem(fromPos,toPos);
                return true;
            }

            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction)
            {
                deleteItem(viewHolder.getPosition());
                onResume();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallBack);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        return view;
    }
    private void moveItem(int oldpos,int newpos)
    {
        customAdapter.notifyItemMoved(oldpos,newpos);

    }
    private void deleteItem(int position)
    {
        Contact contact=db.getData(position);
        int id=contact.getId();
        Intent intent = new Intent(getActivity(), AlarmReceiver.class);
        PendingIntent pendingIntent= PendingIntent.getBroadcast(getActivity(), id, intent, 0);
        AlarmManager alarmManager= (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        db.deleteData(String.valueOf(id));
    }

    public void onResume()
    {
        super.onResume();
        new Handler().post(new Runnable(){

            public void run()
            {
                customAdapter = new CustomToDoListAdapter((getActivity()), db.getAllData());
                recyclerView.setAdapter(customAdapter);
            }
        });
    }
}
