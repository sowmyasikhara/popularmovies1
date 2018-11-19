package com.example.sowmy.popularmoviesstage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

@SuppressWarnings("RedundantCast")
public class MovieDetailView extends AppCompatActivity {

    private static TextView title_name;
    private static TextView release_date_movie;
    private static TextView vote_average_movie;
    private static TextView overview_movie;
    private static ImageView poster_movie;
    private static String path="https://image.tmdb.org/t/p/w500";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_view);

        title_name = (TextView)findViewById(R.id.titleNameId);
        release_date_movie = (TextView)findViewById(R.id.releaseDateId);
        vote_average_movie = (TextView)findViewById(R.id.voteAverageId);
        overview_movie = (TextView)findViewById(R.id.plotSynopsisId);
        poster_movie = (ImageView)findViewById(R.id.imageViewId);


        // Get intent data
        Intent i = getIntent();


        String title = i.getExtras().getString("name");
        title_name.setText(title);

        String release_date = i.getExtras().getString("release_date");
        release_date_movie.setText(release_date);

        String vote_average =i.getExtras().getString("vote_average");
        vote_average_movie.setText(vote_average);

        String overview = i.getExtras().getString("overview");
        overview_movie.setText(overview);

        String poster =  i.getExtras().getString("poster_path");
        poster = path+poster;

        Picasso.get()
                .load(poster)
                .error(R.drawable.picture_unavailable)
                .placeholder(R.drawable.loading_icon1)
                .into(poster_movie);

    }
}
