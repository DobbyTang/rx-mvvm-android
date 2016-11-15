package com.rx.mvvmlibs;


import android.util.Log;


import javax.inject.Inject;

import rx.Scheduler;
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
public class Model<Data> implements IModel{

    private static final String TAG = "Model";

    private Subscriber subscriber;

//    private Action1 next;

    private IViewModel viewModel;


    private Scheduler resultScheduler;

    @Inject
    public Model(IViewModel viewModel){
        this.viewModel = viewModel;
        this.resultScheduler = AndroidSchedulers.mainThread();
        init();
    }

    @Override
    public void enqueueRequest() {
        viewModel.setApiInterface()
//                .doOnNext(next)
                .subscribeOn(Schedulers.io())
                .observeOn(resultScheduler)
                .subscribe(subscriber);
    }

    @Override
    public void cancelRequest() {
        subscriber.unsubscribe();
    }

    @Override
    public void setResultScheduler(Scheduler scheduler) {
        this.resultScheduler = scheduler;
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
                viewModel.onSuccess();
            }

            @Override
            public void onError(Throwable e) {
                viewModel.onError(e);
                e.printStackTrace();
            }

            @Override
            public void onNext(Result<Data> result) {
                Log.d(TAG, "onNext: = " + Thread.currentThread().getName()) ;
                Log.d(TAG, "onNext: result error num = " + result.errNum.get() );
                viewModel.result(result);

            }
        };

//        next = new Action1<Result<Data>> () {
//
//            @Override
//            public void call(Result<Data> result) {
//                Log.d(TAG, "call: next thread = " + Thread.currentThread().getName());
//            }
//        };
    }
}
