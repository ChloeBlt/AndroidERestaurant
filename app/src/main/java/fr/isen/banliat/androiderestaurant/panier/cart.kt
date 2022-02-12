package fr.isen.banliat.androiderestaurant.panier

import fr.isen.banliat.androiderestaurant.model.DishModel


// *****************************************************************
// *****************************************************************
// ************************** CART ACIVITY *************************
// *****************************************************************
// *****************************************************************

/*

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cartList.layoutManager = LinearLayoutManager(this)


        /////////////////////////

        var dish = intent.getSerializableExtra("data") as List<CartData>
        Log.e("test", dish.toString())
        displayDishesCart(dish)





    }

    private fun displayDishesCart(dishResult: List<CartData>) {
        binding.cartList.layoutManager = LinearLayoutManager(this)

        binding.cartList.adapter = CartAdapter(dishResult)
    }
}

*/




// *****************************************************************
// *****************************************************************
// ************************* CART ADAPTER  *************************
// *****************************************************************
// *****************************************************************


/*
package fr.isen.banliat.androiderestaurant.panier

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.banliat.androiderestaurant.R
import fr.isen.banliat.androiderestaurant.databinding.CartCellBinding
import fr.isen.banliat.androiderestaurant.model.CartData


class CartAdapter(private val dishes: List<CartData>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(private val binding: CartCellBinding): RecyclerView.ViewHolder(binding.root){
        val dishPicture = binding.dishImage
        val dishName = binding.dishTitle
        val dishQuantity = binding.dishQuantity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartCellBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val dish = dishes[position]
        holder.dishName.text = dish.DishName

        Picasso.get()
            .load(R.drawable.totono)
            .error(R.drawable.totonocuisine)
            .placeholder(R.drawable.totonocuisine)
            .into(holder.dishPicture)

        holder.dishQuantity.text = "Quantité : " +dishes[position].quantity.toString()
    }

    override fun getItemCount(): Int = dishes.size
}

*/



// *****************************************************************
// *****************************************************************
// ************************* DishCart.kt  *************************
// *****************************************************************
// *****************************************************************


/*
package fr.isen.banliat.androiderestaurant.model

import java.io.Serializable

data class DishCart(val dishName: DishModel,
                    val quantity: Int): Serializable

data class CartData(
    val DishName: String,
    var quantity : Int ): Serializable

*/

