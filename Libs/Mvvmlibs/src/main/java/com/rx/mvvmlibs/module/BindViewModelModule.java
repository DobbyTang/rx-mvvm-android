package com.rx.mvvmlibs.module;


import com.rx.mvvmlibs.RetrofitViewModel;
import com.rx.mvvmlibs.view.iview.BindViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * @ClassName: BindViewModelModule
 * @author create by Tang
 * @date date 16/11/15 下午3:15
 * @Description: TODO
 */

@Module
public class BindViewModelModule {

    private BindViewModel mBinding;

    public BindViewModelModule(BindViewModel binding){
        this.mBinding = binding;
    }


    @Provides
    public RetrofitViewModel providesViewModel(){
        return  mBinding.onBindingViewModel();
    }

}
