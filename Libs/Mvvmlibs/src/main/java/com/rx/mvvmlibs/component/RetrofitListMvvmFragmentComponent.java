package com.rx.mvvmlibs.component;

import com.rx.mvvmlibs.module.BindListViewModelModule;
import com.rx.mvvmlibs.view.RetrofitListMvvmFragment;

import dagger.Component;

/**
 * @ClassName: RetrofitListMvvmFragmentComponent
 * @author create by Tang
 * @date date 16/11/23 下午4:44
 * @Description: TODO
 */

@Component(modules = BindListViewModelModule.class)
public interface RetrofitListMvvmFragmentComponent {

    void inject(RetrofitListMvvmFragment fragment);
}
