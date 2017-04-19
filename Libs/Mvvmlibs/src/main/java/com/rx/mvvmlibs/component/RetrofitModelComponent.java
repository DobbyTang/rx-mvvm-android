package com.rx.mvvmlibs.component;

import com.rx.mvvmlibs.RetrofitModel;
import com.rx.mvvmlibs.RetrofitModelWrapper;
import com.rx.mvvmlibs.module.RetrofitModelModule;
import com.rx.mvvmlibs.module.RetrofitModule;
import com.rx.mvvmlibs.scope.RetrofitModelScope;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * @ClassName: RetrofitModelComponent
 * @author create by Tang
 * @date date 16/11/21 上午9:37
 * @Description: TODO
 */

@RetrofitModelScope
@Component(modules = {RetrofitModelModule.class})
public interface RetrofitModelComponent {

    void inject(RetrofitModelWrapper retrofitModelWrapper);


}
