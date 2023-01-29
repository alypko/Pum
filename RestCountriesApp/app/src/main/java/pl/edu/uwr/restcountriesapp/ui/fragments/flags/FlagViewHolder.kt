package pl.edu.uwr.restcountriesapp.ui.fragments.flags

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pl.edu.uwr.restcountriesapp.databinding.FlagListItemBinding
import pl.edu.uwr.restcountriesapp.model.CountryModel

class FlagViewHolder(private val binding: FlagListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CountryModel){
        binding.name.text = item.name.official
        Glide.with(itemView)
            .load(item.flags.png)
            .into(binding.FlagImage)
    }
}