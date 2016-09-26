package com.ingsoft.url.gudbox;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.net.URLConnection;

/**
 * Created by iiscancinos on 25/09/2016.
 */

public abstract class _API_Connection {
    /***
     *  Global variables
     */
    String main_URL = "http://urlayd.azurewebsites.net/";

    public _API_Connection(){
        try {
            connect("api/GetSeeds");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * API connection method
     * @param function_url
     * @return
     */
    public Boolean connect(String function_url) throws IOException {
        Boolean flag = false;
        URL url = new URL(main_URL + function_url);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try{
            InputStream input = new BufferedInputStream(urlConnection.getInputStream());
            int a = 0;
        }
        finally{
            urlConnection.disconnect();
        }

        return flag;
    }

}
