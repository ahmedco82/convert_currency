import com.androimads.retrolin.ApiClient.BASE_URL
import com.androimads.retrolin.CurrencyService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Url

class Connect {
    var BASE_URL =  "https://open.exchangerate-api.com/"

      //  https://open.exchangerate-api.com/v6/latest?symbols=USD,GBP

    companion object {


      private fun getRetrofit(Url:String):Retrofit {
           return  Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(Url).build()
      }

        fun getApiData():Retrofit {
            val retrofitApi = getRetrofit(BASE_URL)
            return retrofitApi
        }

        fun callApi():CurrencyService{
            val retrofitCall = getApiData()
            return retrofitCall.create(CurrencyService::class.java)
         }
    }
}