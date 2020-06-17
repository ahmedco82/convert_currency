package com.androimads.retrolin

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var weatherData:TextView? = null

    private var From: String? = null
    private var To: String? = null

    private var toIndex:Int? = null
    private var fromIndex:Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weatherData=findViewById(R.id.textView1)
        val currency_ = arrayOf("USD","EUR","CAD","CHF","GBP","SEK")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currency_)
        // Set the drop down view resource
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        // Finally, data bind the spinner object with dapter
        spinner_from.adapter = adapter;
        spinner_to.adapter = adapter;
        // Set an on item selected listener for spinner object
        spinner_from.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int , id: Long){
                From= "${parent.getItemAtPosition(position).toString()}"
                fromIndex = position
            }
            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }

        spinner_to.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int,id:Long){
                To= "${parent.getItemAtPosition(position).toString()}"
                toIndex = position
            }
            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }
        findViewById<View>(R.id.button).setOnClickListener { getCurrentData() }
    }


    internal fun getCurrentData(){
        val retrofit = Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(CurrencyService::class.java)
        val call = service.getCurrentWeatherData(""+From+","+""+To)
        call.enqueue(object:Callback<com.androimads.retrolin.Response> {
         override fun onResponse(call:Call<com.androimads.retrolin.Response>,response:Response<com.androimads.retrolin.Response>) {
          if(response.code() == 200) {
            val weatherResponse = response.body()!!
              //val currency_= arrayOf(weatherResponse.rates?.uSD,weatherResponse.rates?.eUR,weatherResponse.rates?.cAD,weatherResponse.rates?.cHF,weatherResponse.rates?.gBP,weatherResponse.rates?.sEK)
              if(toIndex==0){
                  weatherData!!.text = "To" +weatherResponse.rates!!.uSD
              }else if(toIndex==1){
                  weatherData!!.text = "To" +weatherResponse.rates!!.eUR
              }else if(toIndex==2){
                  weatherData!!.text = "To" +weatherResponse.rates!!.cAD
              }else if(toIndex==3){
                  weatherData!!.text = "To" +weatherResponse.rates!!.cHF
              }else if(toIndex==4){
                  weatherData!!.text = "To" +weatherResponse.rates!!.gBP
              }
              else if(toIndex==5){
                  weatherData!!.text = "To" +weatherResponse.rates!!.sEK
              }
             }
           }

            override fun onFailure(call: Call<com.androimads.retrolin.Response>, t: Throwable) {
               // weatherData!!.text = t.message
            }
        })
    }


    companion object{
        var BaseUrl ="https://api.exchangeratesapi.io/"
    }
}








// https://api.exchangeratesapi.io/latest?symbols=USD,GBP
//  var AppId ="02bddba7c54f51f1739ed12d"
//arrayOf("Red","Green","Blue","Yellow","Black","Crimson","Orange")
//var lat = "35"
//var lon = "139"
// var BaseUrl = "http://api.openweathermap.org/"
//  var AppId = "2e65127e909e178d0af311a81f39948c"