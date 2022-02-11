package fr.isen.banliat.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.banliat.androiderestaurant.databinding.ActivityCartBinding
import fr.isen.banliat.androiderestaurant.model.CartData
import fr.isen.banliat.androiderestaurant.model.DishModel
import fr.isen.banliat.androiderestaurant.model.DishResult

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //displayDishesCart(dish)


    }

    private fun displayDishesCart(dishResult: List<CartData>) {
        binding.cartList.layoutManager = LinearLayoutManager(this)

        binding.cartList.adapter = CartAdapter(dishResult)
    }
}