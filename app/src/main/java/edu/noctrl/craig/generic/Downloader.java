package edu.noctrl.craig.generic;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

/**
 * An asynchronous task that will open an input stream to the specified url.
 * Created by craig_000 on 4/26/2015.
 */
public class Downloader<T> extends AsyncTask<String, Void, T>{
    /**
     *  Listener interface that receives data from the Downloader async task
     */
    public interface DownloadListener<T> {
        /**
         * Override this function.  Create a function that parses the data
         * from the input stream and returns an object of type <code>T</code>
         * @param ios InputStream from the web request
         * @return T object created from the input stream
         */
        public abstract T parseResponse(InputStream in) throws IOException, JSONException;
        /**
         * Override this function.  Create a function that takes an object of type <code>T</code>
         * and performs the required actions by your application
         * @param result Object of type <code>T</code> created in the override parseResponse function
         */
        public abstract void handleResult(T result) throws JSONException;
    }
    DownloadListener<T> listener;
    public Downloader(DownloadListener<T> listener) {
        this.listener = listener;
    }

    protected T doInBackground(String... urls) {
        String urlDisplay = urls[0];
        T val = null;
        try {
            InputStream in = new java.net.URL(urlDisplay).openStream();
            val = listener.parseResponse(in);
            in.close();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return val;
    }

    protected void onPostExecute(T result) {
        try {
            listener.handleResult(result);
        } catch (JSONException e) {

        }
    }
}