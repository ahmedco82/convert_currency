package com.androimads.retrolin.ui

//import Connect.Connect.Companion.callApi


import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.androimads.retrolin.R
import com.androimads.retrolin.models.ViewModelConverter


import kotlinx.android.synthetic.main.activity_main.*

class MainActivity:AppCompatActivity() {

    private var TextViewResults:TextView? = null

    private var from: String? = null
    private var to: String? = null
    private var to_index:Int? = null
    private var from_index:Int? = null

    private lateinit var VModelConverter: ViewModelConverter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         TextViewResults = findViewById(R.id.textview_result)

         VModelConverter = ViewModelProviders.of(this).get(ViewModelConverter::class.java)
           VModelConverter.currency.observe(this, Observer {
               TextViewResults!!.text = ":${VModelConverter.currency.value}"
        })
        
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


       findViewById<View>(R.id.result_button).setOnClickListener {
           VModelConverter.loadData(to_index!!,from!!,to!!) }
    }
}
