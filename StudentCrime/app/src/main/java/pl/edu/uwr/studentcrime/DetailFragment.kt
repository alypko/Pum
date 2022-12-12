package pl.edu.uwr.studentcrime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        view.findViewById<FloatingActionButton>(R.id.fabDetail).setOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToListFragment()
            Navigation.findNavController(view).navigate(action)
        }

        view.findViewById<TextView>(R.id.details_title_text).text = arguments?.getString("title")
        view.findViewById<TextView>(R.id.details_description_text).text = arguments?.getString("description")
        view.findViewById<TextView>(R.id.details_student_index_text).text = arguments?.getInt("index").toString()
        view.findViewById<TextView>(R.id.details_is_solved_text).text = arguments?.getBoolean("solved").toString()

        return view
    }
}