package pl.pwr.andz1.plantcatalog

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.pwr.andz1.plantcatalog.databinding.ActivityMainBinding
import pl.pwr.andz1.plantcatalog.plant.Plants
import pl.pwr.andz1.plantcatalog.plant.PlantsAdapter


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var plantsAdapter: PlantsAdapter
    private lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            plantsAdapter = PlantsAdapter(Plants.plants, this@MainActivity)
            recyclerView.adapter = plantsAdapter
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            addSwipeDeleteAction(plantsAdapter, recyclerView)

            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)

            ArrayAdapter.createFromResource(
                this@MainActivity,
                R.array.categories,
                R.layout.spinner_menu_item
            ).also { adapter ->
                adapter.setDropDownViewResource(R.layout.spinner_menu_item)
                filterSpinner.adapter = adapter
            }
            filterSpinner.onItemSelectedListener = this@MainActivity
        }

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        plantsAdapter.addCategoryFilter(parent.getItemAtPosition(pos).toString())
        handleNoPlants()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_filter, menu)

        if (model.favFilterState) {
            model.favFilterState = false
            onFilterFavourites(menu?.findItem(R.id.only_fav)!!)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.only_fav -> {
                onFilterFavourites(item)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onFilterFavourites(item: MenuItem) {
        if (model.favFilterState) {
            item.setIcon(R.drawable.ic_fav_empty)
            plantsAdapter.removeFavFilter()
        } else {
            item.setIcon(R.drawable.ic_fav)
            plantsAdapter.addFavFilter()
        }

        model.favFilterState = !model.favFilterState
        handleNoPlants()
    }

    private fun addSwipeDeleteAction(adapter: PlantsAdapter, recyclerView: RecyclerView) {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                v: RecyclerView, h: RecyclerView.ViewHolder,
                t: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, dir: Int) {
                adapter.removeAt(viewHolder.adapterPosition)
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
                handleNoPlants()
            }
        }).attachToRecyclerView(recyclerView)
    }

    private fun handleNoPlants() {
        if (plantsAdapter.isEmpty())
            binding.noPlants.visibility = View.VISIBLE
        else
            binding.noPlants.visibility = View.GONE
    }
}

class MainViewModel : ViewModel() {
    var favFilterState: Boolean = false
}
