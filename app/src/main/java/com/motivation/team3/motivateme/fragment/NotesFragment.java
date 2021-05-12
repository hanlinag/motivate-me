package com.motivation.team3.motivateme.fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.motivation.team3.motivateme.R;
import com.motivation.team3.motivateme.activity.UpDateNote;
import com.motivation.team3.motivateme.adapter.CustomNoteAdapter;
import com.motivation.team3.motivateme.database.TaskDbHelper;
import com.motivation.team3.motivateme.listener.RecyclerItemClickListener;
import com.motivation.team3.motivateme.model.Note;

public class NotesFragment extends android.support.v4.app.Fragment
{
    private TaskDbHelper db;
    CustomNoteAdapter customNoteAdapter;
    private RecyclerView recyclerView;
    public NotesFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.notes_layout, container, false);
        super.onCreate(savedInstanceState);

        db = new TaskDbHelper(getActivity());

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_node);
        customNoteAdapter = new CustomNoteAdapter((getActivity()), db.getAllNoteData());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new NotesFragment.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(customNoteAdapter);
        onResume();

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position)
            {
                Note note = db.getNoteData(position);
                Intent in = new Intent(getActivity(),UpDateNote.class);
                in.putExtra("ID", String.valueOf(note.getId()));
                in.putExtra("TITLE", note.getTitle());
                in.putExtra("BODY",note.getBody());
                in.putExtra("TIME",note.getTime());
                in.putExtra("DATE",note.getDate());
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
        customNoteAdapter.notifyItemMoved(oldpos,newpos);
    }
    private void deleteItem(int position)
    {
        Note note=db.getNoteData(position);
        int id=note.getId();
        db.deleteNoteData(id);
    }

    public void onResume()
    {
        super.onResume();
        new Handler().post(new Runnable(){

            @Override
            public void run()
            {
                customNoteAdapter = new CustomNoteAdapter((getActivity()), db.getAllNoteData());
                recyclerView.setAdapter(customNoteAdapter);
            }
        });
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing;
                }
            }
        }
    }
    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
