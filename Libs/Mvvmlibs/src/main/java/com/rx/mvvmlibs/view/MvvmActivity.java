package com.rx.mvvmlibs.view;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.rx.mvvmlibs.component.DaggerMvvmActivityComponent;
import com.rx.mvvmlibs.databinding.ActivityMvvmBinding;
import com.rx.mvvmlibs.databinding.ContentMvvmBinding;
import com.rx.mvvmlibs.module.MvvmActivityModule;
import com.rx.mvvmlibs.view.iview.IMvvmActivity;

import javax.inject.Inject;

/**
 * @ClassName: MvvmActivity
 * @author create by Tang
 * @date date 16/11/15 上午10:44
 * @Description: TODO
 */

public abstract class MvvmActivity extends Activity implements IMvvmActivity{

    @Inject
    ActivityMvvmBinding mvvmBinding;

    @Inject
    ContentMvvmBinding contentMvvmBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

    }

    @Override
    public void startActivity(Intent intent) {
        try{

            super.startActivity(intent);

        }catch (ActivityNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        try{

            super.startActivityForResult(intent, requestCode);

        }catch (ActivityNotFoundException e){
            e.printStackTrace();
        }

    }

    private void init(){
        DaggerMvvmActivityComponent.builder()
                .mvvmActivityModule(new MvvmActivityModule(this))
                .build()
                .inject(this);

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);

        ViewDataBinding contentViewDataBinding = onCreateView(getLayoutInflater(),contentMvvmBinding.contentMvvm);
        if (contentViewDataBinding != null )
        contentMvvmBinding.contentMvvm
                .addView(contentViewDataBinding
                        .getRoot(),lp);
    }
}
