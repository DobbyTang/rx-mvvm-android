package com.rx.mvvmlibs.component;

import com.rx.mvvmlibs.module.BindViewModelModule;
import com.rx.mvvmlibs.view.RetrofitMvvmFragment;

import dagger.Component;

/**
 * @ClassName: RetrofitMvvmFragmentComponent
 * @author create by Tang
 * @date date 16/11/23 下午2:12
 * @Description: TODO
 */

@Component(modules = BindViewModelModule.class)
public interface RetrofitMvvmFragmentComponent {

    void inject(RetrofitMvvmFragment fragment);
}
