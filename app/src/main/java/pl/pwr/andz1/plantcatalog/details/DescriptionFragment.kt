package pl.pwr.andz1.plantcatalog.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import pl.pwr.andz1.plantcatalog.databinding.FragmentDescriptionBinding


class DescriptionFragment : Fragment() {

    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!
    private val model: DetailsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDescriptionBinding.inflate(inflater, container, false)

        binding.apply {
            plantName.text = model.name
            plantDescription.text = model.desc
            plantImage.setImageResource(model.imageResIds[0])
        }
        return binding.root
    }
}