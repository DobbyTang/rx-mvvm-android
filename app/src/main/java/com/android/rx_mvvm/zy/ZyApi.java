package com.android.rx_mvvm.zy;


import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @className: ZyApi
 * @author create by Tang
 * @date 2017/4/19 下午2:50
 * @description:
 */

public interface ZyApi {

    @GET("")
    Observable<ZYResult> getZy();
}
