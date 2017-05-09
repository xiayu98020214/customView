package com.coinvs.testsurfaceview;

import android.view.View;

/**
 * Created by 七喜 on 2017/5/5.
 */

public class DataInfo {
    public String mTitle;
    public View mView;
    public Class<?> moClass;

    public DataInfo(String title, View view){
        mTitle = title;
        mView = view;
    }

    public DataInfo(String title, Class<?> go) {
        mTitle = title;
        moClass = go;
    }

    public DataInfo(String show1, Object o) {
    }
}
