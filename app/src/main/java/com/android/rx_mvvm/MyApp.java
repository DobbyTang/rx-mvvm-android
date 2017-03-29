package com.android.rx_mvvm;

import com.rx.mvvmlibs.RxMvvmApplication;

import java.util.Optional;

/**
 * Created by guohao on 17/3/28.
 */

public class MyApp extends RxMvvmApplication{

    private static final String serverUrl = "https://api.weibo.com/";


    @Override
    public String setServerUrl() {
        return serverUrl;
    }

    @Override
    public int setDefaultDrawableResource() {
        return R.mipmap.test_error;
    }
}
