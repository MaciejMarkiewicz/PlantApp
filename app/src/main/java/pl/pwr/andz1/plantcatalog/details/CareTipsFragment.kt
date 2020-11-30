package pl.pwr.andz1.plantcatalog.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
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
        }

        return binding.root
    }
}