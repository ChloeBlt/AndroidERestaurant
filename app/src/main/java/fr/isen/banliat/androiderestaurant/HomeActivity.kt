package fr.isen.banliat.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import fr.isen.banliat.androiderestaurant.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.cart -> { val intent = Intent(this, BasketActivity::class.java)
                startActivity(intent) }
            R.id.account -> { val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent) }
        }
        return super.onOptionsItemSelected(item)
    }

}