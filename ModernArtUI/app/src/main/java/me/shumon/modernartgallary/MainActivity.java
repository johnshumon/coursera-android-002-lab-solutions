/*
* Author: Abu Fazal Md Shumon
*
*/

package me.shumon.modernartgallary;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


public class MainActivity extends Activity {

    // Divided the main grid layout on Two column. First column contains two rectangles
    // and second contains Three rectangles.

    private TextView pos_1; // First rectangle of 0
    private TextView pos_2; // Second rectangle of 0
    private TextView pos_3; // First rectangle of 1
    private TextView pos_4; // Second rectangle of 1
    private TextView pos_5; // Third rectangle of 1

    int maximumProgress = 100; // maximum progress of the seekbar.

    AlertDialog.Builder builder; // dialog builder for popup option.

    private String urlString = "http://www.moma.org";
    private String dialogMessage = "Inspired by the works of MoMA artists. \n\nClick below to learn more!";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Takes the values of the items defined by the id's
        pos_1 = (TextView) findViewById(R.id.rectangle1);
        pos_2 = (TextView) findViewById(R.id.rectangle2);
        pos_3 = (TextView) findViewById(R.id.rectangle3);
        pos_4 = (TextView) findViewById(R.id.rectangle4);
        pos_5 = (TextView) findViewById(R.id.rectangle5);

        SeekBar seekBarValue = (SeekBar) findViewById(R.id.customSeekBar);
        seekBarValue.setOnSeekBarChangeListener(customSeekBarListener);
    }

    private OnSeekBarChangeListener customSeekBarListener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            // Changes the background color of each rectangle depending on the progress of the seekbar.
            pos_1.setBackgroundColor(Color.rgb( (92*progress/maximumProgress), (137*progress/maximumProgress), 255));
            pos_2.setBackgroundColor(Color.rgb( (238*progress/maximumProgress), (83*progress/maximumProgress), 60));
            pos_3.setBackgroundColor(Color.rgb( (255*progress/maximumProgress), 255, (255*progress/maximumProgress) ));
            pos_4.setBackgroundColor(Color.rgb( (242*progress/maximumProgress), 0, 0));
            pos_5.setBackgroundColor(Color.rgb( (92*progress/maximumProgress), 6, 255));

            // Changes the rectangles color to as when the app launched if user sets seekbar bar to initial position.
            if (progress == 0){
                pos_1.setBackgroundColor(Color.rgb(92, 137, 255));
                pos_2.setBackgroundColor(Color.rgb(238, 83, 255));
                pos_3.setBackgroundColor(Color.rgb(255, 7, 35));
                pos_4.setBackgroundColor(Color.rgb(242, 235, 255));
                pos_5.setBackgroundColor(Color.rgb(75, 4, 255));
            }

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            builder = new AlertDialog.Builder(MainActivity.this, AlertDialog.THEME_HOLO_DARK);

            builder.setNegativeButton("Not Now?", onClick); // Not now button
            builder.setPositiveButton("Visit MoMA", onClick); // Visit button which launches browser to visit moma site.
            builder.setMessage(dialogMessage); // custom message


            AlertDialog dialog = builder.show();

            // Justifies the custom message as CENTERED.
            TextView alertMessage = (TextView) dialog.findViewById(android.R.id.message);
            alertMessage.setGravity(Gravity.CENTER);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // OnClickListener of the dialog interface. works depending on which button is pressed.
    DialogInterface.OnClickListener onClick = new DialogInterface.OnClickListener() {

        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_NEGATIVE) {
                dialog.cancel();
            }
            else if (which == DialogInterface.BUTTON_POSITIVE){

                Intent baseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                Intent chooserIntent = Intent.createChooser(baseIntent, "Load " + urlString + " with: ");
                startActivity(chooserIntent);
            }
        }

    };
}
