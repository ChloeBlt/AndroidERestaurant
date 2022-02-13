package fr.isen.banliat.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import fr.isen.banliat.androiderestaurant.databinding.BasketCellBinding
import fr.isen.banliat.androiderestaurant.model.BasketItem

class BasketAdapter(private val items: List<BasketItem>,
                    val deleteCLickList: (BasketItem) -> Unit):
                    RecyclerView.Adapter<BasketAdapter.BasketHolder>() {

    lateinit var context: Context

    class BasketHolder(private val binding: BasketCellBinding): RecyclerView.ViewHolder(binding.root) {
        val dishNameBasket: TextView = binding.dishNameBasket
        val dishPriceBasket: TextView = binding.dishPriceBasket
        val quantity: TextView = binding.dishQuantityBasket
        val removeDish: ImageView = binding.removeDishBasket
        val dishImageBasket: ImageView = binding.dishPictureBasket
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketHolder {
        context = parent.context
        val binding =  BasketCellBinding.inflate(LayoutInflater.from(context),parent,false)
        return BasketHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketHolder, position: Int) {
        val itemBasket = items[position]

        holder.dishNameBasket.text = itemBasket.dish.name_fr

        holder.dishPriceBasket.text = "Total: " + (itemBasket.dish.prices.first().price.toFloat() * itemBasket.quantity) + "€"

        holder.quantity.text = "Quantité: " + itemBasket.quantity.toString()

        val picture = itemBasket.dish.getFirstPicture()
        val picasso = Picasso.get()
        if (picture != null) {
            picasso
                .load(picture)
                .error(R.drawable.totonocuisine)
                .placeholder(R.drawable.totonocuisine)
                .into(holder.dishImageBasket)
        } else {
            picasso
                .load(R.drawable.totonocuisine)
                .error(R.drawable.totonocuisine)
                .placeholder(R.drawable.totonocuisine)
                .into(holder.dishImageBasket)
        }

        holder.removeDish.setOnClickListener {
            deleteCLickList.invoke(itemBasket)
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}

