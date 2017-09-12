package com.coinvs.testsurfaceview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerView;
    private Myadapter mMyadapter;
    private List<DataInfo> mDataInfos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDataInfos.add(new DataInfo("simple",new MyView(this)));
        mDataInfos.add(new DataInfo("sin",new sinview(this)));
        mDataInfos.add(new DataInfo("touch",new touchdraw(this)));
        PieView pieView = new PieView(this);

        ArrayList<PieData> data = new ArrayList<>();
        data.add(new PieData("harry",20));
        data.add(new PieData("lily",100));
        pieView.setData(data);
        mDataInfos.add(new DataInfo("pie",pieView));

        mDataInfos.add(new DataInfo("show1",ShowActivity1.class));
        mDataInfos.add(new DataInfo("show2",showActivity2.class));
        mDataInfos.add(new DataInfo("show712",show712.class));
        mDataInfos.add(new DataInfo("贝塞尔",BezierActivity.class));
        mDataInfos.add(new DataInfo("动画",AnimationActivity.class));
        mDataInfos.add(new DataInfo("CoordinateLayout",CoordinateLayoutActivity.class));



        mMyadapter = new Myadapter(this, mDataInfos, new Myadapter.ShowDelegate() {
            @Override
            public void show(int position) {
                Log.d(TAG, "show: ");
                if(position <= 3) {
                    newDialog(mDataInfos.get(position).mView);
                }else{
                 //   Intent intent=new Intent();
                   // Context context = MainActivity.this;
                    startActivity(new Intent(MainActivity.this, mDataInfos.get(position).moClass));
                /*    switch (position){
                        case 4:
                            intent.setClass(context,ShowActivity1.class);
                            break;
                        case 5:
                            intent.setClass(context,showActivity2.class);
                            break;
                        case 6:
                            intent.setClass(context,show712.class);
                            break;
                    }
                    context.startActivity(intent);*/

                }

            }
        });
        mRecyclerView.setAdapter(mMyadapter);

        //setContentView(R.layout.activity_main);
      //  setContentView(new MySurface(this, 200,200));
      //  setContentView(new sinview(this));
       // setContentView(new touchdraw(this));
    }

    private  class MyView extends SurfaceView implements SurfaceHolder.Callback
    {

        /**
         * @param context
         */
        SurfaceHolder holder;
        public MyView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
            holder=this.getHolder();
            holder.addCallback(this);
        }
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
            // TODO Auto-generated method stub

        }
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            // TODO Auto-generated method stub
            new Thread(new MyThread()).start();
        }
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            // TODO Auto-generated method stub

        }
        class MyThread implements Runnable
        {

            /* (non-Javadoc)
             * @see java.lang.Runnable#run()
             */
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Canvas canvas = holder.lockCanvas(null);//获取画布
                Paint mPaint = new Paint();
                mPaint.setColor(Color.BLUE);

                canvas.drawRect(new RectF(40,60,80,80), mPaint); //前2个参数代表右上角坐标，后2个参数代表右下角坐标
                holder.unlockCanvasAndPost(canvas);//解锁画布，提交画好的图像

            }

        }


    }


    private void newDialog(View view) {
        final AlertDialog ruleDialog = new AlertDialog.Builder(this).create();
        ruleDialog.setCancelable(true);
        ruleDialog.show();
        ruleDialog.getWindow().setContentView(view );



    }

}
