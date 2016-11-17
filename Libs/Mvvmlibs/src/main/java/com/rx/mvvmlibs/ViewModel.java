package com.rx.mvvmlibs;



import android.app.Activity;
import android.content.DialogInterface;
import android.databinding.ViewDataBinding;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.rx.mvvmlibs.bean.ProgressBean;
import com.rx.mvvmlibs.component.DaggerViewModelComponent;
import com.rx.mvvmlibs.module.ViewModelModule;
import com.rx.mvvmlibs.view.MvvmActivity;
import com.rx.utillibs.LogUtil;

/**
 * @ClassName: ViewModel
 * @author create by Tang
 * @date date 16/11/15 上午10:21
 * @Description: TODO
 */

public abstract class ViewModel<Data> implements IViewModel<Data>,IErrorInfo{


    public ViewModelWrapper viewModelWrapper;

    public ViewModel(MvvmActivity activity){
        init(activity);
    }


    @Override
    public void enqueue() {
        showProgress(true);
        viewModelWrapper.model.enqueueRequest();
    }

    @Override
    public void cancel() {
        showProgress(false);
        viewModelWrapper.model.cancelRequest();
    }

    @Override
    public void onSuccess() {
        showProgress(false);
    }

    @Override
    public void onError(Throwable e) {
        showProgress(false);
    }

    @Override
    public void showProgress(boolean enable) {
        switch (viewModelWrapper.progress.progressType){
            case ProgressBean.PROGRESS_TYPE_DEFAULT:
                if (enable){
                    viewModelWrapper
                            .defaultProgressBinding
                            .mvvmProgressBar
                            .setVisibility(View.VISIBLE);

                    viewModelWrapper.childBinding.getRoot().setVisibility(View.GONE);

                }else {
                    viewModelWrapper
                            .defaultProgressBinding
                            .mvvmProgressBar
                            .setVisibility(View.GONE);

                    viewModelWrapper.childBinding.getRoot().setVisibility(View.VISIBLE);


                }
                break;
            case ProgressBean.PROGRESS_TYPE_DIALOG:
                if (enable){
                    viewModelWrapper.progressDialog.show();
                }else {
                    viewModelWrapper.progressDialog.dismiss();
                }
                break;

            case ProgressBean.PROGRESS_TYPE_DROP_DOWN:
                viewModelWrapper.contentMvvmBinding.refreshLayout.setRefreshing(enable);
                break;
        }
    }

    @Override
    public void setProgressType(int type) {
        if (type != ProgressBean.PROGRESS_TYPE_DROP_DOWN){
            viewModelWrapper.contentMvvmBinding.refreshLayout.setEnabled(false);
        }else {
            viewModelWrapper.contentMvvmBinding.refreshLayout.setEnabled(true);
        }
        viewModelWrapper.progress.progressType = type;
    }

    @Override
    public int setErrorImageResource() {
        return 0;
    }

    @Override
    public String setErrorString() {
        return null;
    }

    @Override
    public void onError(int errorCode, String errorDesc) {

    }

    private void init(MvvmActivity activity){
        viewModelWrapper = new ViewModelWrapper();
        DaggerViewModelComponent.builder()
                .viewModelModule(new ViewModelModule(this,activity)).build()
                .inject(viewModelWrapper);

        viewModelWrapper.contentMvvmBinding.refreshLayout
                .setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LogUtil.d(this.getClass(), "onRefresh: ");
            }
        });


        viewModelWrapper.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                LogUtil.d(this.getClass(), "onCancel: ");
                cancel();

            }
        });

    }

}
