package com.coinvs.testsurfaceview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 七喜 on 2017/5/8.
 */

public class showActivity2 extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_2_activity);
        runnableView change = (runnableView) findViewById(R.id.changeview);
        new Thread(change).start();

    }
}
