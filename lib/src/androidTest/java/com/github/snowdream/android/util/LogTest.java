package com.github.snowdream.android.util;

import android.test.AndroidTestCase;

/**
 * Created by snowdream on 4/8/14.
 */
public class LogTest extends AndroidTestCase {
    private final static String TAG = "ANDROID_LOG";
    private final static String CUSTOM_TAG = "CUSTOM_TAG";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Log.setTag(TAG);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testEnableOrDisableLog() {
        Log.i("Log is enable.");
        Log.setEnabled(false);
        Log.i("Log is disable.");
        Log.setEnabled(true);
        Log.i("Log is enable again.");
    }

    public void testSimpleLog() {
        Log.d("test");
        Log.v("test");
        Log.i("test");
        Log.w("test");
        Log.e("test");
    }

    public void testSimpleLogWithCustomTAG() {
        Log.d(CUSTOM_TAG, "test");
        Log.v(CUSTOM_TAG, "test");
        Log.i(CUSTOM_TAG, "test");
        Log.w(CUSTOM_TAG, "test");
        Log.e(CUSTOM_TAG, "test");
    }

    public void testAdvanceLog() {
        Log.d("test", new Throwable("test"));
        Log.v("test", new Throwable("test"));
        Log.i("test", new Throwable("test"));
        Log.w("test", new Throwable("test"));
        Log.e("test", new Throwable("test"));
    }

    public void testAdvanceLogWithCustomTAG() {
        Log.d(CUSTOM_TAG,"test",new Throwable("test"));
        Log.v(CUSTOM_TAG,"test",new Throwable("test"));
        Log.i(CUSTOM_TAG,"test",new Throwable("test"));
        Log.w(CUSTOM_TAG,"test",new Throwable("test"));
        Log.e(CUSTOM_TAG,"test",new Throwable("test"));
    }

    public void testLogIntoOneFile() {
        Log.setPath("/mnt/sdcard/debug.txt");
        Log.setPolicy(Log.LOG_ALL_TO_FILE);

        Log.d("test 1");
        Log.v("test 2");
        Log.i("test 3");
        Log.w("test 4");
        Log.e("test 5");
    }

    public void testLogIntoOneDirectoryWithFiles() {
        Log.setPath("/mnt/sdcard/snowdream/log","log","log");
        Log.setPolicy(Log.LOG_ALL_TO_FILE);

        Log.d("test 1");
        Log.v("test 2");
        Log.i("test 3");
        Log.w("test 4");
        Log.e("test 5");
    }
}
