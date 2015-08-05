package com.example.android.momintuition;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;


public class FirstPageAnimation extends ActionBarActivity {
    CircularSeekBar circularSeekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page_animation);
        //Button btn = (Button) findViewById(R.id.animatedButton);
        //Animation hyperspaceJump = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);
        //hyperspaceJump.setRepeatCount(4);
        //btn.startAnimation(hyperspaceJump);

        circularSeekbar = new CircularSeekBar(this);
        circularSeekbar.setMaxProgress(100);
        circularSeekbar.setProgress(0);
        setContentView(circularSeekbar);
        circularSeekbar.invalidate();



        circularSeekbar.setSeekBarChangeListener(new CircularSeekBar.OnSeekChangeListener() {

            @Override
            public void onProgressChange(CircularSeekBar view, int newProgress) {
                Log.d("Welcome", "Progress:" + view.getProgress() + "/" + view.getMaxProgress());
            }
        });

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
