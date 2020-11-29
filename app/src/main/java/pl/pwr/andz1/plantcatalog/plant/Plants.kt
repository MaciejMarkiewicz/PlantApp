package pl.pwr.andz1.plantcatalog.plant

object Plants {

    val plants: MutableList<Plant> = mutableListOf()

    private val plantsWithoutRemoved = arrayListOf(
        Plant("sansevieria", Category.SUCCULENT),
        Plant("alocasia", Category.POT_PLANT),
        Plant("crassula", Category.SUCCULENT),
        Plant("palm", Category.PALM),
        Plant("sophora", Category.POT_PLANT),
        Plant("schefflera", Category.GREEN),
        Plant("aloe", Category.SUCCULENT),
        Plant("zamioculcas", Category.POT_PLANT),
        )

    init {
        resetPlants()
    }

    fun filterPlants(predicate: (Plant) -> Boolean) {
        resetPlants()
        plants.retainAll(predicate)

    }

    fun removePlant(index: Int) {
        plantsWithoutRemoved.remove(plants[index])
        plants.removeAt(index)
    }

    private fun resetPlants() {
        plants.clear()
        plants.addAll(0, plantsWithoutRemoved)
    }

    data class Plant(val id_name: String,
                     val category: Category,
                     var favourite: Boolean = false,
                     var removed: Boolean = false)

    enum class Category {
        GREEN, PALM, SUCCULENT, POT_PLANT
    }
}