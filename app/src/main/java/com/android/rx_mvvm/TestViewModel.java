package com.android.rx_mvvm;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.rx_mvvm.bean.NuoMiCategoryBean;
import com.android.rx_mvvm.databinding.ActivityTestBinding;
import com.rx.mvvmlibs.RxMvvmApplication;
import com.rx.mvvmlibs.ViewModel;
import com.rx.mvvmlibs.bean.ProgressBean;
import com.rx.mvvmlibs.databinding.ActivityMvvmBinding;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @ClassName: TestViewModel
 * @author create by Tang
 * @date date 16/11/15 下午4:56
 * @Description: TODO
 */

public class TestViewModel extends ViewModel<List<NuoMiCategoryBean>>{


    private Observable nuomi;
    private TestMvvmActivity activity;

    public TestViewModel(TestMvvmActivity activity) {
        super(activity);
        this.activity = activity;

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

        ActivityTestBinding binding = (ActivityTestBinding) viewModelWrapper.childBinding;
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        TestAdapter adapter = new TestAdapter();
        binding.recyclerView.setAdapter(adapter);
        adapter.setData(resultData);
    }

    @Override
    public Observable setApiInterface(Retrofit retrofit) {
        return retrofit
                .create(BaiduApi.class)
                .rxGetCategory();
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
