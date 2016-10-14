package com.ingsoft.url.gudbox;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by iiscancinos on 25/09/2016.
 */

public class _API_Connection extends AsyncTask {
    /***
     *  Global variables
     */
    String main_URL = "http://urlayd.azurewebsites.net/api/GetSeeds", message = "";
    InternalAPI.OPERATIONS operation_code;
    Map<String, Object> params = new HashMap<String, Object>();
    private onTaskCompleted taskCompleted;

    /***
     * API's constructor; setting url
     * @param activityContext (String) HTTP action path
     */
    public _API_Connection(onTaskCompleted activityContext){
        this.taskCompleted = activityContext;
    }

    public void changeURL(String url){
        this.main_URL = url;
    }

    public void addParam(String key, Object objectParam){
        params.put(key, objectParam);
    }

    public Object getParam(String key){
        return params.get(key);
    }

    /***
     * Thread main execution
     * @param objects (Object[]) use for http request
     * @return (String) retrieve body request
     */
    @Override
    protected Object doInBackground(Object[] objects) {
        message = connect();
        return null;
    }

    protected void onPostExecute(Object o){
        // Your code to handle data
        taskCompleted.onTaskCompleted(message, operation_code, params);
    }

    /***
     * API connection method
     * @return flag (String) retrieve body request
     */
    public String connect(){
        /* Local variables */
        String response = "";
        HttpURLConnection urlConnection = null;

        try{
            URL url = new URL(main_URL); // setting url
            urlConnection = (HttpURLConnection) url.openConnection(); // http request
            urlConnection.connect();
            response = jsonToString(urlConnection.getInputStream()); // String format to body request
        }
        catch(Exception e){
            return e.toString();
        }
        finally{
            urlConnection.disconnect();
        }

        return response;
    }

    /***
     * Allow us to convert from json to string object
     * @param is (InputStream) Input from http request
     * @return flag (String) object in string format
     * @throws IOException
     */
    public String jsonToString(InputStream is) throws IOException {
        /* local variables */
        String flag = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        /* readline of the request */
        while ((flag = br.readLine()) != null) {
            sb.append(flag + "\n");
        }
        br.close();

        return sb.toString();
    }

    public interface onTaskCompleted {
        void onTaskCompleted(String response, InternalAPI.OPERATIONS code, Map<String, Object> params);
    }
}
