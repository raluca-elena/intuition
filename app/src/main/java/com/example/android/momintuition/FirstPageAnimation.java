package com.example.android.momintuition;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;


public class FirstPageAnimation extends ActionBarActivity {
    CircularSeekBar circularSeekbar;
    CircularSeekBar circularSeekbar2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page_animation);
        ViewGroup v = (ViewGroup)findViewById(R.id.abc);


        circularSeekbar = new CircularSeekBar(this);
        circularSeekbar.adjustRadius(6, 2);

        circularSeekbar.setMaxProgress(100);
        circularSeekbar.setProgress(0);
        addContentView(circularSeekbar, v.getLayoutParams());
        //circularSeekbar.invalidate();
        //circularSeekbar.setProgress(20);
        circularSeekbar.setAngle(10);
        int angle = 20;


        circularSeekbar.setSeekBarChangeListener(new CircularSeekBar.OnSeekChangeListener() {

            @Override
            public void onProgressChange(CircularSeekBar view, int newProgress) {
                Log.d("Welcome", "Progress:" + view.getProgress() + "/" + view.getMaxProgress());
            }
        });


        circularSeekbar2 = new CircularSeekBar(this);
        circularSeekbar2.setMaxProgress(100);
        circularSeekbar2.setProgress(0);
        addContentView(circularSeekbar2, v.getLayoutParams());
        circularSeekbar2.invalidate();

        circularSeekbar2.setSeekBarChangeListener(new CircularSeekBar.OnSeekChangeListener() {

            @Override
            public void onProgressChange(CircularSeekBar view, int newProgress) {
                Log.d("Welcome1", "Progress:" + view.getProgress() + "/" + view.getMaxProgress());
                circularSeekbar.setProgress(circularSeekbar.getProgressPercent() + 10);
            }
        });

        circularSeekbar.setProgress(circularSeekbar.getProgressPercent() + 10);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first_page_animation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
