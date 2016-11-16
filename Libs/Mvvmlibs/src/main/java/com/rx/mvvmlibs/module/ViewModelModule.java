package com.rx.mvvmlibs.module;

import android.app.Activity;
import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;


import com.rx.mvvmlibs.IModel;
import com.rx.mvvmlibs.IViewModel;
import com.rx.mvvmlibs.Model;
import com.rx.mvvmlibs.R;
import com.rx.mvvmlibs.databinding.ActivityMvvmBinding;
import com.rx.mvvmlibs.databinding.ContentMvvmBinding;
import com.rx.mvvmlibs.databinding.DefaultProgressBinding;
import com.rx.mvvmlibs.databinding.ErrorBinding;

import dagger.Module;
import dagger.Provides;

/**
 * @ClassName: ViewModelModule
 * @author create by Tang
 * @date date 16/11/16 上午10:41
 * @Description: TODO
 */

@Module
public class ViewModelModule {

    private Activity activity;
    private IViewModel viewModel;
    private ActivityMvvmBinding activityMvvmBinding;
    private ContentMvvmBinding contentMvvmBinding;

    public ViewModelModule(IViewModel viewModel, Activity activity){
        this.activity = activity;
        this.viewModel = viewModel;
    }

    @Provides
    public ActivityMvvmBinding providesActivityMvvmBinding(){
        activityMvvmBinding = DataBindingUtil.setContentView(activity, R.layout.activity_mvvm);
        return activityMvvmBinding;
    }

    @Provides
    public ContentMvvmBinding providesContentMvvmBinding(){
        contentMvvmBinding = activityMvvmBinding.contentMvvm;
        return contentMvvmBinding;
    }

    @Provides
    public ErrorBinding providesErrorBinding(){
        return contentMvvmBinding.mvvmError;
    }

    @Provides
    public DefaultProgressBinding providesProgressBinding(){
        return contentMvvmBinding.mvvmDefaultProgress;
    }

    @Provides
    public IModel providesModel(){
        return new Model<>(viewModel);
    }

    @Provides
    public ProgressDialog providesProgressDialog(){
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setTitle("加载中");
        return dialog;
    }
}
