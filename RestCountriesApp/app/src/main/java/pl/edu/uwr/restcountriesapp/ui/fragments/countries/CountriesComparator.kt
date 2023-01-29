package pl.edu.uwr.restcountriesapp.ui.fragments.countries

import androidx.recyclerview.widget.DiffUtil
import pl.edu.uwr.restcountriesapp.model.CountryModel

class CountriesComparator : DiffUtil.ItemCallback<CountryModel>() {
    override fun areItemsTheSame(oldItem: CountryModel, newItem: CountryModel): Boolean {
        return newItem.name == oldItem.name
    }

    override fun areContentsTheSame(oldItem: CountryModel, newItem: CountryModel): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
}