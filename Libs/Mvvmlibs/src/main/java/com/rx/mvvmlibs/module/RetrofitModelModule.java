package com.rx.mvvmlibs.module;

import com.rx.mvvmlibs.IRetrofitViewModel;
import com.rx.mvvmlibs.Result;
import com.rx.mvvmlibs.scope.RetrofitScope;
import com.rx.utillibs.LogUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Flowable;
import retrofit2.Retrofit;

/**
 * @ClassName: RetrofitModelModule
 * @author create by Tang
 * @date date 16/11/17 上午11:25
 * @Description: TODO
 */

@Module
public class RetrofitModelModule {

    private IRetrofitViewModel viewModel;
    private Subscription subscription;

    public RetrofitModelModule(IRetrofitViewModel viewModel){
        this.viewModel = viewModel;
    }

    @Provides
    @RetrofitScope
    public Flowable providesFlowable(Retrofit retrofit){
        return viewModel.setApiInterface(retrofit);
    }

    @Provides
    @RetrofitScope
    public Subscriber<Result> providesSubscriber(){
        return new Subscriber<Result>() {

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
        };
    }

    @Provides
    @RetrofitScope
    public Subscription providesSubscription(){
        return subscription;
    }
}
