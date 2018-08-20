package com.coinvs.testsurfaceview;

/**
 * Created by xiayu on 2018/8/10  16 42
 */

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * A Simple Rebound ScrollView
 *
 * @author Denluoyia
 */
public class PullView extends RelativeLayout {

    private static final String TAG = "ReboundScrollView";

    private boolean mEnableTopRebound = true;
    private boolean mEnableBottomRebound = true;
    private OnReboundEndListener mOnReboundEndListener;
    private View mContentView;
    private View digView;
    private Rect mRect = new Rect();
    private Rect mRect_dig = new Rect();

    public PullView(Context context) {
        super(context);
    }

    public PullView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * after inflating view, we can get the width and height of view
     */
    private boolean first = true;
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContentView = this;
        digView = getChildAt(0);
     //   bottom = mContentView.getBottom();

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (mContentView == null) return;
        // to remember the location of mContentView
  //      mRect.set(mContentView.getLeft(), mContentView.getTop(), mContentView.getRight(), mContentView.getBottom());
    }

    public PullView setOnReboundEndListener(OnReboundEndListener onReboundEndListener) {
        this.mOnReboundEndListener = onReboundEndListener;
        return this;

    }

    public PullView setEnableTopRebound(boolean enableTopRebound) {
        this.mEnableTopRebound = enableTopRebound;
        return this;
    }

    public PullView setEnableBottomRebound(boolean mEnableBottomRebound) {
        this.mEnableBottomRebound = mEnableBottomRebound;
        return this;
    }

    private int lastY;
    private boolean rebound = false;
    private int reboundDirection = 0; //<0 表示下部回弹 >0 表示上部回弹 0表示不回弹
    private boolean pulling = false;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mContentView == null) {
            return super.dispatchTouchEvent(ev);
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = (int) ev.getY();
                if(first) {
                    first = false;
                    mRect.set(mContentView.getLeft(), mContentView.getTop(), mContentView.getRight(), mContentView.getBottom());
                    mRect_dig.set(digView.getLeft(), digView.getTop(), digView.getRight(), digView.getBottom());
                }
                if (mOnReboundEndListener != null) mOnReboundEndListener.onDown();
                break;

            case MotionEvent.ACTION_MOVE:
                if(pulling) break;
    /*            if (!isScrollToTop() && !isScrollToBottom()) {
                    lastY = (int) ev.getY();
                    break;
                }*/
                //处于顶部或者底部
                int deltaY = (int) (ev.getY() - lastY);
                if(deltaY < 0 ) break;
                //deltaY > 0 下拉 deltaY < 0 上拉
                if (deltaY > 80) {
                    if (mOnReboundEndListener != null) mOnReboundEndListener.onPull();
                }
                if (deltaY > 190) {
                    break;
                }


                //disable top or bottom rebound
                if ((!mEnableTopRebound && deltaY > 0) || (!mEnableBottomRebound && deltaY < 0))

                {
                    break;
                }

             //   int offset = (int) (deltaY * 0.48);
                int offset = (int) (deltaY * 0.48);
         //       mContentView.layout(mRect.left, mRect.top , mRect.right, mRect.bottom + offset);
             //   digView.layout(mRect_dig.left, mRect_dig.top + offset, mRect_dig.right, mRect_dig.bottom + offset);
                LinearLayout.LayoutParams lp=(LinearLayout.LayoutParams)mContentView.getLayoutParams();
                lp.height= (mRect.bottom - mRect.top) +offset;
                mContentView.setLayoutParams(lp);

                rebound = true;
                break;

            case MotionEvent.ACTION_UP:
                if (!rebound) break;
                pulling = true;
                if (mOnReboundEndListener != null) mOnReboundEndListener.onRelease();
                reboundDirection = mContentView.getTop() - mRect.top;
                final int distance = mContentView.getBottom() - mRect.bottom;
                ValueAnimator animator = ValueAnimator.ofFloat(1,0);
                animator.setInterpolator(new OvershootInterpolator());
                animator.setDuration(1000).start();
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Float value = (Float)animation.getAnimatedValue();
                        Log.e(TAG,"value :"+value);
                        int dis = (int)(distance*value);
 /*                       mContentView.layout(mRect.left, mRect.top, mRect.right, mRect.bottom+dis);
                        digView.layout(mRect_dig.left, mRect_dig.top + dis, mRect_dig.right, mRect_dig.bottom + dis);*/

                        LinearLayout.LayoutParams lp=(LinearLayout.LayoutParams)mContentView.getLayoutParams();
                        lp.height= (mRect.bottom - mRect.top) +dis;
                        mContentView.setLayoutParams(lp);
                        if(dis == 0 ) pulling = false;

                    }
                });


                Log.e(TAG,"layout :   mContentView.getTop(): "+mContentView.getTop()+"   mRect.top: "+mRect.top);

                rebound = false;
                break;
        }
        return super.

                dispatchTouchEvent(ev);
    }

/*    @Override
    public void setFillViewport(boolean fillViewport) {
        super.setFillViewport(true); //默认是填充ScrollView 或者再XML布局文件中设置fillViewport属性
    }*/

    /**
     * 判断当前ScrollView是否处于顶部
     */
    private boolean isScrollToTop() {
        return getScrollY() == 0;
    }

    /**
     * 判断当前ScrollView是否已滑到底部
     */
    private boolean isScrollToBottom() {
        return mContentView.getHeight() <= getHeight() + getScrollY();
    }

    /**
     * listener for top and bottom rebound
     * do your implement in the following methods
     */
    public interface OnReboundEndListener {

        void onDown();

        void onPull();

        void onRelease();

        void onReboundTopComplete();

        void onReboundBottomComplete();
    }
}
