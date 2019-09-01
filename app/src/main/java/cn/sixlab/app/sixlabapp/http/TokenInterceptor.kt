//package cn.sixlab.app.sixlabapp.http
//
//import okhttp3.Interceptor
//import okhttp3.Response
//
///**
// * Created by patrick on 2017/7/4.
// */
//class TokenInterceptor : Interceptor {
//    private val name = "nianqinianyi"
//    private val token = "$2a$10\$b/9egxzjBiHqMA6GywVzA.1JUvp/W/F/GowksMkRv.OB1R9/oFZSy"
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val oldRequest = chain.request()
//
//        // 新的请求,添加参数
//        val builder = oldRequest.url()
//                .newBuilder()
//                .addQueryParameter("v-name", name)
//                .addQueryParameter("v-token", token)
//
//        val newRequest = oldRequest.newBuilder()
//                .method(oldRequest.method(), oldRequest.body())
//                .url(builder.build())
//                .build()
//
//        return chain.proceed(newRequest)
//    }
//}