package com.rx.mvvmlibs.view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.rx.mvvmlibs.R;
import com.rx.mvvmlibs.view.iview.IMvvmActivity;

/**
 * @ClassName: MvvmActivity
 * @author create by Tang
 * @date date 16/11/23 下午5:38
 * @Description: 无网络连接的Activity继承该类
 */

public abstract class MvvmActivity extends AppCompatActivity implements IMvvmActivity{

    private CoordinatorLayout contentViewGroup;
    private View contentView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm);
        initBaseView();

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


    private void initBaseView(){



        contentViewGroup = (CoordinatorLayout) ((ViewGroup)
                findViewById(android.R.id.content)).getChildAt(0);
        CoordinatorLayout.LayoutParams lp = new CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT
                ,CoordinatorLayout.LayoutParams.MATCH_PARENT);
        lp.setBehavior(new AppBarLayout.ScrollingViewBehavior());

        contentView = setContentView(getLayoutInflater(),contentViewGroup);

        contentViewGroup.addView(contentView,lp);
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
}
