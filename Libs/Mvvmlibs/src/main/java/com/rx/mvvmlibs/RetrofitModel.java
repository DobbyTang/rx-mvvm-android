package com.rx.mvvmlibs;

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

    Flowable flowable;
    private Subscription subscription;

    @Inject
    Retrofit retrofit;

    @Inject
    BaseParamsInterceptor.Builder builder;

    private IRetrofitViewModel viewModel;

    private Scheduler resultScheduler;

    private String defaultUrl = "http://apis.baidu.com";
    private int defaultTimeOut = 15;

    @Inject
    public RetrofitModel(IRetrofitViewModel viewModel){
        this.viewModel = viewModel;
        this.resultScheduler = AndroidSchedulers.mainThread();
        DaggerRetrofitModelComponent
                .builder()
                .retrofitModelModule(new RetrofitModelModule(viewModel))
                .retrofitModule(new RetrofitModule(defaultUrl,defaultTimeOut))
                .build().inject(this);
    }

    @Override
    public void enqueueRequest() {

        flowable = viewModel.setApiInterface(retrofit);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(resultScheduler)
                .subscribe(new Subscriber<Result>() {

                    @Override
                    public void onError(Throwable e) {
                        viewModel.onNetworkError(e);
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.d( "onSuccess");
                    }

                    @Override
                    public void onSubscribe(Subscription s) {
                        subscription = s;
                    }

                    @Override
                    public void onNext(Result result) {
                        viewModel.onResult(result);

                    }
                });
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


}
