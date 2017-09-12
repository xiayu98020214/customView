package com.coinvs.testsurfaceview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CoordinateLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinate_layout);
        TextView tv = (TextView)findViewById(R.id.tv);
        String content = "";
        for(int i = 0 ;i < 20; i++){
            content = content + i + "\n";
        }
        tv.setText(content);
    }
}
