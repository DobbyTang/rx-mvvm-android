package com.rx.mvvmlibs;



import android.app.Activity;

import com.rx.mvvmlibs.component.DaggerViewModelComponent;
import com.rx.mvvmlibs.module.ViewModelModule;

/**
 * @ClassName: ViewModel
 * @author create by Tang
 * @date date 16/11/15 上午10:21
 * @Description: TODO
 */

public abstract class ViewModel<Data> implements IViewModel<Data>,IErrorInfo{

    private ViewModelWrapper viewModelWrapper;

    public ViewModel(Activity activity){
        init(activity);
    }

    @Override
    public void enqueue() {
        viewModelWrapper.progress.showProgress.set(true);
        viewModelWrapper.model.enqueueRequest();
    }

    @Override
    public void cancel() {
        viewModelWrapper.model.cancelRequest();
    }

    @Override
    public void onSuccess() {
        viewModelWrapper.progress.showProgress.set(false);
    }

    @Override
    public void onError(Throwable e) {
        viewModelWrapper. progress.showProgress.set(false);
    }



    @Override
    public void setProgressType(int type) {
        viewModelWrapper.progress.setProgressType(type);
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
