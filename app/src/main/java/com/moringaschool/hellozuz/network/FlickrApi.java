package com.moringaschool.hellozuz.network;

import com.moringaschool.hellozuz.models.FlickrPhotosSearchApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrApi {
    @GET("rest")
    Call<FlickrPhotosSearchApiResponse> getPhotos(
            @Query("method") String method,
            @Query("api_key") String apikey,
            @Query("text") String text,
            @Query("format") String format,
            @Query("nojsoncallback") String callBack
    );
}
