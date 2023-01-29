package pl.edu.uwr.restcountriesapp.ui.fragments.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import pl.edu.uwr.restcountriesapp.databinding.CountriesListItemBinding
import pl.edu.uwr.restcountriesapp.model.CountryModel

class CountriesAdapter(itemComparator: CountriesComparator)
    : ListAdapter<CountryModel, CountriesViewHolder>(itemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : CountriesViewHolder{
        return CountriesViewHolder(
            CountriesListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position:Int){
        val item = getItem(position)
        holder.bind(item)
    }
}