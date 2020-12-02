package pl.pwr.andz1.plantcatalog.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.pwr.andz1.plantcatalog.R

class CareTipsAdapter(
        private val values: Array<String>
) : RecyclerView.Adapter<CareTipsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_care_tips_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.contentView.text = item
        holder.icon.setImageResource(getIconForText(item))
    }

    override fun getItemCount(): Int = values.size

    private fun getIconForText(text: String): Int {
        return when {
            text.contains("water", ignoreCase = true) -> R.drawable.ic_water_drop
            text.contains("repot", ignoreCase = true) -> R.drawable.ic_flower_pot
            text.contains("place", ignoreCase = true) -> R.drawable.ic_sun
            text.contains("soil", ignoreCase = true) -> R.drawable.ic_soil
            text.contains("temperature", ignoreCase = true) -> R.drawable.ic_thermometer
            else -> R.drawable.ic_exclamation
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentView: TextView = view.findViewById(R.id.content)
        val icon: ImageView = view.findViewById(R.id.care_icon)
    }
}