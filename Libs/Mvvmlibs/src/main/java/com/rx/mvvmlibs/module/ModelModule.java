package com.rx.mvvmlibs.module;

import com.rx.mvvmlibs.IRetrofitViewModel;
import com.rx.mvvmlibs.IViewModel;
import com.rx.mvvmlibs.Result;
import com.rx.utillibs.LogUtil;

import dagger.Module;
import dagger.Provides;
import rx.Subscriber;

/**
 * @ClassName: ModelModule
 * @author create by Tang
 * @date date 16/11/17 上午11:25
 * @Description: TODO
 */

@Module
public class ModelModule {

    private IRetrofitViewModel viewModel;

    public ModelModule(IRetrofitViewModel viewModel){
        this.viewModel = viewModel;
    }

}
