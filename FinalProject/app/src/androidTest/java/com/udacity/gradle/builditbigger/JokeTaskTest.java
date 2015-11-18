package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;

import junit.framework.TestCase;

import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/11/18
 * Email: vhly@163.com
 */
public class JokeTaskTest extends AndroidTestCase {

    public void testGetTodayJoke() throws ExecutionException, InterruptedException {
        JokeTask task = new JokeTask(null);
        String s = task.get();

        assertNotNull(s);
    }

}
