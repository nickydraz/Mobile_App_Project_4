package edu.noctrl.craig.generic;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by bacraig on 5/25/2016.
 */
public class WebHandler {
    public void sendHighScore( String name, int score, Date datetime) throws IOException {
        URL url = new URL("http://craiginsdev.com/highscore/scores.php");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        Log.i("WebHandler","Send High Score");
        try {
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);
            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            writePostData(out, name, score, datetime);
            out.flush();
            out.close();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            //readStream(in);
            in.close();
        }
        finally {
            urlConnection.disconnect();
        }

    }

    private void writePostData(OutputStream out, String name, int score, Date datetime) throws IOException{
        String postString = "";
        postString += "name=" + URLEncoder.encode(name, "UTF-8") + "&";
        postString += "score=" + URLEncoder.encode(score+"", "UTF-8") + "&";
        postString += "game=" + URLEncoder.encode("Deserted Space", "UTF-8") + "&";
        postString += "datetime=" + URLEncoder.encode(datetime.toString(), "UTF-8") + "&";
        Log.i("WebHandler","WritePostData: " + postString);
        byte[] postBytes = postString.getBytes();
        out.write(postBytes, 0, postBytes.length);
    }
}
