package com.rx.mvvmlibs.module;

import com.rx.mvvmlibs.IRetrofitViewModel;
import com.rx.mvvmlibs.RxMvvmApplication;
import com.rx.mvvmlibs.network.BaseParamsInterceptor;
import com.rx.mvvmlibs.scope.RetrofitModelScope;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @ClassName: RetrofitModelModule
 * @author create by Tang
 * @date date 16/11/17 上午11:25
 * @Description: TODO
 */

@Module
public class RetrofitModelModule {

    private IRetrofitViewModel viewModel;

    public RetrofitModelModule(IRetrofitViewModel viewModel){
        this.viewModel = viewModel;
    }

    @Provides
    @RetrofitModelScope
    public Retrofit providesRetrofit(){
        return RxMvvmApplication.getInstance().getRetrofit();
    }

    @Provides
    @RetrofitModelScope
    public BaseParamsInterceptor.Builder providesBuilder(){
        return RxMvvmApplication.getInstance().getInterceptorBuilder();
    }



}
