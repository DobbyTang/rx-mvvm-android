package com.rx.mvvmlibs.params;


import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: PaginationParams
 * @author create by Tang
 * @date date 16/11/21 下午2:52
 * @Description: TODO
 */

public class PaginationParams {

    public int count;

    public int page;

    private final String countKey;

    private final String pageKey;

    private Map paginationParams = new HashMap();

    public PaginationParams(String countKey,String pageKey){
        this.countKey = countKey;
        this.pageKey = pageKey;
    }

    public Map toMap(){
        paginationParams.put(countKey,count);
        paginationParams.put(pageKey,page);
        return paginationParams;
    }

}
