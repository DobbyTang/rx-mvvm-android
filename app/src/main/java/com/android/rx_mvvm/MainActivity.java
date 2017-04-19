package com.android.rx_mvvm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.rx.mvvmlibs.RxMvvmApplication;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Observable observable;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                test();
            }
        });

        retrofit = RxMvvmApplication.getInstance().getRetrofit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void test(){


        TestBean testBean = new TestBean();
        testBean.testBoolean.set(true);
        testBean.testFloat.set(1.27F);
        testBean.testString.set("hello test");
        testBean.testInt.set(10000);
        Log.d(TAG, "test: " + RxMvvmApplication.getInstance().getGson().toJson(testBean));

    }

    public void nuomiListActivity(View view){
        Intent intent = new Intent(MainActivity.this,TestListMvvmActivity.class);
        startActivity(intent);

    }

    public void nuomiActivity(View view){
        Intent intent = new Intent(this,TestMvvmActivity.class);
        startActivity(intent);
    }

}
