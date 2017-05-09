package com.coinvs.testsurfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static android.view.View.MeasureSpec.AT_MOST;

/**
 * Created by 七喜 on 2017/5/8.
 */

/*
*  layout文件中match_pare对应 MeasureSpec.EXACTLY
* */
public class MyImageView extends View {
    private static final String TAG = "MyImageView";
    private Bitmap mBitmap;// 位图对象

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制位图
        canvas.drawBitmap(mBitmap, 0, 0, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 设置测量尺寸
        // 声明一个临时变量来存储计算出的测量值
        int resultWidth = 0;

        // 获取宽度测量规格中的mode
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);

        // 获取宽度测量规格中的size
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        Log.d(TAG, "onMeasure: sizeWidth"+sizeWidth);

    /*
     * 如果爹心里有数
     */
        Log.d(TAG, "onMeasure: modeWidth:"+modeWidth);
        if (modeWidth == MeasureSpec.EXACTLY) {
            // 那么儿子也不要让爹难做就取爹给的大小吧
            Log.d(TAG, "onMeasure: EXACTLY");
            resultWidth = sizeWidth;
        }
    /*
     * 如果爹心里没数
     */
        else {
            Log.d(TAG, "onMeasure: else");

            // 那么儿子可要自己看看自己需要多大了
            resultWidth = mBitmap.getWidth();

        /*
         * 如果爹给儿子的是一个限制值
         */
            if (modeWidth == AT_MOST) {
                Log.d(TAG, "onMeasure: AT_MOST:");
                // 那么儿子自己的需求就要跟爹的限制比比看谁小要谁
                resultWidth = Math.min(resultWidth, sizeWidth);
            }
        }

        int resultHeight = 0;
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (modeHeight == MeasureSpec.EXACTLY) {
            resultHeight = sizeHeight;
        } else {
            resultHeight = mBitmap.getHeight();
            if (modeHeight == AT_MOST) {
                resultHeight = Math.min(resultHeight, sizeHeight);
            }
        }

        // 设置测量尺寸
        setMeasuredDimension(resultWidth, resultHeight);
    }
    /**
     * 设置位图
     *
     * @param bitmap
     *            位图对象
     */
    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }
}
