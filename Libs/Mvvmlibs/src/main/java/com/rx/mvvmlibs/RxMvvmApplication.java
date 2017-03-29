package com.rx.mvvmlibs;

import android.app.Application;
import android.support.annotation.DrawableRes;
import android.util.Log;

import com.google.gson.Gson;
import com.rx.mvvmlibs.component.DaggerRetrofitComponent;
import com.rx.mvvmlibs.module.RetrofitModule;
import com.rx.mvvmlibs.network.BaseParamsInterceptor;

import java.util.Optional;

import javax.inject.Inject;

import retrofit2.Retrofit;


/**
 * @ClassName: RxMvvmApplication
 * @author create by Tang
 * @date date 16/11/11 上午9:56
 * @Description: App初始化
 */

public abstract class RxMvvmApplication extends Application {

    private static final String TAG = "RxMvvmApplication";
    private static RxMvvmApplication myApp;

    public abstract String setServerUrl();

    public abstract @DrawableRes int setDefaultDrawableResource();

    @Inject
    Retrofit retrofit;

    @Inject
    BaseParamsInterceptor.Builder interceptorBuilder;

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


    private void initRetrofit(){
        Optional<String> serverUrl = Optional.ofNullable(setServerUrl());
        DaggerRetrofitComponent
                .builder()
                .retrofitModule(new RetrofitModule(serverUrl.orElseGet(() -> "http://tangpj.com/"),15))
                .build()
                .inject(this);

    }

    public Retrofit getRetrofit(){
        return retrofit;
    }

    public BaseParamsInterceptor.Builder getInterceptorBuilder(){
        return interceptorBuilder;
    }

    public Gson getGson(){
        return gson;
    }

}
