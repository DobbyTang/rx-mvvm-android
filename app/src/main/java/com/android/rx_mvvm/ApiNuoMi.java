package com.android.rx_mvvm;

import com.rx.mvvmlibs.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import rx.Observable;

/**
 * Created by guohao on 16/11/9.
 */

public interface ApiNuoMi {

    @Headers("apikey: ")
    @GET("baidunuomi/openapi/categories")
    Call<Result<NuoMiCategoryBean>> getCategory();

    @Headers("apikey: ")
    @GET("baidunuomi/openapi/categories")
    Observable<Result<List<NuoMiCategoryBean>>> rxGetCategory();
}
