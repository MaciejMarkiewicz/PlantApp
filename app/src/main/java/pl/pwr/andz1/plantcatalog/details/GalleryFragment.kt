package pl.pwr.andz1.plantcatalog.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

import pl.pwr.andz1.plantcatalog.databinding.FragmentGalleryBinding


class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private val model: DetailsViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        binding.apply {
            galleryImage.setImageResource(model.imageResIds[model.galleryPosition])
        }

        return binding.root
    }
}