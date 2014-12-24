package me.shumon.selfiemania;

/**
 * Created by kutimuti on 11/29/14.
 */


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageViewerActivity extends Activity {

    private ArrayList<String> mImagePathList;
    private int mListSelectionPosition;
    private String mCurrentPhotoPath;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);

        // set the actionbar color
        getActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#808080")));


        //get the viewer
        mImageView = (ImageView) findViewById(R.id.show_image);


        // receive the intent data
        Intent i = getIntent();

        // get the image paths and the list selection position.
        if (i.getExtras() != null) {
            mImagePathList = i.getExtras().getStringArrayList("images");
            mListSelectionPosition = i.getIntExtra("position", 0);
        }

        //get the path for selected image
        mCurrentPhotoPath = mImagePathList.get(mListSelectionPosition);
        setPic();
    }



    private void setPic() {
        // Get the dimensions of the View;
        int targetW;
        int targetH;

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        targetW = displaymetrics.widthPixels;
        targetH = displaymetrics.heightPixels;


        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        mImageView.setImageBitmap(bitmap);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.image_viewer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.rotate) {
            rotate(-90);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void rotate(float degree) {
        final RotateAnimation rotateAnim = new RotateAnimation(0.0f, degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotateAnim.setDuration(0);
        rotateAnim.setFillAfter(true);
        mImageView.startAnimation(rotateAnim);
    }

}
