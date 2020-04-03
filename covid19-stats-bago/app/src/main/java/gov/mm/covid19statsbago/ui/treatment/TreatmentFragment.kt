package gov.mm.covid19statsbago.ui.treatment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import gov.mm.covid19statsbago.R

class TreatmentFragment : Fragment() {



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_treatment, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)

        return root
    }
}
