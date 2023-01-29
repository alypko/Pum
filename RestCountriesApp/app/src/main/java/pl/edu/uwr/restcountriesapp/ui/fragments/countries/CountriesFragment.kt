package pl.edu.uwr.restcountriesapp.ui.fragments.countries

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import pl.edu.uwr.restcountriesapp.R
import pl.edu.uwr.restcountriesapp.databinding.FragmentCountriesBinding
import pl.edu.uwr.restcountriesapp.model.CountryModel
import pl.edu.uwr.restcountriesapp.util.Resource


class CountriesFragment : Fragment() {

    private val countriesViewModel: CountriesViewModel by viewModels()
    private lateinit var binding: FragmentCountriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countriesViewModel.getAllCountries()

        val adapter = CountriesAdapter(CountriesComparator())
        setupRecyclerView(adapter)

        observeCountries(adapter)
    }

    private fun setupRecyclerView(countriesAdapter: CountriesAdapter){
        binding.countriesRV.apply {
            adapter = countriesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeCountries(countriesAdapter: CountriesAdapter){
        countriesViewModel.countries.observe(viewLifecycleOwner) { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { res ->
                        countriesAdapter.submitList(res.map { CountryModel(it.name, it.capital, it.flags) })
                    }
                }
                is Resource.Error -> {
                    response.message?.let { Log.e("RESPONSE_ERROR", "Error occured $it") }
                }
                is Resource.Loading -> Log.d("RESPONSE_LOADING", "LOADING DATA")
            }

        }
    }

}