package com.rx.mvvmlibs.component;

import com.rx.mvvmlibs.module.BindViewModelModule;
import com.rx.mvvmlibs.view.MvvmActivity;

import dagger.Component;

/**
 * @ClassName: MvvmActivityComponent
 * @author create by Tang
 * @date date 16/11/15 下午3:20
 * @Description: TODO
 */

@Component(modules = BindViewModelModule.class)
public interface MvvmActivityComponent {

    void inject(MvvmActivity bindViewModel);
}
