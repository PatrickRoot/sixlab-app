package cn.sixlab.app.sixlabapp.util

import cn.sixlab.app.sixlabapp.http.TokenInterceptor
import okhttp3.OkHttpClient

/**
 * Created by patrick on 2017/7/4.
 */
object HttpUtil{
    // public static String sixlab = "http://192.168.200.132:8888/";
    @JvmStatic
    var sixlab = "https://api.sixlab.cn/"
    @JvmStatic
    var douban = "https://api.douban.com/"

    @JvmStatic
    fun apiToken(): OkHttpClient {
        val interceptor = TokenInterceptor()
        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
        return client
    }
}