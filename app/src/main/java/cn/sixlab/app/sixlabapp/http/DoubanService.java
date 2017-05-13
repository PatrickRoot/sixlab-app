package cn.sixlab.app.sixlabapp.http;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DoubanService {

    @GET("v2/movie/search")
    Call<Map> queryMovie(@Query("q") CharSequence q);

    @GET("v2/movie/subject/{subject}")
    Call<Map> selectMovie(@Path("subject") String subject);
}
