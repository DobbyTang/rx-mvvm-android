package com.rx.mvvmlibs;

/**
 * @ClassName: ServerManager
 * @author create by Tang
 * @date 2016/12/14 下午9:54
 * @Description: 默认服务器地址配置
 */

public class ServerManager {

    //默认发布服务器地址
    private static final String RELEASE_URL = "";

    //默认测试服务器地址
    private static final String DEBUG_URL = "";

    private static boolean debug = false;

    public static String getServerAddress(){
        if (debug){
            return DEBUG_URL;
        }
        return RELEASE_URL;
    }
}
