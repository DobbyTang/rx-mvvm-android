package com.rx.mvvmlibs;




import com.rx.mvvmlibs.component.DaggerModelComponent;
import com.rx.mvvmlibs.module.ModelModule;
import com.rx.mvvmlibs.module.RetrofitModule;
import com.rx.mvvmlibs.network.BaseParamsInterceptor;
import com.rx.utillibs.LogUtil;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @ClassName: Model
 * @author create by Tang
 * @date date 16/11/10 下午2:11
 * @Description: MVVM的Model实现
 */
public class Model implements IModel{

    private Subscription subscription;

    @Inject
    Retrofit retrofit;

    @Inject
    BaseParamsInterceptor.Builder builder;

    private IViewModel viewModel;

    private Scheduler resultScheduler;

    private String defaultUrl = "http://apis.baidu.com";
    private int defaultTimeOut = 15;

    @Inject
    public Model(IViewModel viewModel){
        this.viewModel = viewModel;
        this.resultScheduler = AndroidSchedulers.mainThread();
        DaggerModelComponent
                .builder()
                .modelModule(new ModelModule(viewModel))
                .retrofitModule(new RetrofitModule(defaultUrl,defaultTimeOut))
                .build().inject(this);
    }

    @Override
    public void enqueueRequest() {

        subscription = viewModel.setApiInterface(retrofit)
                .subscribeOn(Schedulers.io())
                .observeOn(resultScheduler)
                .subscribe(new Subscriber<Result>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.d(Model.this.getClass(), "onCompleted: onSuccess");
                    }

                    @Override
                    public void onError(Throwable e) {
                        viewModel.onNetworkError(e);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Result result) {
                        viewModel.onResult(result);

                    }
                });
    }

    @Override
    public void cancelRequest() {
        if (subscription != null && !subscription.isUnsubscribed()){
            LogUtil.d(this.getClass(), "cancelRequest: ");
            subscription.unsubscribe();
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
