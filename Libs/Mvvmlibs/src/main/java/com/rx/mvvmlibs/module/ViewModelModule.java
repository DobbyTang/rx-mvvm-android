package com.rx.mvvmlibs.module;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
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
import com.rx.mvvmlibs.view.iview.BindingViewModel;
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

    private ContentMvvmBinding contentMvvmBinding;
    private Context context;
    private IViewModel viewModel;
    private BindingViewModel bindingViewModel;

    public ViewModelModule(ViewModel viewModel, MvvmActivity activity, ContentMvvmBinding contentMvvmBinding){
        this.viewModel = viewModel;
        this.context = activity;
        this.contentMvvmBinding = contentMvvmBinding;
        this.bindingViewModel = activity;
    }


//
//    @ViewModelScope
//    @Provides
//    public ActivityMvvmBinding providesActivityMvvmBinding(){
//        return DataBindingUtil.setContentView(activity, R.layout.activity_mvvm);
//    }

    @ViewModelScope
    @Provides
    public ContentMvvmBinding providesContentMvvmBinding(){
        return contentMvvmBinding;
    }

    @ViewModelScope
    @Provides
    ViewDataBinding providesChildBinding(ContentMvvmBinding contentMvvmBinding){

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);

        ViewDataBinding childBinding = bindingViewModel.onCreateBinding(LayoutInflater.from(context)
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
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setTitle("加载中");
        return dialog;
    }
}
