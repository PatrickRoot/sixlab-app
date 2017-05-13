package cn.sixlab.app.sixlabapp.http;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SixlabService {

    //    @GET("tool/movie/search/{keyword}")
    //    SixlabMovies searchMovie(@Path("keyword") String keyword);
    //    SixlabMovies searchMovie(@Query("keyword") String keyword);
    //    Call<ResponseBody> get();

    //    @POST("tool/movie/search")
    //    Call<ResponseBody> queryMovie(@Query("keyword")String keyword);

    @POST("tool/movie/search")
    Call<Map> queryMovie(@Query("keyword") String keyword);

    @POST("tool/movie/add")
    Call<Map> addMovie(@Query("movieName") String movieName, @Query("produceYear") String produceYear, @Query("director") String director, @Query("remark") String remark, @Query("viewDate") String viewDate, @Query("doubanScore") double doubanScore, @Query("doubanKey") String doubanKey);

    @POST("tool/show/search")
    Call<Map> queryShow(@Query("keyword") String keyword);

    @POST("tool/show/season/add")
    Call<Map> addSeason(@Query("id") int id);

    @POST("tool/show/episode/add")
    Call<Map> addEpisode(@Query("id") int id);

    @POST("tool/show/end")
    Call<Map> endShow(@Query("id") int id);

    @POST("tool/show/finish")
    Call<Map> finishShow(@Query("id") int id);

    @POST("tool/show/view/status")
    Call<Map> changeViewStatus(@Query("id") int id,@Query("status") String status);

    @POST("tool/show/add")
    Call<Map> addShow(@Query("showName") String showName, @Query("showSeason") int showSeason, @Query("showEpisode") int showEpisode, @Query("beginDate") String beginDate, @Query("remark") String remark, @Query("tv") String tv, @Query("doubanKey") String doubanKey);
}