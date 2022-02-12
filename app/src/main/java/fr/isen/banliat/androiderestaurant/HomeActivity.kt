package fr.isen.banliat.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import fr.isen.banliat.androiderestaurant.databinding.ActivityHomeBinding


class HomeActivity : MenuActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        // Toast.makeText(this, "Vous avez choisie les " + category + "s", Toast.LENGTH_SHORT).show()
        Log.i("INFO","End of HomeActivity")
        startActivity(intent)
    }

    override fun onResume() {
        invalidateOptionsMenu()
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("HomeActivity","destroyed")
    }

}

