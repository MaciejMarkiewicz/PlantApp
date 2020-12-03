package pl.pwr.andz1.plantcatalog.plant

import androidx.lifecycle.ViewModel


class PlantsViewModel : ViewModel() {

    var plants = listOf<Plant>()
        private set

    var favFilterState = false
        set(value) {
            field = value
            filterPlants()
        }

    var categoryFilter: Int = 0
        set(value) {
            field = value
            filterPlants()
        }

    private val plantsWithoutRemoved = mutableListOf(
        Plant("sansevieria", PlantCategory.SUCCULENT),
        Plant("alocasia", PlantCategory.POT_PLANT),
        Plant("crassula", PlantCategory.SUCCULENT),
        Plant("palm", PlantCategory.PALM),
        Plant("sophora", PlantCategory.POT_PLANT),
        Plant("schefflera", PlantCategory.GREEN),
        Plant("aloe", PlantCategory.SUCCULENT),
        Plant("zamioculcas", PlantCategory.POT_PLANT),
        )

    init {
        resetPlants()
    }

    private fun filterPlants() {
        val categoryPredicate = if (categoryFilter == 0) {
            { true }
        } else {
            { plant: Plant -> plant.category == PlantCategory.values()[categoryFilter - 1] }
        }

        val favPredicate = if (favFilterState) {
            { plant: Plant -> plant.favourite }
        } else {
            { true }
        }

        plants = plantsWithoutRemoved.filter{ plant: Plant ->
            categoryPredicate(plant) && favPredicate(plant) }
    }

    fun removePlantAt(index: Int) {
        plantsWithoutRemoved.remove(plants[index])
        resetPlants()
        filterPlants()
    }

    private fun resetPlants() {
        plants = plantsWithoutRemoved.toList()
    }
}

data class Plant(val id_name: String,
                 val category: PlantCategory,
                 var favourite: Boolean = false,
                 var removed: Boolean = false)

enum class PlantCategory {
    GREEN, PALM, SUCCULENT, POT_PLANT
}