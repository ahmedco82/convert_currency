package com.androimads.retrolin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback

class ViewModelConverter:ViewModel(){

   val currency  = MutableLiveData<String>()

    fun setIntValue(to_index:Int,from:String,to:String){
        //imageLiveData.value=5
        val retrofitCall =  RestClient.getInterfaceInstance( "https://open.exchangerate-api.com/")
        retrofitCall!!.getCurrentCurrency(""+from+","+""+to).enqueue(object: Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if(response.code() == 200) {
                    val currencyResponse = response.body()!!
                    if(to_index==0){
                        currency.value= ""+currencyResponse.rates!!.uSD
                    }else if(to_index==1){
                        currency.value= ""+currencyResponse.rates!!.eUR
                    }else if(to_index==2){
                        currency.value= "" +currencyResponse.rates!!.cAD
                    }else if(to_index==3){
                        currency.value= "" +currencyResponse.rates!!.cHF
                    }else if(to_index==4){
                        currency.value= "" +currencyResponse.rates!!.gBP
                    }
                    else if(to_index==5){
                        currency.value= "" +currencyResponse.rates!!.sEK
                    }
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
               // DataTextView!!.text = t.message
                //  println("print_CXX00:"+ t.message )
            }
        })
    }
}