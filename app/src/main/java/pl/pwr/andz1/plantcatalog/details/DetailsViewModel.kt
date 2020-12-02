package pl.pwr.andz1.plantcatalog.details

import androidx.lifecycle.ViewModel

class DetailsViewModel : ViewModel() {
    var name: String = ""
    var desc: String = ""
    var imageResIds: List<Int> = listOf()
    var careTips: Array<String> = arrayOf()
    var galleryPosition: Int = 0
}