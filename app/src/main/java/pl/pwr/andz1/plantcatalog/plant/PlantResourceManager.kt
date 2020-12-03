package pl.pwr.andz1.plantcatalog.plant

import android.content.Context

// to simplify things, I set the number of images for all plants to 6,
// instead of storing it in the plant object
const val NUMBER_OF_IMAGES = 3

class PlantResourceManager(private val context: Context) {
    fun getCategoryString(category: PlantCategory): String {
        val categoryResourceName: String = when (category) {
            PlantCategory.POT_PLANT -> "pot_plant"
            PlantCategory.GREEN -> "green"
            PlantCategory.SUCCULENT -> "succulent"
            PlantCategory.PALM -> "palm"
        }

        return context.resources.getString(
            getResourceId("category_name_$categoryResourceName", "string")
        )
    }

    fun getFullPlantName(name: String): String {
        return context.resources.getString(getResourceId(name + "_full_name", "string"))
    }

    fun getPlantDescription(name: String): String {
        return context.resources.getString(getResourceId(name + "_desc", "string"))
    }

    fun getPlantCareTips(name: String): Array<String> {
        return context.resources.getStringArray(getResourceId(name + "_care_tips", "array"))
    }

    fun getPlantImageIds(name: String): List<Int> {
        val ids = mutableListOf<Int>()
        for (i in 1..NUMBER_OF_IMAGES)
            ids.add(getResourceId("plants_${name}_$i", "drawable"))
        return ids
    }

    private fun getResourceId(name: String, type: String): Int {
        return context.resources.getIdentifier(name, type, context.packageName)
    }
}
