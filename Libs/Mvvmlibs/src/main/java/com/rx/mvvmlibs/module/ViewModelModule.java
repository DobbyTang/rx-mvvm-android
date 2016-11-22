package com.rx.mvvmlibs.module;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.widget.FrameLayout;


import com.rx.mvvmlibs.IErrorInfo;
import com.rx.mvvmlibs.IModel;
import com.rx.mvvmlibs.IViewModel;
import com.rx.mvvmlibs.Model;
import com.rx.mvvmlibs.R;
import com.rx.mvvmlibs.ViewModel;
import com.rx.mvvmlibs.bean.ErrorBean;
import com.rx.mvvmlibs.databinding.ActivityMvvmBinding;
import com.rx.mvvmlibs.databinding.ContentMvvmBinding;
import com.rx.mvvmlibs.databinding.DefaultProgressBinding;
import com.rx.mvvmlibs.databinding.ErrorBinding;
import com.rx.mvvmlibs.scope.ViewModelScope;
import com.rx.mvvmlibs.view.MvvmActivity;
import com.rx.mvvmlibs.view.MvvmFragment;
import com.rx.utillibs.LogUtil;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * @ClassName: ViewModelModule
 * @author create by Tang
 * @date date 16/11/16 上午10:41
 * @Description: TODO
 */

@Module
public class ViewModelModule {

    private MvvmActivity activity;
    private MvvmFragment fragment;
    private IViewModel viewModel;

    public ViewModelModule(ViewModel viewModel, MvvmActivity activity){
        this.activity = activity;
        this.viewModel = viewModel;
    }

    public ViewModelModule(ViewModel viewModel, MvvmFragment fragment){
        this.fragment = fragment;
        this.viewModel = viewModel;
    }

    @ViewModelScope
    @Provides
    public ActivityMvvmBinding providesActivityMvvmBinding(){
        return DataBindingUtil.setContentView(activity, R.layout.activity_mvvm);
    }

    @ViewModelScope
    @Provides
    public ContentMvvmBinding providesContentMvvmBinding(ActivityMvvmBinding activityMvvmBinding){
        return activityMvvmBinding.contentMvvm;
    }

    @ViewModelScope
    @Provides
    ViewDataBinding providesChildBinding(ContentMvvmBinding contentMvvmBinding){
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);

        ViewDataBinding childBinding = activity.onCreateBinding(activity.getLayoutInflater()
                ,contentMvvmBinding.mvvmFrameLayout);
        if (childBinding != null ){
            contentMvvmBinding.mvvmFrameLayout
                    .addView(childBinding.getRoot(),lp);
        }
        return childBinding;
    }


    @ViewModelScope
    @Provides
    public ErrorBinding providesErrorBinding(ContentMvvmBinding contentMvvmBinding){
        return contentMvvmBinding.mvvmError;
    }

    @ViewModelScope
    @Provides
    public DefaultProgressBinding providesProgressBinding(ContentMvvmBinding contentMvvmBinding){
        return contentMvvmBinding.mvvmDefaultProgress;
    }

    @ViewModelScope
    @Provides
    public ErrorBean providesError(){
        return new ErrorBean();
    }

    @ViewModelScope
    @Provides
    public IModel providesModel(){
        return new Model(viewModel);
    }

    @ViewModelScope
    @Provides
    public ProgressDialog providesProgressDialog(){
        ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setTitle("加载中");
        return dialog;
    }
}
