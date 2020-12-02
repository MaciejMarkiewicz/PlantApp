package pl.pwr.andz1.plantcatalog

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import pl.pwr.andz1.plantcatalog.databinding.ActivityDetailsBinding
import pl.pwr.andz1.plantcatalog.details.CareTipsFragment
import pl.pwr.andz1.plantcatalog.details.DescriptionFragment
import pl.pwr.andz1.plantcatalog.details.DetailsViewModel
import pl.pwr.andz1.plantcatalog.details.GalleryFragment
import pl.pwr.andz1.plantcatalog.plant.NUMBER_OF_IMAGES
import pl.pwr.andz1.plantcatalog.plant.PLANT_NAME_KEY
import pl.pwr.andz1.plantcatalog.plant.PlantResourceManager


private const val NUM_PAGES_NON_GALLERY = 2
private const val TOTAL_NUM_PAGES = NUM_PAGES_NON_GALLERY + NUMBER_OF_IMAGES

class DetailsActivity : AppCompatActivity() {

    private val plantResourceManager: PlantResourceManager = PlantResourceManager(this)
    private val model: DetailsViewModel by viewModels()
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val plantName = intent.getStringExtra(PLANT_NAME_KEY).toString()

        binding.viewPager.adapter = DetailsAdapter(this)

        model.careTips = plantResourceManager.getPlantCareTips(plantName)
        model.name = plantResourceManager.getFullPlantName(plantName)
        model.desc = plantResourceManager.getPlantDescription(plantName)
        model.imageResIds = plantResourceManager.getPlantImageIds(plantName)
    }

    override fun onBackPressed() {
        if (binding.viewPager.currentItem == 0)
            super.onBackPressed()
        else
            binding.viewPager.currentItem = binding.viewPager.currentItem - 1
    }

    private inner class DetailsAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = TOTAL_NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> DescriptionFragment()
                1 -> CareTipsFragment()
                else -> {
                    model.galleryPosition = position - NUM_PAGES_NON_GALLERY
                    GalleryFragment()
                }
            }
        }
    }
}
