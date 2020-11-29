package pl.pwr.andz1.plantcatalog.plant

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.pwr.andz1.plantcatalog.R

class PlantsAdapter(private val plants: List<Plants.Plant>, private val context: Context) :
    RecyclerView.Adapter<PlantsAdapter.ViewHolder>() {
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val image: ImageView = itemView.findViewById(R.id.image_view)
        val favButton: ImageButton = itemView.findViewById(R.id.fav_button)
        val title: TextView = itemView.findViewById(R.id.card_title)
        val subtitle: TextView = itemView.findViewById(R.id.card_subtitle)
    }

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
            getResourceId(name + "_full_name","string"))

        val subtitleView = viewHolder.subtitle
        subtitleView.text = context.resources.getString(
            getResourceId(name + "_full_name","string"))

        val imageView = viewHolder.image
        imageView.setImageResource(getResourceId("plants_${name}_1","drawable"))
    }

    private fun getResourceId(name: String, type: String): Int {
        return context.resources.getIdentifier(name, type, context.packageName)
    }
    override fun getItemCount(): Int {
        return plants.size
    }
}