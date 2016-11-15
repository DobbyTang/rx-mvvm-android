package com.rx.mvvmlibs.module;

import android.databinding.ObservableField;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.TypeAdapters;
import com.rx.mvvmlibs.internal.ObservableFieldTypeAdapter;
import com.rx.mvvmlibs.network.HeaderInterceptor;
import com.rx.mvvmlibs.scope.ApplicationScope;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ClassName: RetrofitModule
 * @author create by Tang
 * @date date 16/11/11 上午11:27
 * @Description: TODO
 */

@Module
public class RetrofitModule {

    private String SERVER_PRODUCTION = "http://apis.baidu.com";

    @ApplicationScope
    @Provides
    public Retrofit providesRetrofit(){
        return new Retrofit.Builder().baseUrl(SERVER_PRODUCTION)
                .addConverterFactory(initGsonConverterFactory())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(initOkHttpClient())
                .build();

    }

    @ApplicationScope
    @Provides
    public Gson initGson(){
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .registerTypeAdapterFactory(TypeAdapters.newFactory(ObservableField.class, new ObservableFieldTypeAdapter()))
                .create();
    }


    private GsonConverterFactory initGsonConverterFactory(){
        return GsonConverterFactory.create(initGson());
    }


    private OkHttpClient initOkHttpClient(){
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new HeaderInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
    }






}
