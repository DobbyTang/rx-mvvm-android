package com.rx.mvvmlibs.view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.rx.mvvmlibs.ViewModel;
import com.rx.mvvmlibs.component.DaggerMvvmActivityComponent;
import com.rx.mvvmlibs.module.MvvmActivityModule;
import com.rx.mvvmlibs.view.iview.BindingViewModel;

import javax.inject.Inject;

/**
 * @ClassName: MvvmActivity
 * @author create by Tang
 * @date date 16/11/15 上午10:44
 * @Description: TODO
 */

public abstract class MvvmActivity extends AppCompatActivity implements BindingViewModel {

    @Inject
    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMvvmActivityComponent.builder()
                .mvvmActivityModule(new MvvmActivityModule(this))
                .build()
                .inject(this);
        init();

    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.cancel();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public void init() {

    }
}
