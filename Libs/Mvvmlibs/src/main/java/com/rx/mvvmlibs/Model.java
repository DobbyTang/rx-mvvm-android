package com.rx.mvvmlibs;


import android.util.Log;


import com.rx.utillibs.LogUtil;

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


    private Subscriber subscriber;

//    private Action1 next;

    private IViewModel viewModel;


    private Scheduler resultScheduler;

    @Inject
    public Model(IViewModel viewModel){
        this.viewModel = viewModel;
        this.resultScheduler = AndroidSchedulers.mainThread();
    }

    @Override
    public void enqueueRequest() {

        subscriber = new Subscriber<Result<Data>>() {
            @Override
            public void onCompleted() {
                LogUtil.d(Model.this.getClass(), "onCompleted: onSuccess");
                viewModel.onSuccess();
            }

            @Override
            public void onError(Throwable e) {
                viewModel.onError(e);
                e.printStackTrace();
            }

            @Override
            public void onNext(Result<Data> result) {
                viewModel.result(result);

            }
        };

        viewModel.setApiInterface()
                .subscribeOn(Schedulers.io())
                .observeOn(resultScheduler)
                .subscribe(subscriber);
    }

    @Override
    public void cancelRequest() {
        if (subscriber != null && !subscriber.isUnsubscribed()){
            LogUtil.d(this.getClass(), "cancelRequest: ");
            subscriber.unsubscribe();
        }
    }

    @Override
    public void setResultScheduler(Scheduler scheduler) {
        this.resultScheduler = scheduler;
    }



}
