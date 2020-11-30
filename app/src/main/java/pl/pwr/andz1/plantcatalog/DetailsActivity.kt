package pl.pwr.andz1.plantcatalog

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import pl.pwr.andz1.plantcatalog.details.CareTipsFragment
import pl.pwr.andz1.plantcatalog.details.DescriptionFragment
import pl.pwr.andz1.plantcatalog.details.DetailsViewModel
import pl.pwr.andz1.plantcatalog.details.GalleryFragment
import pl.pwr.andz1.plantcatalog.plant.NUMBER_OF_IMAGES
import pl.pwr.andz1.plantcatalog.plant.PLANT_NAME_KEY
import pl.pwr.andz1.plantcatalog.plant.PlantResourceManager


private const val NUM_PAGES_NON_GALLERY = 2
private const val TOTAL_NUM_PAGES = NUM_PAGES_NON_GALLERY + NUMBER_OF_IMAGES

class DetailsActivity : FragmentActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var plantName: String
    private val plantResourceManager: PlantResourceManager = PlantResourceManager(this)
    private val model: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        plantName = intent.getStringExtra(PLANT_NAME_KEY).toString()

        viewPager = findViewById(R.id.pager)
        viewPager.adapter = DetailsAdapter(this)

        model.careTips = plantResourceManager.getPlantCareTips(plantName)
        model.name = plantResourceManager.getFullPlantName(plantName)
        model.desc = plantResourceManager.getPlantDescription(plantName)
        model.imageResIds = plantResourceManager.getPlantImageIds(plantName)
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0)
            super.onBackPressed()
        else
            viewPager.currentItem = viewPager.currentItem - 1
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
