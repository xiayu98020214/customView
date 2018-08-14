package com.coinvs.testsurfaceview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.facebook.shimmer.ShimmerFrameLayout;

import static android.animation.ValueAnimator.REVERSE;

public class ReboundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rebound);
        final View hello = findViewById(R.id.shimmer_view_container);
        final ShimmerFrameLayout container =
                (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        final ObjectAnimator alpha = ObjectAnimator.ofFloat(hello, "translationY", 40);
        alpha.setDuration(2000);//设置动画时间
        alpha.setInterpolator(new OvershootInterpolator());//设置动画插入器，减速
        alpha.setRepeatCount(-1);//设置动画重复次数，这里-1代表无限
        alpha.setRepeatMode(REVERSE);//设置动画循环模式。
        alpha.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {


                container.startShimmer();
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                container.startShimmer();

            }
        });
        alpha.start();//启动动画。


        ReboundScrollView reboundScrollView = (ReboundScrollView)findViewById(R.id.rebound);
        final View pull =findViewById(R.id.pull);
        reboundScrollView.setOnReboundEndListener(new ReboundScrollView.OnReboundEndListener() {
            @Override
            public void onDown() {
                alpha.cancel();
            }

            @Override
            public void onPull() {
                pull.setVisibility(View.VISIBLE);


            }

            @Override
            public void onRelease() {
                pull.setVisibility(View.INVISIBLE);
                alpha.start();
            }

            @Override
            public void onReboundTopComplete() {

            }

            @Override
            public void onReboundBottomComplete() {

            }
        });
    }

}
