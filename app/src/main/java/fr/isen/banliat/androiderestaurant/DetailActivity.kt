package fr.isen.banliat.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import fr.isen.banliat.androiderestaurant.model.Dish
import fr.isen.banliat.androiderestaurant.model.DishModel

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        findViewById<TextView>(R.id.detailTitle).text = (intent.getSerializableExtra("dish") as DishModel).name_fr


    }
}