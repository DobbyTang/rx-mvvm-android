package com.rx.mvvmlibs.component;


import com.rx.mvvmlibs.ViewModelWrapper;
import com.rx.mvvmlibs.databinding.ActivityMvvmBinding;
import com.rx.mvvmlibs.databinding.ContentMvvmBinding;
import com.rx.mvvmlibs.module.ViewModelModule;
import com.rx.mvvmlibs.scope.ViewModelScope;
import com.rx.mvvmlibs.view.MvvmActivity;

import dagger.Component;

/**
 * @ClassName: ViewModelComponent
 * @author create by Tang
 * @date date 16/11/16 上午10:59
 * @Description:
 */

@ViewModelScope
@Component(modules = ViewModelModule.class)
public interface ViewModelComponent {

    void inject(ViewModelWrapper viewModelWrapper);

    ContentMvvmBinding getContentMvvmBinding();

}
