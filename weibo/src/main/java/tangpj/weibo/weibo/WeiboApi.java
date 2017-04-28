package tangpj.weibo.weibo;

import com.rx.mvvmlibs.ListResult;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import tangpj.weibo.application.bean.WeiboBean;

/**
 * @className: WeiboApi
 * @author create by Tang
 * @date 2017/4/25 下午2:28
 * @description:
 */

public interface WeiboApi {

    @GET("2/statuses/public_timeline.json")
    Observable<ListResult<WeiboBean>> publicTimeLine(@Query("access_token") String accessToken);

    @GET("2/statuses/home_timeline.json")
    Observable<ListResult<WeiboBean>> homeTimeLine(@Query("access_token") String accessToken);
}
