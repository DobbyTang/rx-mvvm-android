package com.rx.mvvmlibs.view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.rx.mvvmlibs.ListViewModel;
import com.rx.mvvmlibs.component.DaggerListMvvmActivityComponent;
import com.rx.mvvmlibs.module.ListMvvmActivityModule;
import com.rx.mvvmlibs.view.iview.IListMvvmActivity;

import javax.inject.Inject;

/**
 * @ClassName: ListMvvmActivity
 * @author create by Tang
 * @date date 16/11/18 下午3:54
 * @Description: 列表型activty
 */

public abstract class ListMvvmActivity extends AppCompatActivity implements IListMvvmActivity{

    @Inject
    ListViewModel listViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerListMvvmActivityComponent
                .builder()
                .listMvvmActivityModule(new ListMvvmActivityModule(this))
                .build()
                .inject(this);
        init();
    }

    @Override
    protected void onPause() {
        super.onPause();
        listViewModel.cancel();
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
    public ListViewModel onReBindingViewModel() {
        return null;
    }

    @Override
    public void init() {
        // TODO: 16/11/19
    }
}
