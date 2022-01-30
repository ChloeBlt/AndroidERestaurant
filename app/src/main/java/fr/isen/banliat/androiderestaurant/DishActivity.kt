package fr.isen.banliat.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.banliat.androiderestaurant.databinding.ActivityDishBinding

class DishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainDishTitle.text = intent.getStringExtra("category_type")


        // *************** RecyclerView ***************

        binding.dishList.layoutManager = LinearLayoutManager(this)

        /*
        val dishes = listOf(
            Dish("curry", R.drawable.totonocuisine, "description"),
            Dish("curry", R.drawable.totonocuisine, "description")
        )
        */

        // ArrayList of class ItemsViewModel
        val dishes = ArrayList<Dish>()

        // This loop will create 20 Views containing
        for (i in 1..20) {
            dishes.add(Dish("Item" + i, R.drawable.totono_totoro, "description"))
        }

        // Setting the Adapter with the recyclerview
        binding.dishList.adapter = DishAdapter(dishes) {
            val intent = Intent(this, DetailActivity::class.java )
            intent.putExtra("dish",it)
            startActivity( intent)
        }

    }
}