package pl.edu.uwr.studentcrime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment() {
    private lateinit var crimeList: MutableList<CrimeData>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCrimeData()
        val recyclerView: RecyclerView = view.findViewById(R.id.listRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CrimeListAdapter(requireContext(), crimeList)
    }

    private fun setCrimeData(){
        crimeList = mutableListOf<CrimeData>()
        var crimeTitle: String
        var crimeDescription: String
        var studentIndex: Int
        var isSolved: Boolean
        for (i in 1..20){
            crimeTitle = "Crime #$i"
            crimeDescription = "Crime description $i"
            studentIndex = 325170+i*15
            isSolved = i % 2 == 0
            val crime = CrimeData(crimeTitle, crimeDescription, studentIndex, isSolved)
            crimeList.add(crime)
        }
    }
}