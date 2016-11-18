package com.rx.mvvmlibs;

import android.graphics.drawable.Drawable;

import rx.Observable;

/**
 * @ClassName: ListViewModel
 * @author create by Tang
 * @date date 16/11/18 下午3:49
 * @Description: 用于处理列表型数据
 */

public class ListViewModel<Data> implements IViewModel<Data>,IErrorInfo{
    @Override
    public void enqueue() {

    }

    @Override
    public void cancel() {

    }

    @Override
    public Observable setApiInterface() {
        return null;
    }

    @Override
    public void onReconnection() {

    }

    @Override
    public void onResult(Result<Data> result) {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onNetworkError(Throwable e) {

    }

    @Override
    public void showProgress(boolean enable) {

    }

    @Override
    public void init() {

    }

    @Override
    public Drawable setErrorImageResource() {
        return null;
    }

    @Override
    public String setErrorString() {
        return null;
    }

    @Override
    public void onError(int errorCode, String errorDesc) {

    }
}
