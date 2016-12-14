package com.android.rx_mvvm;

import com.android.rx_mvvm.bean.NuoMiCategoryBean;
import com.android.rx_mvvm.bean.TngouBean;
import com.rx.mvvmlibs.Result;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by guohao on 16/11/9.
 */

public interface BaiduApi {

    @GET("baidunuomi/openapi/categories")
    Call<Result<NuoMiCategoryBean>> getCategory();

    @GET("baidunuomi/openapi/categories")
    Flowable<Result<List<NuoMiCategoryBean>>> rxGetCategory();

    @GET("tngou/store/location")
    Flowable<Result<List<TngouBean>>> getLocationStore();
}
