package com.rx.mvvmlibs.module;

import com.rx.mvvmlibs.IRetrofitViewModel;
import com.rx.mvvmlibs.scope.RetrofitModelScope;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Flowable;

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


}
