package fr.isen.banliat.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.banliat.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.banliat.androiderestaurant.model.CartData

class BasketActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBasketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_basket)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cartList.layoutManager = LinearLayoutManager(this)

        /*
        /////////////////////////

        var dish = intent.getSerializableExtra("data") as List<CartData>
        Log.e("test", dish.toString())
        displayDishesCart(dish)

        */



    }

    private fun displayDishesCart(dishResult: List<CartData>) {
        binding.cartList.layoutManager = LinearLayoutManager(this)

        binding.cartList.adapter = BasketAdapter(dishResult)
    }
}