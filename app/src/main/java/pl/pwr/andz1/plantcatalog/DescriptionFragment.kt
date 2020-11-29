package pl.pwr.andz1.plantcatalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.databinding.DataBindingUtil
import pl.pwr.andz1.plantcatalog.databinding.FragmentDescriptionBinding


class DescriptionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: DescriptionFragment = DataBindingUtil.inflate(
            inflater, R.layout.fragment_description, container, false)

        return binding.view
    }
}