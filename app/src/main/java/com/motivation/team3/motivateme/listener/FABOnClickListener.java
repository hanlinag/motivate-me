package com.motivation.team3.motivateme.listener;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.View;
import com.motivation.team3.motivateme.MainActivity;
import com.motivation.team3.motivateme.R;
import com.motivation.team3.motivateme.activity.AddNewToDoList;
import com.motivation.team3.motivateme.activity.AddNote;

public class FABOnClickListener implements View.OnClickListener {
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.floating_todolist:
                floatingToDoListOnClick(view);
                break;
            case R.id.floating_menu_home:
                floatingHomeOnClick(view);
                break;
            case R.id.floating_addtodolist:
                floatingToDoListOnClick(view);
                break;
            case R.id.floating_addnote:
                floatingNote(view);
                break;
            case R.id.floating_note:
                floatingNote(view);
                break;
        }
    }

    private void floatingHomeOnClick(View view)
    {
        Snackbar.make(view, "FH", Snackbar.LENGTH_INDEFINITE).show();

    }

    private void floatingToDoListOnClick(View view)
    {
        Intent intent=new Intent(MainActivity.getInstance().getApplicationContext() ,AddNewToDoList.class);
        MainActivity.getInstance().startActivity(intent);
    }

    private void floatingNote(View view)
    {
        Intent intent=new Intent(MainActivity.getInstance().getApplicationContext() ,AddNote.class);
        MainActivity.getInstance().startActivity(intent);
    }
}
