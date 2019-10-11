package com.shine.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shine.ui.buttons.ButtonActivity;
import com.shine.ui.text.TextViewActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        skip();
    }

    private void skip(){
        Intent intent = new Intent();
        intent.setClass(this, TextViewActivity.class);
        intent.setClass(this, ButtonActivity.class);
        startActivity(intent);
        finish();
    }
}
