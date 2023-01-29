package pl.edu.uwr.restcountriesapp.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import pl.edu.uwr.restcountriesapp.util.Constants.Companion.baseUrl
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    val api by lazy{
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(CountriesApi::class.java)
    }
}