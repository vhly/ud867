package mobi.vhly.jokeshowerandroid.lib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Show joke from Library
 */
public class JokeShowerActivity extends Activity {

    private TextView txtJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_shower);

        txtJoke = (TextView) findViewById(R.id.joke_shower_joke_text);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();

        String content = intent.getStringExtra(Constants.INTENT_KEY_JOKE_CONTENT);

        if (content != null) {

            if (txtJoke != null) {

                txtJoke.setText(content);

            }

        }

    }
}
