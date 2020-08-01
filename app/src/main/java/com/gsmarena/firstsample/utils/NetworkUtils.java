package com.gsmarena.firstsample.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    // Class name for Log tag.
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    /**
     * Method for downloading data from api.
     * This method makes a network call so it can not be called on the main thread.
     *
     * @param queryString API query
     * @return The raw response from the API as a JSON String
     */
    public static String getDataFromServer(String queryString) {

        // Set up variables for the try block that need to be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;
        // Attempt to query the API.
        try {
            // Base URI for the Books API.
            URL requestURL = new URL("http://www.mocky.io/v2/5b1ffaf0310000bf2d230aae");
            // Open the network connection.
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // Get the InputStream.
            InputStream inputStream = urlConnection.getInputStream();
            // Read the response string into a StringBuilder.
            StringBuilder builder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // but it does make debugging a *lot* easier if you print out the completed buffer for debugging.
                builder.append(line + "\n");
            }
            if (builder.length() == 0) {
                // Stream was empty.  No point in parsing.
                // return null;
                return null;
            }
            bookJSONString = builder.toString();
            // Catch errors.
        } catch (IOException e) {
            e.printStackTrace();
            // Close the connections.
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }       // Return the raw response.
        return bookJSONString;
    }
}

