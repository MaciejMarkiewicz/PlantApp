package pl.pwr.andz1.plantcatalog.plant

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.pwr.andz1.plantcatalog.R

class PlantsAdapter(private val plants: MutableList<Plants.Plant>, private val context: Context) :
    RecyclerView.Adapter<PlantsAdapter.ViewHolder>() {
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val image: ImageView = itemView.findViewById(R.id.image_view)
        val favButton: ImageButton = itemView.findViewById(R.id.fav_button)
        val title: TextView = itemView.findViewById(R.id.plant_name)
        val category: TextView = itemView.findViewById(R.id.plant_category)
    }

    var favFilterOn: Boolean = false
        private set
    private var categoryFilter: Plants.Category? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantsAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.list_item_layout, parent, false)

        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: PlantsAdapter.ViewHolder, position: Int) {
        val plant: Plants.Plant = plants[position]
        val name: String = plant.id_name

        val titleView = viewHolder.title
        titleView.text = context.resources.getString(
            getResourceId(name + "_full_name", "string")
        )

        val categoryView = viewHolder.category
        categoryView.text = getCategoryString(plant.category)

        val imageView = viewHolder.image
        imageView.setImageResource(getResourceId("plants_${name}_1", "drawable"))

        val favButton = viewHolder.favButton
        favButton.setOnClickListener {
            plant.favourite = !plant.favourite
            handleFavButton(plant, favButton)
        }

        handleFavButton(plant, favButton)
    }

    override fun getItemCount(): Int {
        return plants.size
    }

    fun removeAt(position: Int) {
        Plants.removePlant(position)
    }

    fun isEmpty(): Boolean {
        return plants.isEmpty()
    }

    fun removeFavFilter() {
        favFilterOn = false
        filter()
    }

    fun addFavFilter() {
        favFilterOn = true
        filter()
    }

    fun addCategoryFilter(name: String) {
        categoryFilter = getCategoryFromName(name)
        filter()
    }

    private fun filter() {
        val categoryPredicate: (Plants.Plant) -> Boolean = if (categoryFilter == null) {
            { true }
        } else {
            { plant: Plants.Plant -> plant.category == categoryFilter }
        }

        val favPredicate: (Plants.Plant) -> Boolean = if (favFilterOn) {
            { plant: Plants.Plant -> plant.favourite }
        } else {
            { true }
        }

        Plants.filterPlants { plant: Plants.Plant -> categoryPredicate(plant) && favPredicate(plant) }
        notifyDataSetChanged()
    }

    private fun getCategoryString(category: Plants.Category): String {
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

    private fun getCategoryFromName(name: String): Plants.Category? {
        return when (name) {
            "Pot plants" -> Plants.Category.POT_PLANT
            "Green plants" -> Plants.Category.GREEN
            "Succulents" -> Plants.Category.SUCCULENT
            "Palms" -> Plants.Category.PALM
            else -> return null
        }
    }

    private fun handleFavButton(plant: Plants.Plant, favButton: ImageButton) {
        if (plant.favourite) {
            favButton.setImageResource(R.drawable.ic_fav)
            favButton.isSelected = true
        } else {
            favButton.setImageResource(R.drawable.ic_fav_empty)
            favButton.isSelected = false
        }
    }

    private fun getResourceId(name: String, type: String): Int {
        return context.resources.getIdentifier(name, type, context.packageName)
    }
}