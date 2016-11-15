package com.rx.mvvmlibs;



import com.rx.mvvmlibs.bean.ProgressBean;

/**
 * @ClassName: ViewModel
 * @author create by Tang
 * @date date 16/11/15 上午10:21
 * @Description: TODO
 */

public abstract class ViewModel<Data> implements IViewModel<Data>,IErrorInfo{

    public ProgressBean progress;

    private IModel model;

    public ViewModel(){
        progress = new ProgressBean();
        this.model = new Model<Data>(this);
    }

    @Override
    public void enqueue() {
        progress.showProgress.set(true);
        model.enqueueRequest();
    }

    @Override
    public void cancel() {
        model.cancelRequest();
    }

    @Override
    public void onSuccess() {
        progress.showProgress.set(false);
    }

    @Override
    public void onError(Throwable e) {
        progress.showProgress.set(false);
    }



    @Override
    public void setProgressType(int type) {
        progress.setProgressType(type);
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
}
