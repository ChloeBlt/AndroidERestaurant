package fr.isen.banliat.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.isen.banliat.androiderestaurant.databinding.ActivityHomeBinding



class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

            binding.buttonStarter.setOnClickListener {
                changeActivity(getString(R.string.home_starter))
            }

            binding.buttonMainDish.setOnClickListener {
                changeActivity(getString(R.string.home_main_dish))
            }

            binding.buttonDessert.setOnClickListener {
                changeActivity(getString(R.string.home_dessert))
            }
    }

    private fun changeActivity(category: String) {
        val intent = Intent ( this, DishActivity::class.java)
        intent.putExtra("category_type", category)
        //toast
        Toast.makeText(this, "Vous avez choisie les " + category + "s", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

}