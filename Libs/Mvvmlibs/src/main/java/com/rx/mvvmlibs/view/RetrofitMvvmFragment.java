package com.rx.mvvmlibs.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rx.mvvmlibs.R;
import com.rx.mvvmlibs.RetrofitViewModel;
import com.rx.mvvmlibs.component.DaggerRetrofitMvvmFragmentComponent;
import com.rx.mvvmlibs.databinding.ContentRetrofitMvvmBinding;
import com.rx.mvvmlibs.module.BindViewModelModule;
import com.rx.mvvmlibs.view.iview.BindViewModel;
import com.rx.mvvmlibs.view.iview.IMvvmFragment;

import javax.inject.Inject;

/**
 * @ClassName: RetrofitMvvmFragment
 * @author create by Tang
 * @date date 16/11/21 下午4:10
 * @Description: TODO
 */
public abstract class RetrofitMvvmFragment extends Fragment implements IMvvmFragment,BindViewModel {

    @Inject
    RetrofitViewModel viewModel;

    private ContentRetrofitMvvmBinding contentMvvmBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentMvvmBinding = DataBindingUtil.inflate(inflater,R.layout.content_retrofit_mvvm,container,false);
        init();
        return contentMvvmBinding.getRoot();
    }

    @Override
    public ContentRetrofitMvvmBinding getContentMvvmBinding() {
        return contentMvvmBinding;
    }

    @Override
    public void init() {
        DaggerRetrofitMvvmFragmentComponent
                .builder()
                .bindViewModelModule(new BindViewModelModule(this))
                .build()
                .inject(this);
    }
}
