package com.coinvs.testsurfaceview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 七喜 on 2017/5/8.
 */
//http://blog.csdn.net/aigestudio/article/details/42989325
public class show712 extends AppCompatActivity {
    private MyImageView mImgView;
    private IconView mIconvew;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show712_3);

        mIconvew = (IconView) findViewById(R.id.main_pv);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.saving_pot_top_bg);
        //mImgView.setBitmap(bitmap);
    }
}

