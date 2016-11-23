package com.rx.mvvmlibs.module;

import com.rx.mvvmlibs.RetrofitListViewModel;
import com.rx.mvvmlibs.view.iview.BindListViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * @ClassName: BindListViewModelModule
 * @author create by Tang
 * @date date 16/11/18 下午3:57
 * @Description: TODO
 */

@Module
public class BindListViewModelModule {

    private BindListViewModel bindListViewModel;

    public BindListViewModelModule(BindListViewModel bindListViewModel){
        this.bindListViewModel = bindListViewModel;
    }

    @Provides
    public RetrofitListViewModel providesListViewModel(){
        return bindListViewModel.onBindingViewModel();
    }
}
