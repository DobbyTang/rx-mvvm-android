package com.rx.mvvmlibs.component;

import com.rx.mvvmlibs.module.BindListViewModelModule;
import com.rx.mvvmlibs.view.RetrofitListMvvmActivity;

import dagger.Component;

/**
 * @ClassName: RetrofitListMvvmActivityComponent
 * @author create by Tang
 * @date date 16/11/19 下午3:54
 * @Description: TODO
 */

@Component(modules = BindListViewModelModule.class)
public interface RetrofitListMvvmActivityComponent {

    void inject(RetrofitListMvvmActivity listMvvmActivity);
}
