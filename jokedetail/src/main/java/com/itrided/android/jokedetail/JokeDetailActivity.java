package com.itrided.android.jokedetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class JokeDetailActivity extends AppCompatActivity {
    public static final String JOKE_EXTRA_NAME = "com.itrided.android.jokedetail.JOKE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_detail);

        if (!getIntent().hasExtra(JOKE_EXTRA_NAME))
            finish();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String joke = getIntent().getStringExtra(JOKE_EXTRA_NAME);
        final TextView jokeTv = findViewById(R.id.joke_tv);
        jokeTv.setText(joke);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
