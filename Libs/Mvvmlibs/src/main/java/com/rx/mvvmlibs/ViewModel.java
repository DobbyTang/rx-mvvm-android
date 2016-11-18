package com.rx.mvvmlibs;



import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
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

    private MvvmActivity activity;

    public abstract void result(Data resultData);

    public ViewModel(MvvmActivity activity){
        this.activity = activity;
        init();
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
    public void onReconnection() {
        LogUtil.d(ViewModel.this.getClass(), "onRefresh: ");
        if (viewModelWrapper.progress.progressType != ProgressBean.PROGRESS_TYPE_DROP_DOWN
                && viewModelWrapper.errorBinding.getRoot().getVisibility() == View.VISIBLE){
            viewModelWrapper.errorBinding.getRoot().setVisibility(View.GONE);
        }
        enqueue();
    }

    @Override
    public void onResult(Result<Data> result) {
        if (result.errNum == 0){
            result(result.data);
        }else {
            onError(result.errNum,result.errMsg);
        }
    }

    @Override
    public void onSuccess() {
        isSuccess = true;
        viewModelWrapper.childBinding.getRoot().setVisibility(View.VISIBLE);
        viewModelWrapper.errorBinding.getRoot().setVisibility(View.GONE);
        showProgress(false);
    }

    @Override
    public void onNetworkError(Throwable e) {
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
    public Drawable setErrorImageResource() {
        return activity.getResources().getDrawable(R.mipmap.ic_launcher);
    }

    @Override
    public String setErrorString() {
        return "网络错误，点击重新连接";
    }

    @Override
    public void onError(int errorCode, String errorDesc) {
        /**
         * 这里没有针对错误代码做处理
         * 如果需要根据错误代码处理特殊情况需要手动实现
         */
        showProgress(false);
        if (!isSuccess){
            viewModelWrapper.childBinding.getRoot().setVisibility(View.GONE);
            viewModelWrapper.errorBinding.getRoot().setVisibility(View.VISIBLE);
        }

    }


    @Override
    public void init(){
        viewModelWrapper = new ViewModelWrapper();
        DaggerViewModelComponent.builder()
                .viewModelModule(new ViewModelModule(this,activity)).build()
                .inject(viewModelWrapper);

        viewModelWrapper.errorBinding.setError(viewModelWrapper.error);
        activity.setSupportActionBar(viewModelWrapper.activityMvvmBinding.toolbar);
        setProgressType(ProgressBean.PROGRESS_TYPE_DEFAULT);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModelWrapper.contentMvvmBinding.refreshLayout

                .setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onReconnection();
            }
        });


        viewModelWrapper.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                cancel();

            }
        });

        viewModelWrapper.errorBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onReconnection();
            }
        });

    }

    public void setProgressType(int type) {
        if (type != ProgressBean.PROGRESS_TYPE_DROP_DOWN){
            viewModelWrapper.contentMvvmBinding.refreshLayout.setEnabled(false);
        }else {
            viewModelWrapper.contentMvvmBinding.refreshLayout.setEnabled(true);
        }
        viewModelWrapper.progress.progressType = type;
    }


}
