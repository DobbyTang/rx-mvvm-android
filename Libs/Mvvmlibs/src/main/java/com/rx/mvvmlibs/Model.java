package com.rx.mvvmlibs;


import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @ClassName: Model
 * @author create by Tang
 * @date date 16/11/10 下午2:11
 * @Description: MVVM的Model实现
 */
public abstract class Model<Data> implements IModel{

    private static final String TAG = "Model";

    private Subscriber subscriber;

    private Action1 next;

    protected abstract Observable setApiInterface();

    public Model(){
        subscriber = new Subscriber<Result<Data>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: 完成");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: 错误");
                e.printStackTrace();
            }

            @Override
            public void onNext(Result<Data> nuoMiCategoryBeanBaseResponse) {


            }
        };

        next = new Action1<Result<Data>> () {

            @Override
            public void call(Result<Data> result) {
                Log.d(TAG, "call: next");
            }
        };
    }

    @Override
    public void enqueue() {
        setApiInterface()
                .doOnNext(next)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void cancel() {
        subscriber.unsubscribe();
    }
}
