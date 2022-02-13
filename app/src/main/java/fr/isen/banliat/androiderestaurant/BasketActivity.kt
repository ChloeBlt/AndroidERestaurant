package fr.isen.banliat.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.banliat.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.banliat.androiderestaurant.model.Basket

class BasketActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBasketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)

        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.basketList.layoutManager = LinearLayoutManager(this)

        loadBasket()

        binding.basketValidationButton.setOnClickListener {
            val intent = Intent ( this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadBasket() {
        val basket = Basket.getBasket(this)
        val items = basket.items

        binding.basketList.adapter = BasketAdapter(items) {
            basket.removeItem(it)
            basket.save(this)
            loadBasket()
        }
    }
}
