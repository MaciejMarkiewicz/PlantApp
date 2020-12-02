package pl.pwr.andz1.plantcatalog.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import pl.pwr.andz1.plantcatalog.R
import pl.pwr.andz1.plantcatalog.databinding.FragmentCareTipsListBinding


class CareTipsFragment : Fragment() {

    private var _binding: FragmentCareTipsListBinding? = null
    private val binding get() = _binding!!
    private val model: DetailsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCareTipsListBinding.inflate(inflater, container, false)

        binding.apply {
            careTipsList.layoutManager = LinearLayoutManager(context)
            careTipsList.adapter = CareTipsAdapter(model.careTips)

            (activity as AppCompatActivity).setSupportActionBar(toolbarCare)
            (activity as AppCompatActivity)
                .supportActionBar?.title = resources.getString(R.string.care_tips)
        }

        return binding.root
    }
}