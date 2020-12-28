package com.khodealib.basalam01.service.api

import com.khodealib.basalam01.data.ProductResponse
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("user")
    fun getProductsList(@Body requestBody: RequestBody): Single<ProductResponse>
}



