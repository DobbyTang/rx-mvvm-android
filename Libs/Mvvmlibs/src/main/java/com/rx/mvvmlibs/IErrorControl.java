package com.rx.mvvmlibs;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

/**
 * @ClassName: IErrorControl
 * @author create by Tang
 * @date date 16/10/26 上午11:11
 * @Description: 重设错误显示View
 */

public interface IErrorControl {

    /**
     * @Method: setErrorImageResource
     * @author create by Tang
     * @date date 16/10/26 上午10:31
     * @Description: 设置错误显示图片
     */
    Drawable setErrorImageDrawable();


    /**
     * @Method: setErrorString
     * @author create by Tang
     * @date date 16/10/26 上午10:31
     * @Description: 设置错误显示信息
     */
    String setErrorString();

}
