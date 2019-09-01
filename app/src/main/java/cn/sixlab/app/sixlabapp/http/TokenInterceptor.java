package cn.sixlab.app.sixlabapp.http;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    private final String name = "nianqinianyi";
    private final String token = "$2a$10$b/9egxzjBiHqMA6GywVzA.1JUvp/W/F/GowksMkRv.OB1R9/oFZSy";

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request oldRequest = chain.request();

        // 新的请求,添加参数
        HttpUrl.Builder builder = oldRequest.url()
                .newBuilder()
                .addQueryParameter("v-name", name)
                .addQueryParameter("v-token", token);

        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(builder.build())
                .build();

        return chain.proceed(newRequest);
    }
}