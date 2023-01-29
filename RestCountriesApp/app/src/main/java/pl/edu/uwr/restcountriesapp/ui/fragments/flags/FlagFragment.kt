package pl.edu.uwr.restcountriesapp.ui.fragments.flags

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelStore
import androidx.recyclerview.widget.LinearLayoutManager
import pl.edu.uwr.restcountriesapp.R
import pl.edu.uwr.restcountriesapp.databinding.FragmentFlagBinding
import pl.edu.uwr.restcountriesapp.model.CountryModel
import pl.edu.uwr.restcountriesapp.ui.fragments.countries.CountriesAdapter
import pl.edu.uwr.restcountriesapp.ui.fragments.countries.CountriesComparator
import pl.edu.uwr.restcountriesapp.ui.fragments.countries.CountriesViewModel
import pl.edu.uwr.restcountriesapp.util.Resource

class FlagFragment : Fragment() {
    private val flagViewModel: CountriesViewModel by viewModels()
    private lateinit var binding: FragmentFlagBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFlagBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flagViewModel.getAllCountries()

        val adapter = FlagAdapter(CountriesComparator())
        setupRecyclerView(adapter)

        observeCountries(adapter)
    }

    private fun setupRecyclerView(flagAdapter: FlagAdapter){
        binding.flagRV.apply {
            adapter = flagAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeCountries(countriesAdapter: FlagAdapter){
        flagViewModel.countries.observe(viewLifecycleOwner) { response ->
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