package pl.pwr.andz1.plantcatalog.plant

import android.content.Context

// to simplify things, I set the number of images for all plants to 6,
// instead of storing it in the plant object
const val NUMBER_OF_IMAGES = 3

class PlantResourceManager(private val context: Context) {
    fun getCategoryString(category: Plants.Category): String {
        val categoryResourceName: String = when (category) {
            Plants.Category.POT_PLANT -> "pot_plant"
            Plants.Category.GREEN -> "green"
            Plants.Category.SUCCULENT -> "succulent"
            Plants.Category.PALM -> "palm"
        }

        return context.resources.getString(
            getResourceId("category_name_$categoryResourceName", "string")
        )
    }

    fun getCategoryFromName(name: String): Plants.Category? {
        return when (name) {
            "Pot plants" -> Plants.Category.POT_PLANT
            "Green plants" -> Plants.Category.GREEN
            "Succulents" -> Plants.Category.SUCCULENT
            "Palms" -> Plants.Category.PALM
            else -> return null
        }
    }

    public fun getFullPlantName(name: String): String {
        return context.resources.getString(getResourceId(name + "_full_name", "string"))
    }

    public fun getPlantDescription(name: String): String {
        return context.resources.getString(getResourceId(name + "_desc", "string"))
    }

    public fun getPlantCareTips(name: String): Array<String> {
        return context.resources.getStringArray(getResourceId(name + "_care_tips", "array"))
    }

    public fun getPlantImageIds(name: String): List<Int> {
        val ids = mutableListOf<Int>()
        for (i in 1..NUMBER_OF_IMAGES)
            ids.add(getResourceId("plants_${name}_$i", "drawable"))
        return ids
    }

    private fun getResourceId(name: String, type: String): Int {
        return context.resources.getIdentifier(name, type, context.packageName)
    }
}