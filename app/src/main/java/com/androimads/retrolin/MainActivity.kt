package com.androimads.retrolin

//import Connect.Connect.Companion.callApi
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback

//import retrofit2.Call
//import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var DataTextView:TextView? = null

    private var from: String? = null
    private var to: String? = null

    private var to_index:Int? = null
    private var from_index:Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DataTextView=findViewById(R.id.textview_result)
        //val currency_ = arrayOf("USD","EUR","CAD","CHF","GBP","SEK")
        var currencyArrays = resources.getStringArray(R.array.array_currency)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencyArrays)
        // Set the drop down view resource
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        // Finally, data bind the spinner object with dapter
        spinner_from.adapter = adapter;
        spinner_to.adapter = adapter;
        // Set an on item selected listener for spinner object
        spinner_from.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int , id: Long){
                from= "${parent.getItemAtPosition(position).toString()}"
                from_index = position
            }
            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }

        spinner_to.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent:AdapterView<*>, view: View, position: Int,id:Long){
                to= "${parent.getItemAtPosition(position).toString()}"
                to_index = position
            }
            override fun onNothingSelected(parent: AdapterView<*>){
                // Another interface callback
            }
        }
        findViewById<View>(R.id.result_button).setOnClickListener { getCurrentData() }
    }
    
    //  كلاس منفصل
    internal fun getCurrentData(){
        
        val retrofitCall=  RestClient.getInterfaceInstance( "https://open.exchangerate-api.com/")
        retrofitCall!!.getCurrentData(""+from+","+""+to).enqueue(object: Callback<com.androimads.retrolin.Response> {

         override fun onResponse(call:Call<com.androimads.retrolin.Response>, response:Response<com.androimads.retrolin.Response>) {
           if(response.code() == 200) {
             val weatherResponse = response.body()!!
               // println("print_:"+weatherResponse.rates )
                if(to_index==0){
                  DataTextView!!.text = "" +weatherResponse.rates!!.uSD
                }else if(to_index==1){
                  DataTextView!!.text = "" +weatherResponse.rates!!.eUR
                }else if(to_index==2){
                  DataTextView!!.text = "" +weatherResponse.rates!!.cAD
                }else if(to_index==3){
                  DataTextView!!.text = "" +weatherResponse.rates!!.cHF
                }else if(to_index==4){
                  DataTextView!!.text = "" +weatherResponse.rates!!.gBP
                }
                else if(to_index==5){
                  DataTextView!!.text = "" +weatherResponse.rates!!.sEK
                 }
              }
           }

            override fun onFailure(call: Call<com.androimads.retrolin.Response>, t: Throwable) {
              DataTextView!!.text = t.message
              //  println("print_CXX00:"+ t.message )
            }
        })
    }
}