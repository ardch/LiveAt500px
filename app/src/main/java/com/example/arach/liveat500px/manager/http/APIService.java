package com.example.arach.liveat500px.manager.http;

import com.example.arach.liveat500px.dao.PhotoItemCollectionDao;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {
    @POST("list")
    Call<PhotoItemCollectionDao> loadPhotoList() ;

    @POST("list/after/{id}")
    Call<PhotoItemCollectionDao> loadPhotoListAfterId(@Path("id") int id) ;

    @POST("list/before/{id}")
    Call<PhotoItemCollectionDao> loadPhotoListBeforeId(@Path("id") int id) ;

}
