package tangpj.weibo.application.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @className: Geo
 * @author create by Tang
 * @date 2017/4/25 上午10:56
 * @description: 地理信息
 */

public class Geo {

    //经度坐标
    public String longitude;

    //维度坐标
    public String latitude;

    //所在城市的城市代码
    public String city;

    //所在省份的省份代码
    public String province;

    //所在城市的城市名称
    @SerializedName("city_name")
    public String cityName;

    //所在省份的省份名称
    @SerializedName("province_name")
    public String provinceName;

    //所在的实际地址，可以为空
    public String address;

    //地址的汉语拼音，不是所有情况都会返回该字段
    public String pinyin;

    //更多信息，不是所有情况都会返回该字段
    public String more;
}
