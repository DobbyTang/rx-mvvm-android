package com.android.rx_mvvm;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.android.rx_mvvm.bean.NuoMiCategoryBean;
import com.rx.mvvmlibs.RxMvvmApplication;
import com.rx.mvvmlibs.ViewModel;
import com.rx.mvvmlibs.bean.ProgressBean;

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
        nuomi = viewModelWrapper.model.getRetrofit().create(BaiduApi.class).rxGetCategory();
        viewModelWrapper.model
                .getBuilder()
                .addHeader("apikey","05cecef32508c4bd5853a0fed178e322");
    }


    @Override
    public void result(List<NuoMiCategoryBean> resultData) {
        Snackbar.make(
                viewModelWrapper.childBinding.getRoot()
                ,resultData.get(0).cat_name.get()
                , Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public Observable setApiInterface() {
        return nuomi;
    }

    @Override
    public void init() {
        super.init();
        setProgressType(ProgressBean.PROGRESS_TYPE_DROP_DOWN);

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
