package com.android.rx_mvvm.zy;

import com.rx.mvvmlibs.IRetrofitViewModel;
import com.rx.mvvmlibs.RetrofitModel;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @className: ZYModel
 * @author create by Tang
 * @date 2017/4/19 下午2:48
 * @description:
 */

public class ZYModel extends RetrofitModel<ZYResult>{

    public ZYModel(IRetrofitViewModel viewModel) {
        super(viewModel);
    }

    @Override
    public Observable setApiInterface(Retrofit retrofit) {
        return retrofit.create(ZyApi.class).getZy();
    }
}
