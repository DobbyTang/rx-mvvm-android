package com.rx.mvvmlibs.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @ClassName: HeaderInterceptor
 * @author create by Tang
 * @date date 16/11/11 上午10:30
 * @Description: Http头部文件拦截器
 */

public class HeaderInterceptor implements Interceptor{

    private Request.Builder headBuilder;

    @Override
    public Response intercept(Chain chain) throws IOException {
        headBuilder = chain.request().newBuilder();
        /**
         * 设置头文件
         * 可以根据具体业务逻辑添加头
         */
        headBuilder.addHeader("apikey","05cecef32508c4bd5853a0fed178e322");
        return chain.proceed(headBuilder.build());
    }
}
