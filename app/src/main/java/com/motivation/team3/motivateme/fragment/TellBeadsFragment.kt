package com.motivation.team3.motivateme.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.motivation.team3.motivateme.R;

public class TellBeadsFragment extends Fragment
{
    TextView text,countText;

    public TellBeadsFragment() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.tell_beads_layout, container, false);

        Button btClick = (Button)rootView.findViewById(R.id.btClick);
        Button btReset = (Button)rootView.findViewById(R.id.btReset);
        text= (TextView)rootView.findViewById(R.id.tvclickcount);
        countText=(TextView)rootView.findViewById(R.id.tvroundcount);

        btClick.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String num = text.getText().toString();
                int intNum = Integer.parseInt(num);

                intNum++;
                text.setText(String.valueOf(intNum));

                if (intNum ==108){
                    String countNumber= countText.getText().toString().trim();
                    int intcountNumber = Integer.parseInt(countNumber);

                    intcountNumber++;
                    countText.setText(String.valueOf(intcountNumber));
                    text.setText("0");
                }
            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AppCompatAlertDialogStyle);
                builder.setTitle("Confirm");
                builder.setMessage("Are you sure want to reset?");
                builder.setPositiveButton("OK", new Reset());
                builder.setNegativeButton("Cancel", null);
                builder.show();
            }
        });

        return rootView;
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onDetach() {
        super.onDetach();
    }

    private class Reset implements DialogInterface.OnClickListener {

        public void onClick(DialogInterface dialog, int which) {
            text.setText("0");
            countText.setText("0");
        }
    }
}

