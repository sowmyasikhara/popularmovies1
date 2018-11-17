package com.example.sowmy.popularmoviesstage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieDetailView extends AppCompatActivity {



    TextView titlename;
    TextView release_date_movie;
    TextView vote_average_movie;
    TextView overview_movie;
    ImageView poster_movie;
    String path="https://image.tmdb.org/t/p/w500";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_view);




        titlename = (TextView)findViewById(R.id.titlename);
        release_date_movie = (TextView)findViewById(R.id.release_date);
        vote_average_movie = (TextView)findViewById(R.id.vote_average);
        overview_movie = (TextView)findViewById(R.id.overview);
        poster_movie = (ImageView)findViewById(R.id.imageViewId);


        // Get intent data
        Intent i = getIntent();
        Log.d("intent CALLED","in moviedetail");

        // Selected image id
       // int position = i.getExtras().getInt("id");

        String title = i.getExtras().getString("name");
        titlename.setText(title);

        String release_date = i.getExtras().getString("release_date");
        release_date_movie.setText(release_date);

        String vote_average =i.getExtras().getString("vote_average");
        vote_average_movie.setText(vote_average);

        String overview = i.getExtras().getString("overview");
        overview_movie.setText(overview);

        String poster =  i.getExtras().getString("poster_path");
        poster = path+poster;

        Picasso.get().load(poster).into(poster_movie);













    }







}
