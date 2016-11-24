package com.rx.mvvmlibs.component;

import com.rx.mvvmlibs.module.BindListViewModelModule;
import com.rx.mvvmlibs.view.ListMvvmFragment;

import dagger.Component;

/**
 * @ClassName: ListMvvmFragmentComponent
 * @author create by Tang
 * @date date 16/11/23 下午4:44
 * @Description: TODO
 */

@Component(modules = BindListViewModelModule.class)
public interface ListMvvmFragmentComponent {

    void inject(ListMvvmFragment fragment);
}
