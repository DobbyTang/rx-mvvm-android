package com.rx.mvvmlibs.view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.rx.mvvmlibs.RetrofitViewModel;
import com.rx.mvvmlibs.component.DaggerRetrofitMvvmActivityComponent;
import com.rx.mvvmlibs.module.BindViewModelModule;
import com.rx.mvvmlibs.view.iview.BindViewModel;

import javax.inject.Inject;

/**
 * @ClassName: RetrofitMvvmActivity
 * @author create by Tang
 * @date date 16/11/15 上午10:44
 * @Description: TODO
 */

public abstract class RetrofitMvvmActivity extends AppCompatActivity implements BindViewModel {

    @Inject
    RetrofitViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerRetrofitMvvmActivityComponent.builder()
                .bindViewModelModule(new BindViewModelModule(this))
                .build()
                .inject(this);
        init();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (viewModel != null){
            viewModel.cancel();
        }
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
