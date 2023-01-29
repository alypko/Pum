package pl.edu.uwr.restcountriesapp.ui.fragments.flags

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import pl.edu.uwr.restcountriesapp.databinding.FlagListItemBinding
import pl.edu.uwr.restcountriesapp.model.CountryModel
import pl.edu.uwr.restcountriesapp.ui.fragments.countries.CountriesComparator

class FlagAdapter(itemComparator: CountriesComparator)
    : ListAdapter<CountryModel, FlagViewHolder>(itemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : FlagViewHolder{
        return FlagViewHolder(
            FlagListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FlagViewHolder, position: Int){
        val item = getItem(position)
        holder.bind(item)
    }

}