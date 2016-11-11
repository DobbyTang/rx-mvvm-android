package com.rx.mvvmlibs;

import android.app.Application;
import android.util.Log;

import com.rx.mvvmlibs.component.DaggerRetrofitComponent;
import com.rx.mvvmlibs.component.RetrofitComponent;

import javax.inject.Inject;

import retrofit2.Retrofit;


/**
 * @ClassName: com.rx.mvvmlibs.RxMvvmApplication
 * @author create by Tang
 * @date date 16/11/11 上午9:56
 * @Description: App初始化
 */

public class RxMvvmApplication extends Application {

    private static final String TAG = "RxMvvmApplication";
    private static RxMvvmApplication myApp;

    private RetrofitComponent mvvmAppComponent;

    @Inject
    Retrofit retrofit;

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
        reSetRetrofit();
    }

    public Retrofit getRetrofit(){
        Log.d(TAG, "getRetrofit: " + retrofit);
        return retrofit;
    }

    public void reSetRetrofit(){
        mvvmAppComponent = DaggerRetrofitComponent.builder().build();
        mvvmAppComponent.inject(this);
        Log.d(TAG, "init: " + retrofit);
    }




}
