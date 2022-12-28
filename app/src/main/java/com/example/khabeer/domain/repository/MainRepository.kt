package com.example.khabeer.domain.repository

import com.example.khabeer.data.network.ServicesApi
import com.example.khabeer.domain.model.UserBody
import javax.inject.Inject


class MainRepository  @Inject constructor( private val servicesApi: ServicesApi ){

    private val body    = UserBody(MobileNumber =  "01068962997" , Password     =   "123456" )
    suspend fun login() = servicesApi.login(userBody = body)

    suspend fun getInformation(token : String) = servicesApi.getInformation(token)


}