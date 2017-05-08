package com.coinvs.testsurfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by 七喜 on 2017/5/3.
 */

public class sinview extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder holder;
    private Canvas mCanvas;
    private Paint mPaint;
    private Path mPath;



    public sinview(Context context) {
        super(context);
        holder=this.getHolder();
        holder.addCallback(this);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPath = new Path();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        y = (int)(100*Math.sin(x*2*Math.PI/180)+400);
        mPath.moveTo(x, y);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
    int x = 0;
    int y;
    @Override
    public void run() {
         while(true){


             x +=1;
             y = (int)(100*Math.sin(x*2*Math.PI/180)+400);
             mPath.lineTo(x,y);
             draw();
             try {
                 Thread.sleep(10);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }

    }
    private void draw(){


        mCanvas = holder.lockCanvas();
        if(mCanvas != null) {
            mCanvas.drawColor(Color.BLUE);
            mCanvas.drawPath(mPath, mPaint);

            holder.unlockCanvasAndPost(mCanvas);
        }
    }

}


