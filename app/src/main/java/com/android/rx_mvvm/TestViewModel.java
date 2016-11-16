package com.android.rx_mvvm;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.rx.mvvmlibs.Result;
import com.rx.mvvmlibs.RxMvvmApplication;
import com.rx.mvvmlibs.ViewModel;

import rx.Observable;

/**
 * @ClassName: TestViewModel
 * @author create by Tang
 * @date date 16/11/15 下午4:56
 * @Description: TODO
 */

public class TestViewModel extends ViewModel{

    private static final String TAG = "TestViewModel";

    public TestViewModel(Activity activity) {
        super(activity);
    }

    @Override
    public Observable setApiInterface() {
        return RxMvvmApplication.getInstance().getRetrofit().create(ApiNuoMi.class).rxGetCategory();
    }

    @Override
    public void result(Result result) {
        Log.d(TAG, "result: ");
    }

    public void testClick(View view){
        Log.d(TAG, "testClick: ");
        enqueue();
    }
}
