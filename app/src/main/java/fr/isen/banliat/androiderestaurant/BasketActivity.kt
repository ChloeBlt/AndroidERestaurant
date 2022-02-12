package fr.isen.banliat.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import fr.isen.banliat.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.banliat.androiderestaurant.model.Basket
import java.io.File

class BasketActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBasketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.basketList.layoutManager = LinearLayoutManager(this)

        readFile()


    }

    private fun readFile() {
        val gson = GsonBuilder().setPrettyPrinting().create()
        val file = File(cacheDir.absolutePath + "UserCart.Json")
        if (file.exists()) {

            //binding.basketLoading.visibility = View.GONE
            binding.basketList.layoutManager = LinearLayoutManager(this)

            val basket = gson.fromJson(file.readText(), Basket::class.java)


            binding.basketList.adapter = BasketAdapter(basket.items) {

                basket.items.remove(it)
                resetBasket(basket)
                //invalidateOptionsMenu()


            }

        }
    }
    private fun resetBasket(basket: Basket) {
        val file = File(cacheDir.absolutePath + "UserCart.Json")
        saveInMemory(basket, file)
    }

    private fun saveInMemory(basket: Basket, file: File){
        saveDishCount(basket)
        file.writeText(GsonBuilder().create().toJson(basket))
    }

    private fun saveDishCount(basket: Basket){
        val count = basket.items.sumOf { it.quantity }
        val sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
        sharedPreferences.edit().putInt("basket_count", count).apply()
        if(count <= 0)
            startActivity(Intent(this, HomeActivity::class.java))
    }














/*
    private fun displayDishesCart(dishResult: List<CartData>) {
        binding.cartList.layoutManager = LinearLayoutManager(this)

        binding.cartList.adapter = BasketAdapter(dishResult)
            }


 */
}