package com.rx.mvvmlibs;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @className: ListResult
 * @author create by Tang
 * @date 2017/4/19 上午11:43
 * @description:
 */

public class ListResult<Data> extends ErrorInfo{

    @SerializedName(value = "statuses")
    public List<Data> data;
}
