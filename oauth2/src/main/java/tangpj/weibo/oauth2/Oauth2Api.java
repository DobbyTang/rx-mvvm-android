package tangpj.weibo.oauth2;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import tangpj.weibo.oauth2.result.AccessTokenResult;

/**
 * @className: Oauth2Api
 * @author create by Tang
 * @date 2017/4/19 下午9:11
 * @description:
 */

public interface Oauth2Api {

    @FormUrlEncoded
    @POST("oauth2/access_token")
    Observable<AccessTokenResult> getAccessToken(@Field("client_id") String clientId,
                                                 @Field("client_secret") String clientSecret,
                                                 @Field("grant_type") String grantType,
                                                 @Field("code") String code,
                                                 @Field("redirect_uri") String redirectUri);

}
