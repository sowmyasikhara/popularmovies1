package com.example.sowmy.popularmoviesstage1;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.sowmy.popularmoviesstage1.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieImageAdapter extends BaseAdapter  {
    private Context mContext;
    List<Movie> poster_images;
    String path="https://image.tmdb.org/t/p/w500";
    private String poster;
    String[] movie_images;






    // Constructor
    public MovieImageAdapter(Context c,ArrayList<Movie> poster_paths) {
        Log.d("constructor called","adapter");


        mContext = c;
        poster_images = poster_paths;

        movie_images = new String[poster_images.size()];

        //poster_images = new ArrayList<Movie>();

        for (int i = 0; i < poster_images.size(); i++) {
            poster = poster_images.get(i).getMovie_poster();
            poster = path + poster;
            movie_images[i]=poster;
            Log.d("poster adapter",poster);
        }

    }


    public int getCount()
    {
        Log.d("poster images inadapter",String.valueOf(poster_images.size()));
        return poster_images.size();
    }

    public Object getItem(int position)
    {
        Log.d("items position",String.valueOf(position));
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position,  View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(500, 500));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else
        {
            imageView = (ImageView) convertView;

        }

            Picasso.get()
                    .load(movie_images[position])
                    .into(imageView);



        //Picasso.get().load(poster_images.get(position).getMovie_poster()).into(imageView);
        //imageView.setImageResource(Integer.parseInt(poster_images.get(position)));
        return imageView;
    }

   }
