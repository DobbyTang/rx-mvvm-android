package com.rx.mvvmlibs;

import android.app.Application;
import android.databinding.ObservableField;
import android.util.Log;

import com.google.gson.Gson;
import com.rx.mvvmlibs.component.DaggerRetrofitComponent;
import com.rx.mvvmlibs.component.RetrofitComponent;
import com.rx.mvvmlibs.module.RetrofitModule;

import javax.inject.Inject;

import retrofit2.Retrofit;


/**
 * @ClassName: RxMvvmApplication
 * @author create by Tang
 * @date date 16/11/11 上午9:56
 * @Description: App初始化
 */

public class RxMvvmApplication extends Application {

    private static final String TAG = "RxMvvmApplication";
    private static RxMvvmApplication myApp;

    @Inject
    Retrofit retrofit;

    @Inject
    Gson gson;

    public static RxMvvmApplication getInstance(){
        return myApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        init();

    }

    protected void init(){
        initRetrofit();
    }

    public Retrofit getRetrofit(){
        Log.d(TAG, "getRetrofit: " + retrofit);
        return retrofit;
    }

    public void initRetrofit(){
        DaggerRetrofitComponent
                .builder()
                .retrofitModule(new RetrofitModule("http://apis.baidu.com",15))
                .build()
                .inject(this);
        Log.d(TAG, "init: " + retrofit);

    }

    public Gson getGson(){
        return gson;
    }




}
