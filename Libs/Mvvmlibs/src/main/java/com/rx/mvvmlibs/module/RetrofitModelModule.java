package com.rx.mvvmlibs.module;

import com.rx.mvvmlibs.IRetrofitViewModel;
import com.rx.mvvmlibs.scope.RetrofitScope;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
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

    public RetrofitModelModule(IRetrofitViewModel viewModel){
        this.viewModel = viewModel;
    }

    @Provides
    @RetrofitScope
    public Observable providesObservable(Retrofit retrofit){
        return viewModel.setApiInterface(retrofit);
    }


}
