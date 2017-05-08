package com.coinvs.testsurfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by 七喜 on 2017/5/3.
 */

public class touchdraw extends SurfaceView implements SurfaceHolder.Callback, Runnable  {

    private SurfaceHolder holder;
    private Canvas mCanvas;
    private Paint mPaint;
    private Path mPath;
    private boolean mIsDrawing;

    public touchdraw(Context context) {
        super(context);
        holder=this.getHolder();
        holder.addCallback(this);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPath = new Path();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("xiayu", "onTouchEvent: x:" + event.getX()+"   y:"+event.getY() );
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        return true;
    }

    private void draw(){
        mCanvas = holder.lockCanvas();
        if(mCanvas != null) {
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, mPaint);
            holder.unlockCanvasAndPost(mCanvas);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;

    }

    @Override
    public void run() {
        while(mIsDrawing){
            draw();
        }
    }

}
