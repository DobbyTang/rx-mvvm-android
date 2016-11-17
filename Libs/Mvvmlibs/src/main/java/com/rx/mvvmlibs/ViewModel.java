package com.rx.mvvmlibs;



import android.content.DialogInterface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.rx.mvvmlibs.bean.ProgressBean;
import com.rx.mvvmlibs.component.DaggerViewModelComponent;
import com.rx.mvvmlibs.module.ViewModelModule;
import com.rx.mvvmlibs.network.Error;
import com.rx.mvvmlibs.view.MvvmActivity;
import com.rx.utillibs.LogUtil;

/**
 * @ClassName: ViewModel
 * @author create by Tang
 * @date date 16/11/15 上午10:21
 * @Description: TODO
 */

public abstract class ViewModel<Data> implements IViewModel<Data>,IErrorInfo{

    private boolean isSuccess;

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
        isSuccess = true;
        showProgress(false);
    }

    @Override
    public void onNetworkError(Throwable e) {
        showProgress(false);
        onError(Error.CODE_NETWORK,Error.DESC_NETWORK);
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
        /**
         * 这里没有针对错误代码做处理
         * 如果需要根据错误代码处理特殊情况需要手动实现
         */

        setErrorString();
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
