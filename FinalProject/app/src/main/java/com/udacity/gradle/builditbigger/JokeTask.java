package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.lang.ref.WeakReference;

import mobi.vhly.jokes.backend.myApi.MyApi;
import mobi.vhly.jokes.backend.myApi.model.MyBean;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/11/18
 * Email: vhly@163.com
 */
public class JokeTask extends AsyncTask<String, String, String> {

    private WeakReference<TaskCallback> reference;

    public JokeTask(TaskCallback callback) {
        if (callback != null) {

            reference = new WeakReference<TaskCallback>(callback);

        }
    }

    @Override
    protected String doInBackground(String... params) {

        String ret = null;

        MyApi.Builder builder = new MyApi.Builder(
                AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(),
                null
        );

        builder.setRootUrl("http://10.0.2.2:8080/_ah/api/")
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });

        MyApi myApi = builder.build();

        try {
            MyApi.GetTodayJoke todayJoke = myApi.getTodayJoke();

            MyBean execute = todayJoke.execute();

            ret = execute.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return ret;
    }

    @Override
    protected void onPostExecute(String s) {
        if (reference != null) {
            TaskCallback taskCallback = reference.get();
            if (taskCallback != null) {
                taskCallback.onTaskResult(s);
            }
        }
    }
}
