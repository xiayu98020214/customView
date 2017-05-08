package com.coinvs.testsurfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 七喜 on 2017/5/8.
 */
//http://blog.csdn.net/aigestudio/article/details/42677973
public class LayerView extends View {
    private Paint mPaint;// 画笔对象

    private int mViewWidth, mViewHeight;// 控件宽高

    public LayerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 实例化画笔对象并设置其标识值
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        /*
         * 获取控件宽高
         */
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
    /*
     * 保存并裁剪画布填充绿色
     */
        int saveID1 = canvas.save(Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect(mViewWidth / 2F - 300, mViewHeight / 2F - 300, mViewWidth / 2F + 300, mViewHeight / 2F + 300);
        canvas.drawColor(Color.YELLOW);
        canvas.restore();


    /*
     * 保存并裁剪画布填充绿色
     */
        int saveID2 = canvas.save(Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect(mViewWidth / 2F - 200, mViewHeight / 2F - 200, mViewWidth / 2F + 200, mViewHeight / 2F + 200);
        canvas.drawColor(Color.GREEN);
        canvas.restore();



    /*
     * 保存画布并旋转后绘制一个蓝色的矩形
     */
        int saveID3 = canvas.save(Canvas.MATRIX_SAVE_FLAG);

        // 旋转画布
        canvas.rotate(5);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(mViewWidth / 2F - 100, mViewHeight / 2F - 100, mViewWidth / 2F + 100, mViewHeight / 2F + 100, mPaint);

        canvas.restore();
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(mViewWidth / 2F, mViewHeight / 2F, mViewWidth / 2F + 400, mViewHeight / 2F + 400, mPaint);
    }
}
//saveLayer相当又生成了一个层，但是合成层的时候，只合成指定的一个区域。