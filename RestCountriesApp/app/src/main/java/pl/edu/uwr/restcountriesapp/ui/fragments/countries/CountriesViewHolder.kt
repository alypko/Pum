package pl.edu.uwr.restcountriesapp.ui.fragments.countries

import androidx.recyclerview.widget.RecyclerView
import pl.edu.uwr.restcountriesapp.databinding.CountriesListItemBinding
import pl.edu.uwr.restcountriesapp.model.CountryModel


class CountriesViewHolder(private val binding: CountriesListItemBinding ) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CountryModel){
        binding.capital.text = item.capital?.first()
        binding.name.text = item.name.official
    }
}