package com.example.covid_19tracking

import retrofit2.Call
import retrofit2.http.GET

interface TrackingService {

    @GET("states/current.json")
    fun searchState(): Call<List<States>>
}