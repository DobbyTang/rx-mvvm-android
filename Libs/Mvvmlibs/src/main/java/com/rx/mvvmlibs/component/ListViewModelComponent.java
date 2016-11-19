package com.rx.mvvmlibs.component;

import com.rx.mvvmlibs.ListViewModelWrapper;
import com.rx.mvvmlibs.module.ListMvvmActivityModule;
import com.rx.mvvmlibs.module.ListViewModelModule;
import com.rx.mvvmlibs.view.ListMvvmActivity;

import dagger.Component;

/**
 * @ClassName: ListViewModelComponent
 * @author create by Tang
 * @date date 16/11/18 下午4:03
 * @Description: TODO
 */

@Component(modules = ListViewModelModule.class)
public interface ListViewModelComponent {

    void inject(ListViewModelWrapper listViewModelWrapper);
}
