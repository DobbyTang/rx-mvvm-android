package com.android.rx_mvvm.viewModel;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.rx_mvvm.BaiduApi;
import com.android.rx_mvvm.R;
import com.android.rx_mvvm.TestAdapter;
import com.android.rx_mvvm.activity.TestMvvmActivity;
import com.android.rx_mvvm.bean.NuoMiCategoryBean;
import com.android.rx_mvvm.databinding.ActivityTestBinding;
import com.rx.mvvmlibs.ViewModel;
import com.rx.mvvmlibs.bean.ProgressBean;
import com.rx.mvvmlibs.view.MvvmFragment;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @ClassName: TestViewModel
 * @author create by Tang
 * @date date 16/11/15 下午4:56
 * @Description: TODO
 */

public class TestViewModel extends ViewModel<List<NuoMiCategoryBean>> {


    private Observable nuomi;

    private ActivityTestBinding binding;


    private Context context;

    public TestViewModel(TestMvvmActivity activity) {
        super(activity);
        this.context = activity;
        viewModelWrapper.model
                .getBuilder()
                .addHeader("apikey","05cecef32508c4bd5853a0fed178e322");
    }



    public TestViewModel(MvvmFragment fragment) {
        super(fragment);
        this.context = fragment.getContext();
        viewModelWrapper.model
                .getBuilder()
                .addHeader("apikey","05cecef32508c4bd5853a0fed178e322");
    }


    @Override
    public ViewDataBinding onCreateBinding(LayoutInflater inflater, ViewGroup parent) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_test,parent,false);
        binding.setTestViewModel(this);
        return binding;
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

    @Override
    public void result(List<NuoMiCategoryBean> resultData) {
        Snackbar.make(
                viewModelWrapper.childBinding.getRoot()
                ,resultData.get(0).cat_name.get()
                , Snackbar.LENGTH_SHORT).show();

        ActivityTestBinding binding = (ActivityTestBinding) viewModelWrapper.childBinding;
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        TestAdapter adapter = new TestAdapter();
        binding.recyclerView.setAdapter(adapter);
        adapter.setData(resultData);
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
