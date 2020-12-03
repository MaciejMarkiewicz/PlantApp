package pl.pwr.andz1.plantcatalog.plant

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.pwr.andz1.plantcatalog.DetailsActivity
import pl.pwr.andz1.plantcatalog.R

const val PLANT_NAME_KEY = "pl.pwr.andz1.plantcatalog.PLANT_NAME"

class PlantsAdapter(private val model: PlantsViewModel, private val context: Context) :
    RecyclerView.Adapter<PlantsAdapter.ViewHolder>() {
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val image: ImageView = itemView.findViewById(R.id.image_view)
        val favButton: ImageButton = itemView.findViewById(R.id.fav_button)
        val title: TextView = itemView.findViewById(R.id.plant_name)
        val category: TextView = itemView.findViewById(R.id.plant_category)
    }

    private val plantResourceManager = PlantResourceManager(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantsAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.main_list_item_layout, parent, false)

        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: PlantsAdapter.ViewHolder, position: Int) {
        val plant: Plant = model.plants[position]
        val name: String = plant.id_name

        val titleView = viewHolder.title
        titleView.text = plantResourceManager.getFullPlantName(name)

        val categoryView = viewHolder.category
        categoryView.text = plantResourceManager.getCategoryString(plant.category)

        val imageView = viewHolder.image
        imageView.setImageResource(plantResourceManager.getPlantImageIds(name)[0])

        val favButton = viewHolder.favButton
        favButton.setOnClickListener {
            plant.favourite = !plant.favourite
            handleFavButton(plant, favButton)
        }

        handleFavButton(plant, favButton)

        viewHolder.itemView.setOnClickListener {
            val intent: Intent = Intent(context, DetailsActivity::class.java).apply {
                putExtra(PLANT_NAME_KEY, plant.id_name)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return model.plants.size
    }

    private fun handleFavButton(plant: Plant, favButton: ImageButton) {
        if (plant.favourite) {
            favButton.setImageResource(R.drawable.ic_fav)
            favButton.isSelected = true
        } else {
            favButton.setImageResource(R.drawable.ic_fav_empty)
            favButton.isSelected = false
        }
    }
}