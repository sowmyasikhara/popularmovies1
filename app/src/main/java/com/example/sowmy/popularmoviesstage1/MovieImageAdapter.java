package com.example.sowmy.popularmoviesstage1;


import android.content.Context;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

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
    private String[] movie_images;
    final static String LOG_TAG ="MovieImageAdapter";

    // Constructor
    public MovieImageAdapter(Context c,ArrayList<Movie> poster_paths) {
        Log.d(LOG_TAG,"Constructor called");


        mContext = c;
        poster_images = poster_paths;

        movie_images = new String[poster_images.size()];

        for (int i = 0; i < poster_images.size(); i++) {
            poster = poster_images.get(i).getMovie_poster();
            poster = path + poster;
            movie_images[i]=poster;
            Log.d("poster adapter",poster);
        }

    }


    public int getCount()
    {
        Log.d(LOG_TAG,String.valueOf(poster_images.size()));
        return poster_images.size();
    }

    public Object getItem(int position)
    {
        Log.d(LOG_TAG,String.valueOf(position));
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

        }
        else
        {
            imageView = (ImageView) convertView;

        }

            Picasso.get()

                    .load(movie_images[position])
                    .error(R.drawable.picture_unavailable)
                    .placeholder(R.drawable.loading_icon1)
                    .into(imageView);

        return imageView;
    }

   }
