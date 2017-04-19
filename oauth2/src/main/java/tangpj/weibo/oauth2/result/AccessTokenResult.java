package tangpj.weibo.oauth2.result;

import com.google.gson.annotations.SerializedName;
import com.rx.mvvmlibs.ErrorInfo;

/**
 * @className: AccessTokenResult
 * @author create by Tang
 * @date 2017/4/19 下午8:51
 * @description:
 */

public class AccessTokenResult extends ErrorInfo{

    @SerializedName("access_token")
    public String accessToken;

    @SerializedName("expires_in")
    public String expiresIn;

    public String uid;
}
