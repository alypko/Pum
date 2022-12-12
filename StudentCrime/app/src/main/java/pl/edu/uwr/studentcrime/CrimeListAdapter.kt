package pl.edu.uwr.studentcrime

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class CrimeListAdapter(
    private val context: Context,
    private val crimeList: MutableList<CrimeData>
) : RecyclerView.Adapter<CrimeListAdapter.CrimeListViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeListAdapter.CrimeListViewHolder {
        return CrimeListViewHolder(LayoutInflater.from(context).inflate(R.layout.crime_list_item,parent, false))
    }

    override fun onBindViewHolder(holder: CrimeListViewHolder, position: Int) {
        val crime = crimeList[position]
        holder.crimeTitle.text = crime.crimeTitle
        holder.crimeImg.visibility = if(crime.isSolved) View.VISIBLE else View.INVISIBLE
        holder.itemView.setOnClickListener {
            val action = ListFragmentDirections
                        .actionListFragmentToDetailFragment(
                            title = crime.crimeTitle,
                            description = crime.crimeDescription,
                            index = crime.studentIndex,
                            solved = crime.isSolved
                        )
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = crimeList.size


    inner class CrimeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val crimeTitle: TextView = itemView.findViewById(R.id.crimeTitle)
        val crimeImg: ImageView = itemView.findViewById(R.id.crimeImage)
    }
}