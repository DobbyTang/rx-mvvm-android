package com.rx.mvvmlibs.component;

import com.rx.mvvmlibs.RxMvvmApplication;
import com.rx.mvvmlibs.module.RetrofitModule;
import com.rx.mvvmlibs.scope.ApplicationScope;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * @ClassName: RetrofitComponent
 * @author create by Tang
 * @date date 16/11/11 上午11:31
 * @Description: TODO
 */

@Singleton
@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {

    void inject(RxMvvmApplication application);

    Retrofit getRetrofit();
}
