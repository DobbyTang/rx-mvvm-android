package com.rx.mvvmlibs.view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.rx.mvvmlibs.R;
import com.rx.mvvmlibs.databinding.ActivityBaseBinding;
import com.rx.mvvmlibs.view.iview.IBaseActivity;

/**
 * @ClassName: BaseActivity
 * @author create by Tang
 * @date date 16/11/23 下午5:38
 * @Description: 无网络连接的Activity继承该类
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity {

    private CoordinatorLayout contentViewGroup;
    private View contentView;

    private ActivityBaseBinding baseBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseBinding = DataBindingUtil.setContentView(this,R.layout.activity_base);
        init();

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
    public void init(){

        setSupportActionBar(baseBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        contentViewGroup = baseBinding.coordinatorLayout;
        CoordinatorLayout.LayoutParams lp = new CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT
                ,CoordinatorLayout.LayoutParams.MATCH_PARENT);
        lp.setBehavior(new AppBarLayout.ScrollingViewBehavior());

        try{
            contentView = setContentBinding(getLayoutInflater(),contentViewGroup).getRoot();
            contentViewGroup.addView(contentView,lp);

        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        try{

            super.startActivity(intent);

        }catch (ActivityNotFoundException e){
            Snackbar.make(contentViewGroup,"找不到对应的页面",Snackbar.LENGTH_SHORT)
                    .setAction("Action",null).show();
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        try{

            super.startActivityForResult(intent, requestCode);

        }catch (ActivityNotFoundException e){
            Snackbar.make(contentViewGroup,"找不到对应的页面",Snackbar.LENGTH_SHORT)
                    .setAction("Action",null).show();
        }

    }

    @Override
    public ActivityBaseBinding getBaseBinding() {
        return baseBinding;
    }
}
