package com.rx.mvvmlibs.module;

import com.rx.mvvmlibs.ListViewModel;
import com.rx.mvvmlibs.view.ListMvvmActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @ClassName: ListMvvmActivityModule
 * @author create by Tang
 * @date date 16/11/18 下午3:57
 * @Description: TODO
 */

@Module
public class ListMvvmActivityModule {

    private ListMvvmActivity activity;

    public ListMvvmActivityModule( ListMvvmActivity activity){
        this.activity = activity;
    }

    @Provides
    public ListViewModel providesListViewModel(){
        return activity.onReBindingViewModel();
    }
}
