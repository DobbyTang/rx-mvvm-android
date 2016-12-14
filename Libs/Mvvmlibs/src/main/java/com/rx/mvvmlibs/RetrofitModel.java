package com.rx.mvvmlibs;

import android.text.TextUtils;

import com.rx.mvvmlibs.component.DaggerRetrofitModelComponent;
import com.rx.mvvmlibs.module.RetrofitModelModule;
import com.rx.mvvmlibs.module.RetrofitModule;
import com.rx.mvvmlibs.network.BaseParamsInterceptor;
import com.rx.utillibs.LogUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * @ClassName: RetrofitModel
 * @author create by Tang
 * @date date 16/11/10 下午2:11
 * @Description: MVVM的Model实现
 */
public class RetrofitModel implements IModel{

    @Inject
    Flowable flowable;

    @Inject
    Subscriber<Result> subscriber;

    @Inject
    Subscription subscription;

    @Inject
    Retrofit retrofit;

    @Inject
    BaseParamsInterceptor.Builder builder;

    private IRetrofitViewModel viewModel;

    private Scheduler resultScheduler;

    private String url;
    private int defaultTimeOut = 15;

    @Inject
    public RetrofitModel(IRetrofitViewModel viewModel){
        this.viewModel = viewModel;
        this.resultScheduler = AndroidSchedulers.mainThread();
        if (!TextUtils.isEmpty(resetServerAddress())){
            url = resetServerAddress();
        }else {
            url = ServerManager.getServerAddress();
        }
        DaggerRetrofitModelComponent
                .builder()
                .retrofitModelModule(new RetrofitModelModule(viewModel))
                .retrofitModule(new RetrofitModule(url,defaultTimeOut))
                .build().inject(this);
    }

    @Override
    public void enqueueRequest() {

        flowable = viewModel.setApiInterface(retrofit);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(resultScheduler)
                .subscribe(subscriber);
    }

    @Override
    public void cancelRequest() {
        if (subscription != null ){
            LogUtil.d("");
            subscription.cancel();
        }
    }

    @Override
    public void setResultScheduler(Scheduler scheduler) {
        this.resultScheduler = scheduler;
    }

    @Override
    public Retrofit getRetrofit() {
        return retrofit;
    }

    @Override
    public BaseParamsInterceptor.Builder getBuilder(){
        return builder;
    }

    @Override
    public String resetServerAddress() {
        return null;
    }
}
