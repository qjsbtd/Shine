package com.shine.ui;

import android.app.Activity;
import android.util.Log;

public abstract class BaseActivity extends Activity {

    protected void printLog(String content) {
        Log.i(getLocalClassName(), "Shine..." + content);
    }

    protected void printLog(Exception e) {
        e.printStackTrace();
        printLog(e);
    }
}


