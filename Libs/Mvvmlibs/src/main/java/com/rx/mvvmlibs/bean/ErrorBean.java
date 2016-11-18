package com.rx.mvvmlibs.bean;

import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;

import javax.inject.Inject;


/**
 * @ClassName: ErrorBean
 * @author create by Tang
 * @date date 16/11/18 上午9:31
 * @Description: 错误信息与错误图片
 */

public class ErrorBean {

    public ObservableField<Drawable> drawable = new ObservableField<>();

    public ObservableField<String> message = new ObservableField<>();

    @Inject
    public ErrorBean(Drawable drawable,String message){
        this.drawable.set(drawable);
        this.message.set(message);
    }
}
