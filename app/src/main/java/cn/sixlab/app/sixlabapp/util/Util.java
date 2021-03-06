package cn.sixlab.app.sixlabapp.util;

import java.text.SimpleDateFormat;

import cn.sixlab.app.sixlabapp.http.TokenInterceptor;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by loki on 2016/4/4.
 */
public class Util {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    public static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // public static String sixlab = "http://192.168.200.132:8888/";
    public static String sixlab = "https://api.sixlab.cn/";
    public static String douban = "https://api.douban.com/";

    public static String formatDate(String dateStr){
//        Date date = new Date(Long.valueOf(dateStr));
//        return Util.dateFormat.format(date);
        if(null!=dateStr && !"".equals(dateStr)){
            return dateStr.substring(0,10);
        }else{
            return "";
        }
    }

    public static OkHttpClient apiToken(){
        Interceptor interceptor = new TokenInterceptor();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        return client;
    }

}
