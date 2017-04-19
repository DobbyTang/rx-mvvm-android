package com.rx.mvvmlibs;


import com.rx.mvvmlibs.network.BaseParamsInterceptor;
import com.rx.utillibs.LogUtil;


import java.util.function.Consumer;

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
public abstract class RetrofitModel<Result extends ErrorInfo> implements IModel<Result>{

    private Disposable disposable;
    private Observable observable;

    private IRetrofitViewModel viewModel;
    private Scheduler resultScheduler;
    private Consumer<Result> onResult;
    private Retrofit retrofit;
    private BaseParamsInterceptor.Builder builder;

//    private String url;
//    private int defaultTimeOut = 15;


    public RetrofitModel(IRetrofitViewModel viewModel){
        this(viewModel,null);
    }

    public RetrofitModel(IRetrofitViewModel viewModel,Consumer<Result> onResult){
        this.viewModel = viewModel;
        this.resultScheduler = AndroidSchedulers.mainThread();
        this.onResult = onResult;
        this.retrofit = RxMvvmApplication.getInstance().getRetrofit();
        this.builder = RxMvvmApplication.getInstance().getInterceptorBuilder();

    }

    @Override
    public void enqueueRequest() {

        observable = setApiInterface(retrofit);
        LogUtil.d(retrofit.baseUrl());
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
                        viewModel.showProgress(true);
                        disposable = d;
                    }

                    @Override
                    public void onNext(Result result) {
                        if (result.errorCode == 0){
                            if (onResult != null){
                                onResult.accept(result);
                            }
                            viewModel.onSuccess();
                        }else {
                            viewModel.onError(result.errorApi,result.errorCode,result.errorMsg);
                        }

                    }
                });
    }

    @Override
    public void cancelRequest() {
        if (disposable != null && !disposable.isDisposed()){
            LogUtil.d(retrofit.baseUrl());
            disposable.dispose();
        }
    }

    @Override
    public void setResultScheduler(Scheduler scheduler) {
        this.resultScheduler = scheduler;
    }

    @Override
    public BaseParamsInterceptor.Builder getBuilder() {
        return builder;
    }

    @Override
    public void setOnResult(Consumer<Result> onResult){
        this.onResult = onResult;
    }
}
