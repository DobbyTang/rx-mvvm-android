package com.rx.mvvmlibs.component;


import com.rx.mvvmlibs.ViewModelWrapper;
import com.rx.mvvmlibs.module.ViewModelModule;

import dagger.Component;

/**
 * @ClassName: ViewModelComponent
 * @author create by Tang
 * @date date 16/11/16 上午10:59
 * @Description:
 */

@Component(modules = ViewModelModule.class)
public interface ViewModelComponent {

    void inject(ViewModelWrapper viewModelWrapper);
}
