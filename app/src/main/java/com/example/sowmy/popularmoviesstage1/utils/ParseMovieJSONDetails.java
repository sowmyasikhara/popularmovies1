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

            public String[] getMovieDetails(String movieDbresponse) {

                try {

                    Log.d("moviedbres", movieDbresponse);
                    JSONObject movieJSON = new JSONObject(movieDbresponse);
                    JSONArray resultsJSON;
                    movieObj = new Movie();
                    Log.d("in movie parse", movieJSON.toString());
                    resultsJSON = movieJSON.getJSONArray("results");
                    Log.d("json tag", resultsJSON.toString());

                    for (int i = 0; i < resultsJSON.length(); i++) {
                        //title
                        JSONObject jsonObj = resultsJSON.optJSONObject(i);

                        String title = jsonObj.getString("title");
                        movieObj.setTitle(title);
                        Log.d("title", title);

                        String poster_path = jsonObj.getString("poster_path");
                        movieObj.setMovie_poster(poster_path);

                        Log.d("poster path            ", poster_path);


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return movieList;

            }

    //get the movie poster path list
    public static ArrayList getMoviePosterPathList(String movieDbresponse) {
            poster_paths=new ArrayList<>();
        try {
            Log.d("moviedbres", movieDbresponse);
            JSONObject movieJSON = new JSONObject(movieDbresponse);
            JSONArray resultsJSON;
            movieObj = new Movie();
            Log.d("in movie parse", movieJSON.toString());
            resultsJSON = movieJSON.getJSONArray("results");
            Log.d("json tag", resultsJSON.toString());


            for (int i = 0; i < resultsJSON.length(); i++) {

                JSONObject jsonObj = resultsJSON.optJSONObject(i);

                poster_path = jsonObj.getString("poster_path");
                movieObj.setMovie_poster(poster_path);

                String title = jsonObj.getString("title");
                movieObj.setTitle(title);

                poster_paths.add(poster_path);
                poster_paths.add(title);

                Log.d("tit;e" ,title);
                Log.d("poster path", poster_paths.toString()+"\n");

            }

        } catch (JSONException e) {
            e.printStackTrace();
        } return poster_paths;
    }


    public String[] getAllDetails(String movieDbresponse) {

        ArrayList<Movie>  moviesList = new ArrayList<Movie>();
        String[] moviearrayList = null;
        movieObj = new Movie();

        try {
            Log.d("getall", movieDbresponse);

            JSONObject movieJSON = new JSONObject(movieDbresponse);
            JSONArray resultsJSON;


            Log.d("in movie parse", movieJSON.toString());
            resultsJSON = movieJSON.getJSONArray("results");

            Log.d("json tag", resultsJSON.toString());

            for (int i = 0; i < resultsJSON.length(); i++) {
                //title
                JSONObject jsonObj = resultsJSON.optJSONObject(i);

                String title = jsonObj.getString("title");
                movieObj.setTitle(title);
                Log.d("title", title);

                String poster_path = jsonObj.getString("poster_path");
                Log.d("poster path", poster_path);
                movieObj.setMovie_poster(poster_path);

                String release_date = jsonObj.getString("release_date");
                Log.d("date", release_date);
                movieObj.setRelease_date(release_date);


             }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("length",movieObj.getMovie_poster());
        return moviearrayList ;

    }
    public ArrayList<Movie> getAllMoviesList(String movieDbresponse) {
        int indexOfMovie =0;
        try {
            Log.d("moviedbres", movieDbresponse);
            JSONObject movieJSON = new JSONObject(movieDbresponse);
            JSONArray resultsJSON;




            allMoviesList = new ArrayList<Movie>();
            Log.d("in movie parse", movieJSON.toString());

            resultsJSON = movieJSON.getJSONArray("results");
            Log.d("json tag", resultsJSON.toString());


            for (int i = 0; i < resultsJSON.length(); i++) {
                //title
                JSONObject jsonObj = resultsJSON.optJSONObject(i);
                //allMoviesList.add(jsonObj);


                String title = jsonObj.getString("title");

                Log.d("title", title);

                String release_date = jsonObj.getString("release_date");

                String poster_path = jsonObj.getString("poster_path");

                Log.d("poster path ", poster_path);

                String vote_average = jsonObj.getString("vote_average");


                String plot_synopsis = jsonObj.getString("overview");


                movieObj = new Movie(title,release_date,poster_path,vote_average,plot_synopsis);

                movieObj.setTitle(title);
                movieObj.setRelease_date(release_date);
                movieObj.setMovie_poster(poster_path);
                movieObj.setVote_average(vote_average);
                movieObj.setPlot_synopsis(plot_synopsis);



                allMoviesList.add(movieObj);
                Log.d("movieObj vals",allMoviesList.toString());

                indexOfMovie++;
            }
            Log.d("indexOfMovie",String.valueOf(indexOfMovie));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return allMoviesList;
    }

}
