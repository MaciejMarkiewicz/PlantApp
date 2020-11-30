package pl.pwr.andz1.plantcatalog.details

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import pl.pwr.andz1.plantcatalog.DetailsActivity
import pl.pwr.andz1.plantcatalog.R
import pl.pwr.andz1.plantcatalog.plant.PLANT_NAME_KEY

class GalleryAdapter(
    private val imageIds: List<Int>
    ) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_gallery_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageId = imageIds[position]
        holder.image.setImageResource(imageId)

        holder.itemView.setOnClickListener {
            if (holder.itemView.visibility == View.GONE) {
                (holder.itemView as ImageView).setImageResource(imageId)
                holder.itemView.visibility = View.VISIBLE
            } else
                holder.itemView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = imageIds.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.galleryImage)
        val zoomedImage: ImageView = view.findViewById(R.id.zoomedImage)
    }
}