package cn.sixlab.app.sixlabapp.http;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SixlabService {

    //    @GET("api/movie/search/{keyword}")
    //    SixlabMovies searchMovie(@Path("keyword") String keyword);
    //    SixlabMovies searchMovie(@Query("keyword") String keyword);
    //    Call<ResponseBody> get();

    //    @POST("api/movie/search")
    //    Call<ResponseBody> queryMovie(@Query("keyword")String keyword);

    @POST("api/movie/search")
    Call<Map> queryMovie(@Query("keyword") String keyword);

    @POST("api/movie/add")
    Call<Map> addMovie(@Query("movieName") String movieName, @Query("produceYear") String produceYear, @Query("director") String director, @Query("remark") String remark, @Query("viewDate") String viewDate, @Query("doubanScore") double doubanScore, @Query("doubanKey") String doubanKey);

    @POST("api/show/search")
    Call<Map> queryShow(@Query("keyword") String keyword);

    @POST("api/show/watching")
    Call<Map> queryWatching(@Query("keyword") String keyword);

    @POST("api/show/watched")
    Call<Map> queryWatched(@Query("keyword") String keyword);

    @POST("api/show/season/add")
    Call<Map> addSeason(@Query("id") int id);

    @POST("api/show/episode/add")
    Call<Map> addEpisode(@Query("id") int id);

    @POST("api/show/end")
    Call<Map> endShow(@Query("id") int id);

    @POST("api/show/finish")
    Call<Map> finishShow(@Query("id") int id);

    @POST("api/show/view/status")
    Call<Map> changeViewStatus(@Query("id") int id,@Query("status") String status);

    @POST("api/show/add")
    Call<Map> addShow(@Query("showName") String showName, @Query("showSeason") int showSeason, @Query("showEpisode") int showEpisode, @Query("beginDate") String beginDate, @Query("remark") String remark, @Query("tv") String tv, @Query("doubanKey") String doubanKey);
}