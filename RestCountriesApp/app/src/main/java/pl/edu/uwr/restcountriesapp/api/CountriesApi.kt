package pl.edu.uwr.restcountriesapp.api

import pl.edu.uwr.restcountriesapp.model.CountryResponse
import retrofit2.Response
import retrofit2.http.GET

interface CountriesApi{
    @GET("all")
    suspend fun getCountries() : Response<CountryResponse>
}