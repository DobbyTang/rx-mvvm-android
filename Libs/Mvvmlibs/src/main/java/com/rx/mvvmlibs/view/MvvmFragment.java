package com.rx.mvvmlibs.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rx.mvvmlibs.R;
import com.rx.mvvmlibs.ViewModel;
import com.rx.mvvmlibs.component.DaggerMvvmFragmentComponent;
import com.rx.mvvmlibs.databinding.ContentMvvmBinding;
import com.rx.mvvmlibs.module.BindViewModelModule;
import com.rx.mvvmlibs.view.iview.BindViewModel;
import com.rx.mvvmlibs.view.iview.IMvvmFragment;

import javax.inject.Inject;

/**
 * @ClassName: MvvmFragment
 * @author create by Tang
 * @date date 16/11/21 下午4:10
 * @Description: TODO
 */
public abstract class MvvmFragment extends Fragment implements IMvvmFragment,BindViewModel {

    @Inject
    ViewModel viewModel;

    private ContentMvvmBinding contentMvvmBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentMvvmBinding = DataBindingUtil.inflate(inflater,R.layout.content_mvvm,container,false);
        init();
        return contentMvvmBinding.getRoot();
    }

    @Override
    public ContentMvvmBinding getContentMvvmBinding() {
        return contentMvvmBinding;
    }

    @Override
    public void init() {
        DaggerMvvmFragmentComponent
                .builder()
                .bindViewModelModule(new BindViewModelModule(this))
                .build()
                .inject(this);
    }
}
