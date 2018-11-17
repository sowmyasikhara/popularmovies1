package com.example.sowmy.popularmoviesstage1.utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import java.io.InputStream;
import java.util.Scanner;

public class MovieNetworkUtils {
        final static String MOVIEDB_URL =
                // "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc";
                "https://api.themoviedb.org/3/discover/movie?";
        final static String PARAM_QUERY = "q";

        /*
         * The sort field. One of stars, forks, or updated.
         * Default: results are sorted by best match if no field is specified.
         */
        final static String PARAM_SORT = "sort_by";
        final static String API_KEY = "06a566cf4b59a36e14f0e4ab07ab8f85";
        final static String PARAM_API_KEY = "api_key";

        final static String PARAM_SORT_KEY_VALUE="popularity.desc";
        /**
         * Builds the URL used to query movie database.
         *

         * @return The URL to use to query the movie db server.
         */
        public static URL buildUrl(){

          //getting movie details from TMDB

            Uri builtUri = Uri.parse(MOVIEDB_URL).buildUpon()
                    .appendQueryParameter(PARAM_SORT,PARAM_SORT_KEY_VALUE)
                    .appendQueryParameter(PARAM_API_KEY,API_KEY)
                    .build();

            URL url = null;
            try {
                url = new URL(builtUri.toString());
                Log.d("url connected",url.toString());


            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return url;
        }

        /**
         * This method returns the entire result from the HTTP response.
         *
         * @param url The URL to fetch the HTTP response from.
         * @return The contents of the HTTP response.
         * @throws IOException Related to network and stream reading
         */
        public static String getResponseFromHttpUrl(URL url) throws IOException {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = urlConnection.getInputStream();

                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");

                boolean hasInput = scanner.hasNext();
                if (hasInput) {
                    return scanner.next();
                } else {
                    return null;
                }
            } finally {
                urlConnection.disconnect();
            }
        }
    }


