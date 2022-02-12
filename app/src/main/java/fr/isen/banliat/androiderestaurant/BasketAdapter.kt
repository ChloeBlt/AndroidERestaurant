package fr.isen.banliat.androiderestaurant


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.banliat.androiderestaurant.databinding.CartCellBinding
import fr.isen.banliat.androiderestaurant.model.ItemBasket

private lateinit var binding: CartCellBinding

class BasketAdapter(private val data: MutableList<ItemBasket>, private val deleteItemListener: (ItemBasket) -> Unit) :
                    RecyclerView.Adapter<BasketAdapter.BasketHolder>() {

    class BasketHolder(private val binding: CartCellBinding): RecyclerView.ViewHolder(binding.root){
        val title = binding.dishTitle
        val price = binding.dishPrice
        val image = binding.dishPicture
        val quantity = binding.nbItemsBasket
        val remove = binding.removePicture
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): BasketHolder {
        val binding = CartCellBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BasketHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketHolder, position: Int) {

        val data : ItemBasket = data[position]


        holder.title.text  = data.dish.name_fr

        /*
        holder.price.text = data.dish.getFormattedPrice()
        holder.quantity.text = data.quantity.toString()
        val picture = data.dish.getFirstPicture()

        val picasso = Picasso.get()
        if (picture != null) {
            picasso
                .load(picture)
                .into(holder.image)
        } else {
            picasso
                .load(R.drawable.totonocuisine)
                .into(holder.image)
        }
        holder.remove.setOnClickListener {
            deleteItem(position)
            deleteItemListener.invoke(data)
        }

         */
    }





    private fun deleteItem(position: Int) {
        data.removeAt(position)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size


}



