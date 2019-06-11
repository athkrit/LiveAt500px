package com.example.liveat500px.manager.http;

import com.example.liveat500px.dao.PhotoItemCollection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
        @GET("list")
        Call<PhotoItemCollection> loadPhotolist();

        @GET("list/after/{id}")
        Call<PhotoItemCollection> loadPhotolistAfterId(@Path("id")int id);
        @GET("list/before/{id}")
        Call<PhotoItemCollection> loadPhotolistBeforeId(@Path("id")int id);
}
