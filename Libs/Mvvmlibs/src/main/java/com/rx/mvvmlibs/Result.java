package com.rx.mvvmlibs;

import com.google.gson.annotations.SerializedName;

/**
 * @ClassName: Result
 * @author create by Tang
 * @date date 16/11/11 上午9:48
 * @Description: 服务器返回数据格式
 */

public class Result<Data> {
    @SerializedName(value = "errNum",alternate = {"status","errno"})
    public int errNum;

    @SerializedName(value = "errMsg",alternate = {"msg"})
    public String errMsg;

    /**
     * @author create by Tang
     * @date date 16/10/12 下午4:18
     * @Description: 兼容不同的情况
     * @restData: 百度api,天气data
     */
    @SerializedName(value = "data",alternate = {"retData","categories","shop","subjects"})
    public Data data;
}
