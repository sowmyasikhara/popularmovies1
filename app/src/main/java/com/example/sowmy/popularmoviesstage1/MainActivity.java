package com.example.sowmy.popularmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sowmy.popularmoviesstage1.model.Movie;
import com.example.sowmy.popularmoviesstage1.utils.MovieNetworkUtils;
import com.example.sowmy.popularmoviesstage1.utils.ParseMovieJSONDetails;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private ImageView imageview;
    private MovieImageAdapter mAdapter;
    private GridView gridview;



    Context context ;
    Movie obj;

    ArrayList<Movie> allMoviesList;
    String response1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageview = (ImageView) findViewById(R.id.imageview);

        gridview = (GridView) findViewById(R.id.gridview);



    }
    @Override
    protected void onStart(){

        super.onStart();
        //getting list of movie details
        getMovieDetails();

    }

    private void getMovieDetails(){
        URL movieDBSearchURL = MovieNetworkUtils.buildUrl();
        new MoviesAsyncTask().execute(movieDBSearchURL);
    }



    public class MoviesAsyncTask extends AsyncTask<URL, Void, String> {

            @Override
            protected String doInBackground(URL... params) {
                URL searchUrl = params[0];

                try {
                    response1 = MovieNetworkUtils.getResponseFromHttpUrl(searchUrl);
                    Log.d("response in async task", response1);
                   // loadMovieData(response1);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return response1;
            }
            @Override
            protected void onPostExecute(String response1) {
                if (response1 != null && !response1.equals("")) {
                    loadMovieData(response1);

                }
            }
        }


    private void loadMovieData(String response) {

        ParseMovieJSONDetails parse = new ParseMovieJSONDetails();

        allMoviesList = parse.getAllMoviesList(response);
        Log.d("size allmovieslist",String.valueOf(allMoviesList.size()));
        mAdapter = new MovieImageAdapter(MainActivity.this,allMoviesList);
        gridview.setAdapter(mAdapter);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id){
                // Send intent to SingleViewActivity

                 LayoutInflater layout  = getLayoutInflater();
                 v = layout.inflate(R.layout.activity_movie_detail_view,null,false);
                 Intent i = new Intent(MainActivity.this, MovieDetailView.class);

                 // Pass image inde

                  String  title = allMoviesList.get(position).getTitle();
                  i.putExtra("name",title);

                  String release_date = allMoviesList.get(position).getRelease_date();
                  i.putExtra("release_date",release_date);

                  String vote_average = allMoviesList.get(position).getVote_average();
                  i.putExtra("vote_average",vote_average);

                  String overview = allMoviesList.get(position).getPlot_synopsis();
                  Log.d("overview",overview);

                  i.putExtra("overview",overview);

                  String poster_path = allMoviesList.get(position).getMovie_poster();
                  i.putExtra("poster_path",poster_path);




                 Log.d("intent started",String.valueOf(position));
                 Log.d("overviewtitle",overview);
                startActivity(i);
            }
        });






    }

    }




