package com.motivation.team3.motivateme.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.motivation.team3.motivateme.R;
import com.motivation.team3.motivateme.adapter.CustomSongAdapter;
import com.motivation.team3.motivateme.listener.RecyclerItemClickListener;
import com.motivation.team3.motivateme.model.Song;
import java.util.ArrayList;
import java.util.List;

public class SongFragment extends Fragment
{
    private RecyclerView recyclerView;
    private CustomSongAdapter adapter;
    private List<Song> list;

    public SongFragment(){
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.song_layout,container,false);

        recyclerView=(RecyclerView)view.findViewById(R.id.my_cycler_view);

        list=new ArrayList<Song>();
        prepareSong();

        adapter=new CustomSongAdapter(getContext(),list);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position)
            {
                if(position==0)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=xo1VInw-SKc"));
                    startActivity(intent);
                }
                else if(position==1)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=HHP5MKgK0o8"));
                    startActivity(intent);
                }
                else if(position==2)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=j5-yKhDd64s"));
                    startActivity(intent);
                }
                else if(position==3)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=CevxZvSJLk8"));
                    startActivity(intent);
                }
                else if(position==4)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=VBmMU_iwe6U"));
                    startActivity(intent);
                }
                else if(position==5)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=NG2zyeVRcbs"));
                    startActivity(intent);

                }
                else if(position==6)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=GKSRyLdjsPA"));
                    startActivity(intent);

                }
                else if(position==7)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=hT_nvWreIhg&list=PLhGO2bt0EkwvRUioaJMLxrMNhU44lRWg8"));
                    startActivity(intent);

                }
                else if(position==8)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Qt2mbGP6vFI&list=PLhGO2bt0EkwvRUioaJMLxrMNhU44lRWg8&index=8"));
                    startActivity(intent);

                }
                else if(position==9)
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=O1-4u9W-bns&list=PLhGO2bt0EkwvRUioaJMLxrMNhU44lRWg8&index=14"));
                    startActivity(intent);
                }
            }
        }));

        return view;
    }
    public void prepareSong()
    {
        String[] nameArray = {"Fight Song", "Kill EM with kindness", "Not Afraid", "Roar", "Run the World", "The Climb","The Greatest", "Counting Stars", "Another  days in paradise","I won't give up"};

        String[] artist = {"Rachel Platten", "Selena Gomez", "Eminem", "Katy Perry", "Beyonce", "Miley Cyrus", "Sia","OneRepublic", "Phil Collins", "Jason Marz"};

        Integer[] drawableArray =
                {
                        R.drawable.songblah, R.drawable.songblah1, R.drawable.songblah2, R.drawable.songblah3, R.drawable.songblah4, R.drawable.songblah, R.drawable.songblah3,
                         R.drawable.songblah1, R.drawable.songblah2, R.drawable.songblah4
                };

        Song song;
        for (int i =0; i<nameArray.length; i++)
        {
            song = new Song();
            song=new Song(nameArray[i],artist[i],
                    drawableArray[i]);
            list.add(song);
        }
    }
}
