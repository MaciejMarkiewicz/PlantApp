package pl.pwr.andz1.plantcatalog.plant

object Plants {

    public val ALL_PLANTS: List<Plant> = arrayListOf(
        Plant("alocasia"),
        Plant("crassula"),
        Plant("palm"),
        Plant("sansevieria"),
        Plant("sophora"),
        Plant("schefflera"),
        Plant("aloe"),
        Plant("zamioculcas")
    )

    data class Plant(val id_name: String,
                     var favourite: Boolean = false,
                     var removed: Boolean = false)
}