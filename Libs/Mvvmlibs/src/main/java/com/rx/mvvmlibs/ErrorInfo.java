package com.rx.mvvmlibs;

import com.google.gson.annotations.SerializedName;

/**
 * @ClassName: ErrorInfo
 * @author create by Tang
 * @date date 16/11/11 上午9:48
 * @Description: 服务器返回数据格式
 */

public class ErrorInfo {

    public String errorApi;

    @SerializedName(value = "errNum",alternate = {"status","errno"})
    public int errorCode;

    @SerializedName(value = "errMsg",alternate = {"msg"})
    public String errorMsg;
}
