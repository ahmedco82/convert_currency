package com.androimads.retrolin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {
   @GET("v6/latest?")
   fun getCurrentCurrency(@Query("symbols") symbols:String):Call<Response>
}