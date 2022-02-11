package fr.isen.banliat.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
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
            .load(R.drawable.ic_launcher_foreground)//dishes[position].getFirstPicture())
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.dishPicture)

        holder.dishQuantity.text = "Quantité : " +dishes[position].quantity.toString()
        val data = dishes[position]
    }

    override fun getItemCount(): Int = dishes.size
}