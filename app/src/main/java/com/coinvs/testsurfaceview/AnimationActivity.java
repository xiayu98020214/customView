package com.coinvs.testsurfaceview;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import static android.animation.ValueAnimator.REVERSE;

/**
 * Created by 七喜 on 2017/5/27.
 */

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        final TextView text = (TextView)findViewById(R.id.text);

        AlphaAnimation aa = new AlphaAnimation(0,1);
        aa.setDuration(1000);
        text.startAnimation(aa);

        TextView text1 = (TextView)findViewById(R.id.text1);
        RotateAnimation ra = new RotateAnimation(0,270,100,100);
        ra.setDuration(2000);
        text1.startAnimation(ra);


        TextView text2 = (TextView)findViewById(R.id.text2);
        TranslateAnimation ta = new TranslateAnimation(0,200,0,300);
        ta.setDuration(2000);
        text2.startAnimation(ta);

        TextView text3 = (TextView)findViewById(R.id.text3);
        ScaleAnimation sa = new ScaleAnimation(0,1,0,1);
        sa.setDuration(2000);
        text3.startAnimation(sa);

        TextView text4 = (TextView)findViewById(R.id.text4);
        ScaleAnimation sa2 = new ScaleAnimation(0,1,0,1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        sa2.setDuration(2000);
        text4.startAnimation(sa2);




        TextView text5 = (TextView)findViewById(R.id.text5);

        AnimationSet as = new AnimationSet(true);
        as.setDuration(6000);

        TranslateAnimation ta2 = new TranslateAnimation(0,200,0,300);
        ta2.setDuration(6000);

        as.addAnimation(ta2);

        RotateAnimation ra2 = new RotateAnimation(0,360,50,50);
        ra2.setDuration(6000);
        as.addAnimation(ra2);
        text5.startAnimation(as);


        final TextView text6 = (TextView)findViewById(R.id.text6);
        text6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(text6.getText().equals("变")){
                   text6.setText("hihi");
               }else {
                   text6.setText("变");

               }


            }
        });

        ObjectAnimator alpha = ObjectAnimator.ofFloat(text6, "translationX", 300);
        alpha.setDuration(2000);//设置动画时间
        alpha.setInterpolator(new DecelerateInterpolator());//设置动画插入器，减速
        alpha.setRepeatCount(-1);//设置动画重复次数，这里-1代表无限
        alpha.setRepeatMode(REVERSE);//设置动画循环模式。
        alpha.start();//启动动画。

    }


/*    private static class WrapperView {
        private View mTarget;
        public WrapperView(View target){
            mTarget = target;
        }

        public int getY(){

        }
    }*/
}