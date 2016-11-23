package com.rx.mvvmlibs.component;

import com.rx.mvvmlibs.module.BindViewModelModule;
import com.rx.mvvmlibs.view.RetrofitMvvmActivity;

import dagger.Component;

/**
 * @ClassName: RetrofitMvvmActivityComponent
 * @author create by Tang
 * @date date 16/11/15 下午3:20
 * @Description: TODO
 */

@Component(modules = BindViewModelModule.class)
public interface RetrofitMvvmActivityComponent {

    void inject(RetrofitMvvmActivity bindViewModel);
}
