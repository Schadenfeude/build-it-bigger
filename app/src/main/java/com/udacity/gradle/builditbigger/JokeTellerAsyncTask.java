package com.udacity.gradle.builditbigger;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import com.udacity.gradle.builditbigger.backend.theJokerApi.TheJokerApi;

import java.io.IOException;

class JokeTellerAsyncTask extends AsyncTask<Void, Void, String> {
    private static TheJokerApi theJokerApi = null;
    private static final String ENDPOINT_URL = "http://10.0.2.2:8080/_ah/api/";

    private JokerCallback callback;

    public JokeTellerAsyncTask(JokerCallback callback) {
        this.callback = callback;
    }

    @Override
    protected String doInBackground(Void... voids) {
        if (theJokerApi == null) {  // Only do this once
            TheJokerApi.Builder builder = new TheJokerApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl(ENDPOINT_URL)
                    .setGoogleClientRequestInitializer(abstractGoogleClientRequest ->
                            abstractGoogleClientRequest.setDisableGZipContent(true));
            // end options for devappserver

            theJokerApi = builder.build();
        }

        try {
            return theJokerApi.makeMeLaugh().execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        callback.takeABreath();
    }

    @Override
    protected void onPostExecute(String joke) {
        super.onPostExecute(joke);
        callback.makeMeLaugh(joke);
    }
}