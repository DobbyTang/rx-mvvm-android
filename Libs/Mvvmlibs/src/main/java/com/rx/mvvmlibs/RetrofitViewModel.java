package com.rx.mvvmlibs;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.rx.mvvmlibs.bean.ProgressBean;
import com.rx.mvvmlibs.component.DaggerRetrofitViewModelComponent;
import com.rx.mvvmlibs.databinding.ActivityRetrofitMvvmBinding;
import com.rx.mvvmlibs.module.ViewModelModule;
import com.rx.mvvmlibs.network.Error;
import com.rx.mvvmlibs.view.RetrofitMvvmActivity;
import com.rx.mvvmlibs.view.RetrofitMvvmFragment;
import com.rx.utillibs.LogUtil;

/**
 * @ClassName: RetrofitViewModel
 * @author create by Tang
 * @date date 16/11/15 上午10:21
 * @Description: TODO
 */

public abstract class RetrofitViewModel<Data> implements IViewModel<Data>,IErrorInfo{

    private boolean isSuccess;

    public ViewModelWrapper viewModelWrapper = new ViewModelWrapper();

    private Context context;

    public RetrofitViewModel(RetrofitMvvmActivity activity){
        this.context = activity;
        initActivity(activity);

    }

    public RetrofitViewModel(RetrofitMvvmFragment fragment){
        this.context = fragment.getContext();
        initFragment(fragment);
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
    public void onResult(Result<Data> result) {
        if (result.errNum == 0){
            result(result.data);
            onSuccess();
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
    public void setErrorImageResource(Drawable drawable) {
        viewModelWrapper.error.drawable.set(drawable);
    }

    @Override
    public void setErrorString(String msg) {
        viewModelWrapper.error.message.set(msg);
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
            setErrorString(errorDesc);
        }

    }


    @Override
    public void init(){

        viewModelWrapper.error.drawable.set(context.getResources().getDrawable(R.mipmap.ic_launcher));
        viewModelWrapper.error.message.set("网络错误，请重新加载");
        viewModelWrapper.errorBinding.setError(viewModelWrapper.error);

        setProgressType(ProgressBean.PROGRESS_TYPE_DEFAULT);
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

    public abstract void result(Data resultData);


    /**
     * @Method: onReconnection
     * @author create by Tang
     * @date date 16/11/19 下午3:40
     * @Description: 重连事件
     */
    public void onReconnection() {
        LogUtil.d( "onRefresh: ");
        if (viewModelWrapper.progress.progressType != ProgressBean.PROGRESS_TYPE_DROP_DOWN
                && viewModelWrapper.errorBinding.getRoot().getVisibility() == View.VISIBLE){
            viewModelWrapper.errorBinding.getRoot().setVisibility(View.GONE);
        }
        enqueue();
    }

    private void initActivity(RetrofitMvvmActivity activity){
        ActivityRetrofitMvvmBinding activityMvvmBinding
                = DataBindingUtil.setContentView(activity,R.layout.activity_retrofit_mvvm);
        DaggerRetrofitViewModelComponent.builder()
                .viewModelModule(new ViewModelModule(this,activity,activityMvvmBinding.contentMvvm))
                .build()
                .inject(viewModelWrapper);
        activity.setSupportActionBar(activityMvvmBinding.toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
    }

    private void initFragment(RetrofitMvvmFragment fragment){

        DaggerRetrofitViewModelComponent.builder()
                .viewModelModule(new ViewModelModule(this,fragment.getContext()
                        ,fragment.getContentMvvmBinding()))
                .build()
                .inject(viewModelWrapper);

        init();
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
