package com.rx.mvvmlibs.module;

import com.rx.mvvmlibs.ViewModel;

import dagger.Module;

/**
 * @ClassName: ModelModule
 * @author create by Tang
 * @date date 16/11/17 上午11:25
 * @Description: TODO
 */

@Module
public class ModelModule {

    private ViewModel viewModel;

    public ModelModule(ViewModel viewModel){
        this.viewModel = viewModel;
    }
}
