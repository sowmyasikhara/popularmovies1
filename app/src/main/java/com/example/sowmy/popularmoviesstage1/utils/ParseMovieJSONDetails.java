package com.example.sowmy.popularmoviesstage1.utils;

import android.util.Log;


import com.example.sowmy.popularmoviesstage1.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParseMovieJSONDetails {

    private static ArrayList<Object> poster_paths;
    String[] movieList;
    static Movie movieObj;
    static String poster_path;
    ArrayList<Movie> allMoviesList;
    final static String LOG_TAG="ParseJSONData";

    public ArrayList<Movie> getAllMoviesList(String movieDbresponse) {
        int indexOfMovie =0;
        try {
            Log.d(LOG_TAG, movieDbresponse);
            JSONObject movieJSON = new JSONObject(movieDbresponse);
            JSONArray resultsJSON;

            allMoviesList = new ArrayList<Movie>();

            resultsJSON = movieJSON.getJSONArray("results");
            Log.d(LOG_TAG, resultsJSON.toString());

            for (int i = 0; i < resultsJSON.length(); i++) {
                //title
                JSONObject jsonObj = resultsJSON.optJSONObject(i);
                //allMoviesList.add(jsonObj);

                String title = jsonObj.getString("title");

                String release_date = jsonObj.getString("release_date");

                String poster_path = jsonObj.getString("poster_path");

                String vote_average = jsonObj.getString("vote_average");

                String plot_synopsis = jsonObj.getString("overview");


                movieObj = new Movie(title,release_date,poster_path,vote_average,plot_synopsis);

                movieObj.setTitle(title);
                movieObj.setRelease_date(release_date);
                movieObj.setMovie_poster(poster_path);
                movieObj.setVote_average(vote_average);
                movieObj.setPlot_synopsis(plot_synopsis);



                allMoviesList.add(movieObj);
                Log.d(LOG_TAG,allMoviesList.toString());

                indexOfMovie++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return allMoviesList;
    }

}
