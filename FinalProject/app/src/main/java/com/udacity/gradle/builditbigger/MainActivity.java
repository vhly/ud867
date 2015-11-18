package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import mobi.vhly.jokeshowerandroid.lib.Constants;
import mobi.vhly.jokeshowerandroid.lib.JokeShowerActivity;

public class MainActivity extends AppCompatActivity implements TaskCallback {

    private boolean loading;

    private boolean resume;

    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb = (ProgressBar) findViewById(R.id.pb_indicator);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    protected void onResume() {
        super.onResume();
        resume = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        resume = false;
    }

    public void tellJoke(View view) {

        // Get Joke content form Java Library class define.
//        String jokeContent = Joke.getJokeContent();
//
//        if (jokeContent == null) {
//            jokeContent = "Empty Joke";
//        }
//
//        // Show Activity from JokeShowerAndroid Android Library
//        Intent intent = new Intent(this, JokeShowerActivity.class);
//
//        startActivity(intent);

        if (!loading) {

            JokeTask task = new JokeTask(this);
            task.execute();
            loading = true;

        }

    }

    @Override
    public void onTaskPrepare() {
        if (pb != null) {
            pb.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onTaskResult(Object result) {
        if (result != null) {
            loading = false;
            if (resume) {
                // TODO Show Activity

                if (pb != null) {
                    pb.setVisibility(View.INVISIBLE);
                }

                // Show Activity from JokeShowerAndroid Android Library
                Intent intent = new Intent(this, JokeShowerActivity.class);
                intent.putExtra(Constants.INTENT_KEY_JOKE_CONTENT, result.toString());
                startActivity(intent);

            }

        }
    }
}
