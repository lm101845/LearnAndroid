package com.dongnaoedu.chapter09;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class LifeCycleTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle_test);
        Log.d("ning","onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ning","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ning","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ning","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ning","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ning","onDestroy");
    }
}