package pl.edu.uwr.restcountriesapp.repository

import pl.edu.uwr.restcountriesapp.api.RetrofitInstance
import pl.edu.uwr.restcountriesapp.model.CountryResponse
import retrofit2.Response

class Repository {
    suspend fun getCountries() : Response<CountryResponse> = RetrofitInstance.api.getCountries()
}