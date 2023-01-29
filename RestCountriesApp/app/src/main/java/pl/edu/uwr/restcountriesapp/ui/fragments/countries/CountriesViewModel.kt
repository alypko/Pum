package pl.edu.uwr.restcountriesapp.ui.fragments.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.edu.uwr.restcountriesapp.model.CountryResponse
import pl.edu.uwr.restcountriesapp.repository.Repository
import pl.edu.uwr.restcountriesapp.util.Resource
import retrofit2.Response

class CountriesViewModel : ViewModel() {
    private val repository = Repository()
    private val _countries: MutableLiveData<Resource<CountryResponse>> = MutableLiveData()
    val countries: LiveData<Resource<CountryResponse>>
        get() = _countries

    fun getAllCountries() = viewModelScope.launch{
        _countries.postValue(Resource.Loading())
        val response = repository.getCountries()
        _countries.postValue(handleCountryResponse(response))
    }

    private fun handleCountryResponse(response: Response<CountryResponse>) : Resource<CountryResponse> {
        if (response.isSuccessful)
            response.body()?.let { return Resource.Success(it) }
        return Resource.Error(response.message())
    }


}
