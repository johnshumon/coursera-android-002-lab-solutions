package me.shumon.selfiemania;

/**
 * Created by kutimuti on 11/29/14.
 */

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

public class HelloActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_empty_layout);

        // set the actionbar color
        getActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#B8B8B8")));
    }


}