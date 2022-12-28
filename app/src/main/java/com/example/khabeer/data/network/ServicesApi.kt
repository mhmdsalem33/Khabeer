package com.example.khabeer.data.network

import com.example.khabeer.domain.model.UserBody
import com.example.khabeer.domain.model.information.InformationList
import com.example.khabeer.domain.model.user.UserModel

import retrofit2.Response
import retrofit2.http.*

interface ServicesApi {

    @POST("LogIn")
    suspend fun login(
        @Body userBody : UserBody ,
        @Query("DATE_FROM") dateFrom: String   = "18/12/2021 09:56:35",
        @Query("_DATE_TO")  dateTo  : String   = "18/12/2021 09:56:35"
    ): Response<UserModel>

    @GET("GetPayroll")
    suspend fun getInformation(
        @Header("authorization")  Token : String
    ) : Response<InformationList>

}