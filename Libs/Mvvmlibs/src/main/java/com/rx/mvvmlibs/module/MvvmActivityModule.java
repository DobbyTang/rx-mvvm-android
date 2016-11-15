package com.rx.mvvmlibs.module;

import android.app.Activity;
import android.databinding.DataBindingUtil;

import com.rx.mvvmlibs.R;
import com.rx.mvvmlibs.databinding.ActivityMvvmBinding;
import com.rx.mvvmlibs.databinding.ContentMvvmBinding;

import dagger.Module;
import dagger.Provides;

/**
 * @ClassName: MvvmActivityModule
 * @author create by Tang
 * @date date 16/11/15 下午3:15
 * @Description: TODO
 */

@Module
public class MvvmActivityModule {

    private Activity activity;
    private ActivityMvvmBinding mvvmBinding;

    public MvvmActivityModule(Activity activity){
        this.activity = activity;
    }

    @Provides
    public ActivityMvvmBinding providesActivityMvvmBinding(){
        this.mvvmBinding = DataBindingUtil.setContentView(activity, R.layout.activity_mvvm);
        return mvvmBinding;
    }

    @Provides
    public ContentMvvmBinding providesContentMvvmBinding(){
        return mvvmBinding.contentMvvm;
    }

}
