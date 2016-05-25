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

public class MainActivity extends Activity {
    // called when the app first launches
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call super's onCreate method
        setContentView(R.layout.activity_main); // inflate the layout
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

        JetGameView jetV = (JetGameView) findViewById(R.id.cannonView);

        switch (mi.getItemId()){
            case R.id.select_stage:
            {
                return true;
            }
            case R.id.local_high_scores:
            {
                return true;
            }
            case R.id.global_high_scores:
            {
                return true;
            }
            case R.id.about:
            {
                jetV.getGameThread().pauseGame();
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
                        //do nothing
                        jetV.getGameThread().resumeGame();
                    }
                });
        builder.show();
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
