package com.coinvs.testsurfaceview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Book1_1Activity extends AppCompatActivity {
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book1_1);
        iv = (ImageView) findViewById(R.id.iv);
        Bitmap bmpBuffer = Bitmap.createBitmap(500, 800,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmpBuffer);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//􀧏􀽗􁈿􃔈􀡦
        canvas.drawBitmap(bmp, 0, 0, null);
//􁈩􀴮􂡷􄘋􃹼􃕙􁭮
        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();
        Rect src = new Rect(0, 0, bmpWidth, bmpHeight);
        Rect dst = new Rect(0, bmpHeight, bmpWidth * 3, bmpHeight * 3 + bmpHeight);
        canvas.drawBitmap(bmp, src, dst, null);
        iv.setImageBitmap(bmpBuffer);
    }
}
