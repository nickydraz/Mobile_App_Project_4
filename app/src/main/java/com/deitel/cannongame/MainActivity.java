// CannonGame.java
// MainActivity displays the JetGameFragment
package com.deitel.cannongame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.noctrl.craig.generic.Downloader;

public class MainActivity extends Activity {

    //Set array for stage selection
    String[] stages = new String[3];

    // called when the app first launches
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call super's onCreate method
        setContentView(R.layout.activity_main); // inflate the layout
        stages[0] = getResources().getString(R.string.stage1);
        stages[1] = getResources().getString(R.string.stage2);
        stages[2] = getResources().getString(R.string.stage3);
    }

    //Methods for Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override //what to do when the menu item is selected
    public boolean onOptionsItemSelected(MenuItem mi){

        //Pause the game when a menu item is selected
        JetGameView jetV = (JetGameView) findViewById(R.id.cannonView);
        jetV.getGameThread().pauseGame();

        switch (mi.getItemId()){
            case R.id.select_stage:
            {
                stageSelectDialog(jetV);
                return true;
            }
            case R.id.local_high_scores:
            {
                localScoresDialog(jetV);
                return true;
            }
            case R.id.global_high_scores:
            {
                try {
                    globalScoresDialog(jetV);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
            case R.id.about:
            {
                aboutDialog(jetV);
                return true;
            }
            default:
            {
                return super.onOptionsItemSelected(mi);
            }
        }
    }

    public void aboutDialog(final JetGameView jetV)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(R.string.aboutText).
                setTitle(R.string.aboutTitle).
                setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        jetV.getGameThread().resumeGame();
                    }
                });
        builder.show();
    }

    public void stageSelectDialog(final JetGameView jetV)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.
                setTitle(R.string.stageTitle).
                setItems(stages, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        jetV.onNextStage(false, which + 1);
                    }
                });
        builder.show();
    }

    public void localScoresDialog(final JetGameView jetV)
    {
        List<Object[]> records = jetV.helper.getRecords();
        String[] highScores = new String[Math.min(records.size(), 5)];

        for(int i=0; i<highScores.length; i++){
            highScores[i] = "Name: " + records.get(i)[1] + " Score: " + records.get(i)[2];
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.
                setTitle(R.string.highScoreTitle).
                setItems(highScores, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        jetV.getGameThread().resumeGame();
                    }
                });
        builder.show();
    }

    public void globalScoresDialog(final JetGameView jetV) throws IOException {
        Downloader<JSONArray> downloader = new Downloader<JSONArray>(new Downloader.DownloadListener<JSONArray>() {
            @Override
            public JSONArray parseResponse(InputStream in) throws IOException, JSONException {
                StringBuilder strBuild = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                //read lines from input
                String line = br.readLine();

                while (line != null) {
                    strBuild.append(line);
                    line = br.readLine();
                }
                String result = strBuild.toString();
                JSONArray obj = new JSONArray(result);

                return obj;
            }

            @Override
            public void handleResult(JSONArray result) throws JSONException {
                ArrayList<JSONObject> objs = new ArrayList<JSONObject>();
                try {
                    JSONArray data = result;
                    for (int i = 0; i < data.length(); i++)
                    {
                        objs.add((JSONObject) data.get(i));
                    }

                    Collections.sort(objs, new Comparator<JSONObject>() {
                        @Override
                        public int compare(JSONObject lhs, JSONObject rhs) {
                            try {
                                return rhs.getInt("score") - lhs.getInt("score");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            return 0;
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                String[] highScores = new String[Math.min(objs.size(), 5)];

                for (int i = 0; i < highScores.length; i++){
                    try {
                        highScores[i] = "Name: " + objs.get(i).getString("name") + " Score: " + objs.get(i).getInt("score");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.
                        setTitle(R.string.highScoreTitle).
                        setItems(highScores, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                jetV.getGameThread().resumeGame();
                            }
                        });
                builder.show();

            }
        });

        downloader.execute("http://craiginsdev.com/highscore/scores.php?game=Deserted%20Space");

    }

} // end class MainActivity

/*********************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and * Pearson Education, *
 * Inc. All Rights Reserved. * * DISCLAIMER: The authors and publisher of this   *
 * book have used their * best efforts in preparing the book. These efforts      *
 * include the * development, research, and testing of the theories and programs *
 * * to determine their effectiveness. The authors and publisher make * no       *
 * warranty of any kind, expressed or implied, with regard to these * programs   *
 * or to the documentation contained in these books. The authors * and publisher *
 * shall not be liable in any event for incidental or * consequential damages in *
 * connection with, or arising out of, the * furnishing, performance, or use of  *
 * these programs.                                                               *
 *********************************************************************************/
