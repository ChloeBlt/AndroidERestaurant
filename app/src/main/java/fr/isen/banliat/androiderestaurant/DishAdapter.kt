package fr.isen.banliat.androiderestaurant


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.banliat.androiderestaurant.databinding.CategoryCellBinding
import fr.isen.banliat.androiderestaurant.model.DishModel

class DishAdapter (val dishes: List<DishModel>, val onDishClicked: (DishModel) -> Unit): RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    class DishViewHolder(binding: CategoryCellBinding): RecyclerView.ViewHolder(binding.root){
        val dishPicture = binding.dishImage
        val dishName = binding.dishTitle
        val dishPrice = binding.dishDetails
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val binding = CategoryCellBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return DishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.dishName.text = dishes[position].name_fr
        // todo with picasso holder.dishPicture.setImageResource(dishes[position].getFirstPicture())

        Picasso.get()
            .load(dishes[position].getFirstPicture())
            .error(R.drawable.totonocuisine)
            .placeholder(R.drawable.totonocuisine)
            .into(holder.dishPicture)

        holder.dishPrice.text = dishes[position].getFormattedPrice()

        holder.itemView.setOnClickListener {
            onDishClicked(dishes[position])
        }
    }

    override fun getItemCount(): Int = dishes.size
}