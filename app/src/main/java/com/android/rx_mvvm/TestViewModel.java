package com.android.rx_mvvm;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.rx.mvvmlibs.Result;
import com.rx.mvvmlibs.RxMvvmApplication;
import com.rx.mvvmlibs.ViewModel;
import com.rx.mvvmlibs.bean.ProgressBean;
import com.rx.mvvmlibs.view.MvvmActivity;
import com.rx.utillibs.LogUtil;

import java.util.List;

import rx.Observable;

/**
 * @ClassName: TestViewModel
 * @author create by Tang
 * @date date 16/11/15 下午4:56
 * @Description: TODO
 */

public class TestViewModel extends ViewModel<List<NuoMiCategoryBean>>{


    private Observable nuomi;

    public TestViewModel(TestMvvmActivity activity) {
        super(activity);
        nuomi = RxMvvmApplication.getInstance().getRetrofit().create(ApiNuoMi.class).rxGetCategory();
    }

    @Override
    public Observable setApiInterface() {
        return nuomi;
    }

    @Override
    public void result(Result<List<NuoMiCategoryBean>> result) {
        LogUtil.d(this.getClass(), "result: " + result.data.get(0).cat_name.get());
        Snackbar.make(viewModelWrapper.childBinding.getRoot()
                ,result.data.get(0).cat_name.get(),Snackbar.LENGTH_SHORT).show();
    }


    public void defaultClick(View view){
        setProgressType(ProgressBean.PROGRESS_TYPE_DEFAULT);
        enqueue();
    }

    public void dialogClick(View view){
        setProgressType(ProgressBean.PROGRESS_TYPE_DIALOG);
        enqueue();
    }

    public void dropdownClick(View view){
        setProgressType(ProgressBean.PROGRESS_TYPE_DROP_DOWN);
        enqueue();
    }
}
