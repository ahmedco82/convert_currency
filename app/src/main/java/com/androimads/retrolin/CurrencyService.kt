package com.androimads.retrolin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {
   // v6/02bddba7c54f51f1739ed12d/latest/USD
   // @GET("v6/02bddba7c54f51f1739ed12d/latest/USD")
   // https://open.exchangerate-api.com/v6/latest?symbols=USD,GBP
   // latest?symbols=USD,GBP
   @GET("v6/latest?")
   fun getCurrentData(@Query("symbols") symbols:String):Call<Response>
}