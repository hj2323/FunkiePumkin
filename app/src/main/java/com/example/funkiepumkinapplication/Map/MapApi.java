package com.example.funkiepumkinapplication.Map;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface MapApi {
    @Headers("Accept: application/json")
    @GET("/shopListBody")
    Call<MapDynamicRvModel> getShopListBody(@Query("shopLat") double shopLat, @Query("shopLng") double shopLng);
}
