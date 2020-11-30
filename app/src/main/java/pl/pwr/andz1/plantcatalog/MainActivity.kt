package pl.pwr.andz1.plantcatalog

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.pwr.andz1.plantcatalog.plant.Plants
import pl.pwr.andz1.plantcatalog.plant.PlantsAdapter


const val FAV_STATE: String = "FAV_STATE"

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var plantsAdapter: PlantsAdapter
    private var favFilterState: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        favFilterState = savedInstanceState?.getBoolean(FAV_STATE, false) ?: false

        val recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        plantsAdapter = PlantsAdapter(Plants.plants, this)
        recyclerView.adapter = plantsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        addSwipeDeleteAction(plantsAdapter, recyclerView)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val spinner: Spinner = findViewById(R.id.filter_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.catrgories,
            R.layout.spinner_menu_item
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_menu_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = this
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(FAV_STATE, favFilterState)
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        plantsAdapter.addCategoryFilter(parent.getItemAtPosition(pos).toString())
        handleNoPlants()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_filter, menu)

        if (favFilterState) {
            favFilterState = false
            filterFavourites(menu?.findItem(R.id.only_fav)!!)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.only_fav -> {
                filterFavourites(item)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun filterFavourites(item: MenuItem) {
        if (favFilterState) {
            item.setIcon(R.drawable.ic_fav_empty)
            plantsAdapter.removeFavFilter()
        } else {
            item.setIcon(R.drawable.ic_fav)
            plantsAdapter.addFavFilter()
        }

        favFilterState = !favFilterState
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
            findViewById<TextView>(R.id.no_plants).visibility = View.VISIBLE
        else
            findViewById<TextView>(R.id.no_plants).visibility = View.GONE
    }
}

