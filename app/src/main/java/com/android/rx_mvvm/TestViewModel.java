package com.android.rx_mvvm;

import com.rx.mvvmlibs.Result;
import com.rx.mvvmlibs.ViewModel;

import rx.Observable;

/**
 * @ClassName: TestViewModel
 * @author create by Tang
 * @date date 16/11/15 下午4:56
 * @Description: TODO
 */

public class TestViewModel extends ViewModel{
    @Override
    public Observable setApiInterface() {
        return null;
    }

    @Override
    public void result(Result result) {

    }

    @Override
    public void setProgressType(int type) {

    }
}
