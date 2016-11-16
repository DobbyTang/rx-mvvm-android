package com.rx.mvvmlibs;



import android.app.Activity;
import android.view.View;

import com.rx.mvvmlibs.bean.ProgressBean;
import com.rx.mvvmlibs.component.DaggerViewModelComponent;
import com.rx.mvvmlibs.module.ViewModelModule;

/**
 * @ClassName: ViewModel
 * @author create by Tang
 * @date date 16/11/15 上午10:21
 * @Description: TODO
 */

public abstract class ViewModel<Data> implements IViewModel<Data>,IErrorInfo{

    public ViewModelWrapper viewModelWrapper;

    public ViewModel(Activity activity){
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

                }else {
                    viewModelWrapper
                            .defaultProgressBinding
                            .mvvmProgressBar
                            .setVisibility(View.GONE);
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
                if (enable){
                    viewModelWrapper.contentMvvmBinding.refreshLayout.setRefreshing(true);

                }else {
                    viewModelWrapper.contentMvvmBinding.refreshLayout.setRefreshing(false);
                }
                break;
        }
    }

    @Override
    public void setProgressType(int type) {
        if (type != ProgressBean.PROGRESS_TYPE_DROP_DOWN){
            viewModelWrapper.contentMvvmBinding.refreshLayout.setClickable(false);
        }else {
            viewModelWrapper.contentMvvmBinding.refreshLayout.setClickable(true);
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

    private void init(Activity activity){
        viewModelWrapper = new ViewModelWrapper();
        DaggerViewModelComponent.builder()
                .viewModelModule(new ViewModelModule(this,activity)).build()
                .inject(viewModelWrapper);

    }

}
