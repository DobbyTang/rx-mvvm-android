package com.rx.mvvmlibs.component;

import android.app.ProgressDialog;

import com.rx.mvvmlibs.IModel;
import com.rx.mvvmlibs.ViewModelWrapper;
import com.rx.mvvmlibs.databinding.ActivityMvvmBinding;
import com.rx.mvvmlibs.databinding.ContentMvvmBinding;
import com.rx.mvvmlibs.databinding.DefaultProgressBinding;
import com.rx.mvvmlibs.databinding.ErrorBinding;
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
