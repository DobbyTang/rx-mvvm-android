package com.rx.mvvmlibs;


import android.util.Log;


import com.google.gson.Gson;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
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

    private IViewModel viewModel;

    protected abstract Observable setApiInterface();

    public abstract void resultData(Data data);

    public Model(IViewModel viewModel){
        this.viewModel = viewModel;
        init();
    }

    @Override
    public void enqueueRequest() {
        new Gson();
        setApiInterface()
                .doOnNext(next)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void cancelRequest() {
        subscriber.unsubscribe();
    }

    /**
     * @Method: init
     * @author create by Tang
     * @date date 16/11/14 下午3:47
     * @Description: 初始化Subscriber和Action1
     * @subscriber: 观察者
     * @next: 被观察者
     */
    private void init(){
        subscriber = new Subscriber<Result<Data>>() {
            @Override
            public void onCompleted() {

                Log.d(TAG, "onCompleted: = " + Thread.currentThread().getName() );
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: = " + Thread.currentThread().getName());
                e.printStackTrace();
            }

            @Override
            public void onNext(Result<Data> result) {
                Log.d(TAG, "onNext: = " + Thread.currentThread().getName()) ;
                Log.d(TAG, "onNext: result error num = " + result.errNum.get() );
                resultData(result.data);

            }
        };

        next = new Action1<Result<Data>> () {

            @Override
            public void call(Result<Data> result) {
                Log.d(TAG, "call: next thread = " + Thread.currentThread().getName());
            }
        };
    }
}
