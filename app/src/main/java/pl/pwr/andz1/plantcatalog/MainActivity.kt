package pl.pwr.andz1.plantcatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import pl.pwr.andz1.plantcatalog.plant.Plants
import pl.pwr.andz1.plantcatalog.plant.PlantsAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.tabanim_toolbar)
        toolbar.title = resources.getString(R.string.app_name)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        val adapter = PlantsAdapter(Plants.ALL_PLANTS, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}