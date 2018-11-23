package com.example.sowmy.popularmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sowmy.popularmoviesstage1.model.Movie;
import com.example.sowmy.popularmoviesstage1.utils.MovieNetworkUtils;
import com.example.sowmy.popularmoviesstage1.utils.ParseMovieJSONDetails;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView imageview;
    private MovieImageAdapter mAdapter;
    private GridView gridview;


    Context context;
    Movie obj;

    ArrayList<Movie> allMoviesList;
    private static String response1;
    private String sortByValue;
    public String sortByPref;
    final static String LOG_TAG ="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridview = (GridView) findViewById(R.id.gridview);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        context = MainActivity.this;
        int itemThatWasClickedId = item.getItemId();

        if (itemThatWasClickedId == R.id.popular_movie) {
            sortByPref=getString(R.string.popular);
            //sortByValue = String.valueOf(R.string.sort_by_popularity);
            sortByValue=getString(R.string.sort_by_popularity);
            Log.d(LOG_TAG, sortByValue);

            String textToShow = "showing popular movies";
            Toast.makeText(context, textToShow, Toast.LENGTH_LONG).show();
            getMovieDetails(sortByValue,sortByPref);

        } else {
            if (itemThatWasClickedId == R.id.top_rated) {

                sortByPref=getString(R.string.top_rated);
                sortByValue = String.valueOf(R.string.sort_by_topRated);
                Log.d(LOG_TAG, sortByValue);
                String textToShow = "showing top rated movies";
                Toast.makeText(context, textToShow, Toast.LENGTH_LONG).show();
                getMovieDetails(sortByValue,sortByPref);
            }
            return true;
        }


        return super.onOptionsItemSelected(item);


    }

    @Override
    protected void onStart() {

        //default loading popular movies
        sortByPref = getString(R.string.popular);

        //checking if internet connection is available or not
        if(isNetworkAvailable()) {
            //getting list of movie details
            getMovieDetails(sortByValue,sortByPref);

        }else{
            Toast.makeText(context,"Oops,No Internet!Please check Internet Connection",Toast.LENGTH_SHORT).show();
        }
        super.onStart();
    }

    private void getMovieDetails(String sortByValue,String sortByPref) {

        //checking if internet connection is available or not
        if(isNetworkAvailable()) {
            String sortBy = sortByValue;
            URL movieDBSearchURL = MovieNetworkUtils.buildUrl(sortBy,sortByPref);
            new MoviesAsyncTask().execute(movieDBSearchURL);
        }
        else
        {
            Toast.makeText(context,"Oops,No Internet!Please check Internet Connection",Toast.LENGTH_SHORT).show();

        }
    }


    public class MoviesAsyncTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];

            try {
                response1 = MovieNetworkUtils.getResponseFromHttpUrl(searchUrl);

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
        mAdapter = new MovieImageAdapter(MainActivity.this, allMoviesList);
        gridview.setAdapter(mAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id) {
                // Send intent to MovieViewActivity

                LayoutInflater layout = getLayoutInflater();
                v = layout.inflate(R.layout.activity_movie_detail_view, null, false);
                Intent i = new Intent(MainActivity.this, MovieDetailView.class);

                // Pass image index

                String title = allMoviesList.get(position).getTitle();
                i.putExtra("name", title);

                String release_date = allMoviesList.get(position).getRelease_date();
                i.putExtra("release_date", release_date);

                String vote_average = allMoviesList.get(position).getVote_average();
                i.putExtra("vote_average", vote_average);

                String overview = allMoviesList.get(position).getPlot_synopsis();
                i.putExtra("overview", overview);

                String poster_path = allMoviesList.get(position).getMovie_poster();
                i.putExtra("poster_path", poster_path);

                startActivity(i);
            }
        });


    }

    private boolean isNetworkAvailable() {
        context = MainActivity.this;
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetworkInfo != null && activeNetworkInfo.isConnected());
    }
}



