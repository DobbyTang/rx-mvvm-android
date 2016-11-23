package com.rx.mvvmlibs.component;


import com.rx.mvvmlibs.ViewModelWrapper;
import com.rx.mvvmlibs.databinding.ContentRetrofitMvvmBinding;
import com.rx.mvvmlibs.module.ViewModelModule;
import com.rx.mvvmlibs.scope.ViewModelScope;

import dagger.Component;

/**
 * @ClassName: RetrofitViewModelComponent
 * @author create by Tang
 * @date date 16/11/16 上午10:59
 * @Description:
 */

@ViewModelScope
@Component(modules = ViewModelModule.class)
public interface RetrofitViewModelComponent {

    void inject(ViewModelWrapper viewModelWrapper);

    ContentRetrofitMvvmBinding getContentMvvmBinding();

}
