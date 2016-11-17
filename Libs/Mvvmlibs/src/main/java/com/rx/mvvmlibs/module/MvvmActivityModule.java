package com.rx.mvvmlibs.module;


import com.rx.mvvmlibs.ViewModel;
import com.rx.mvvmlibs.databinding.ContentMvvmBinding;
import com.rx.mvvmlibs.view.MvvmActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @ClassName: MvvmActivityModule
 * @author create by Tang
 * @date date 16/11/15 下午3:15
 * @Description: TODO
 */

@Module
public class MvvmActivityModule {

    private MvvmActivity activity;

    private ViewModel viewModel;

    public MvvmActivityModule(MvvmActivity activity){
        this.activity = activity;
    }

    @Provides
    public ViewModel providesViewModel(){
        viewModel = activity.onBindingViewModel();
        return viewModel;
    }

}
