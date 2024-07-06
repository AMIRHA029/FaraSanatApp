package com.gatirapp.myapplication.htttpservice.config

import com.google.gson.JsonObject
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.util.concurrent.TimeUnit

interface ApiServices  {

    //@GET("Sliders")
    //fun getSliders() : Single<List<AppSlider>>

    @POST("AddMessage")
    fun sendMessage(@Body message: JsonObject): Completable

}

const val DEVELOPMENT_BASE_URL = "https://10.0.2.2:7121/api/"
const val DEVELOPMENT_HOST = "localhost"


fun createApiServiceInstance(): ApiServices {

    val retrofit = Retrofit.Builder()
        .baseUrl(DEVELOPMENT_BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(ApiServices::class.java)
}