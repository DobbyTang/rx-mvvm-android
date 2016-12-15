package com.rx.mvvmlibs.component;

import com.rx.mvvmlibs.Result;
import com.rx.mvvmlibs.RetrofitModel;
import com.rx.mvvmlibs.module.RetrofitModelModule;
import com.rx.mvvmlibs.module.RetrofitModule;
import com.rx.mvvmlibs.scope.RetrofitScope;

import dagger.Component;
import retrofit2.Retrofit;
import org.reactivestreams.Subscriber;

/**
 * @ClassName: RetrofitModelComponent
 * @author create by Tang
 * @date date 16/11/21 上午9:37
 * @Description: TODO
 */

@RetrofitScope
@Component(modules = {RetrofitModule.class,RetrofitModelModule.class})
public interface RetrofitModelComponent {

    void inject(RetrofitModel model);

    Retrofit getRetrofit();


}
