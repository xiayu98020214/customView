package com.coinvs.testsurfaceview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 七喜 on 2017/5/5.
 */

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder>{

    private static final String TAG = "Myadapter";
    public interface ShowDelegate {//定义委托接口
        void show(int position);
    }

    private List<DataInfo> mDatas;
    private Context mContext;
    private ShowDelegate mShowDelegate;
    public Myadapter(Context context,List<DataInfo> datas,ShowDelegate delegate){
        mDatas = datas;
        mContext = context;
        mShowDelegate = delegate;
    }

    @Override
    public Myadapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
/*        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item, parent,
                false));*/

        Log.d(TAG, "onCreateViewHolder() called with: parent = [" + parent + "], viewType = [" + viewType + "]");
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
  /*      RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);*/
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Myadapter.MyViewHolder holder, final int position) {
        holder.mTitle.setText(mDatas.get(position).mTitle);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mShowDelegate.show(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();

    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;

        public MyViewHolder(View convertView) {
            super(convertView);
            mTitle = (TextView) convertView.findViewById(R.id.title);
        }

    }
}
