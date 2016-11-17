package com.android.rx_mvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.rx_mvvm.databinding.ActivityTestBinding;
import com.rx.mvvmlibs.ViewModel;
import com.rx.mvvmlibs.bean.ProgressBean;
import com.rx.mvvmlibs.view.MvvmActivity;

/**
 * @ClassName: TestMvvmActivity
 * @author create by Tang
 * @date date 16/11/15 上午11:37
 * @Description: TODO
 */

public class TestMvvmActivity extends MvvmActivity{

    private ActivityTestBinding binding;

    TestViewModel testViewModel;


    @Override
    public ViewDataBinding onCreateView(LayoutInflater inflater, ViewGroup parent) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_test,parent,false);
        return binding;
    }

    @Override
    public ViewModel onBindingViewModel() {
        testViewModel = new TestViewModel(this);
        testViewModel.setProgressType(ProgressBean.PROGRESS_TYPE_DROP_DOWN);
        binding.setTestViewModel(testViewModel);
        return testViewModel;
    }

}