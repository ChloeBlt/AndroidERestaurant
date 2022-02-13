package fr.isen.banliat.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.banliat.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.banliat.androiderestaurant.model.Basket
import java.io.File
import android.content.Context

class BasketActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBasketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)

        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadBasket()

        binding.basketValidationButton.setOnClickListener {
            val intent = Intent ( this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadBasket() {
        val basket = Basket.getBasket(this)
        val items = basket.items
        val file = File(cacheDir.absolutePath + Basket.BASKET_FILE)
        if (file.exists()) {
            binding.basketList.adapter = BasketAdapter(items) {
                basket.removeItem(it)
                basket.save(this)
                loadBasket()
            }
            binding.basketLoading.visibility = View.GONE
            binding.basketList.layoutManager = LinearLayoutManager(this)

            val total = items.sumOf { it.quantity * it.dish.prices.first().price.toDouble()  }
            binding.basketTotal.text = "total de votre commande : " + "$total" + "€"

        }
    }
}
