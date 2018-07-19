package com.udacity.gradle.builditbigger;

import android.support.annotation.NonNull;
import android.test.InstrumentationTestCase;
import android.util.Log;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;

public class JokeTest extends InstrumentationTestCase {
    private static final String TAG = JokeTest.class.getSimpleName();

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public final void testResultNonEmpty() throws Throwable {
        final Object syncObject = new Object();

        final JokeTellerAsyncTask jokeTask = new JokeTellerAsyncTask(new JokerCallback() {
            @Override
            public void takeABreath() {

            }

            @Override
            public void makeMeLaugh(@NonNull String joke) {
                Log.d(TAG, "Make me laugh: " + joke);

                Assert.assertNotNull(joke);
                Assert.assertTrue(!joke.isEmpty());

                synchronized (syncObject) {
                    syncObject.notify();
                }
            }
        });

        jokeTask.execute();

        synchronized (syncObject) {
            syncObject.wait();
        }
    }
}
