package pl.pwr.andz1.plantcatalog.details

import androidx.lifecycle.ViewModel

class DetailsViewModel : ViewModel() {
    public var name: String = ""
    public var desc: String = ""
    public var imageResIds: List<Int> = listOf()
    public var careTips: Array<String> = arrayOf()
    public var galleryPosition: Int = 0
}