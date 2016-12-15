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

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * @ClassName: RetrofitModel
 * @author create by Tang
 * @date date 16/11/10 下午2:11
 * @Description: MVVM的Model实现
 */
public class RetrofitModel implements IModel{

    Disposable disposable;

    @Inject
    Observable observable;

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
        if (TextUtils.isEmpty(url)){
            throw new NullPointerException("url can be not null");
        }
        DaggerRetrofitModelComponent
                .builder()
                .retrofitModelModule(new RetrofitModelModule(viewModel))
                .retrofitModule(new RetrofitModule(url,defaultTimeOut))
                .build().inject(this);


    }

    @Override
    public void enqueueRequest() {

        observable = viewModel.setApiInterface(retrofit);

        observable.subscribeOn(Schedulers.io())
                .observeOn(resultScheduler)
                .subscribe(new Observer<Result>() {

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
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Result result) {
                        viewModel.onResult(result);

                    }
                });
    }

    @Override
    public void cancelRequest() {
        if (disposable != null && !disposable.isDisposed()){
            LogUtil.d("");
            disposable.dispose();
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
