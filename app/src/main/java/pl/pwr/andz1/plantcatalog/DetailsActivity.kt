package pl.pwr.andz1.plantcatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pl.pwr.andz1.plantcatalog.plant.PLANT_NAME_KEY

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val plantName: String = intent.getStringExtra(PLANT_NAME_KEY).toString()
    }
}