package fr.isen.banliat.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.isen.banliat.androiderestaurant.databinding.CategoryCellBinding

class DishAdapter (val dishes: List<Dish>, val onDishClicked: (Dish) -> Unit): RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    class DishViewHolder(binding: CategoryCellBinding): RecyclerView.ViewHolder(binding.root){
        val dishImage = binding.dishImage
        val dishTitle = binding.dishTitle
        val dishDetails = binding.dishDetails

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val binding = CategoryCellBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return DishViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.dishTitle.text = dishes[position].title
        holder.dishImage.setImageResource(dishes[position].image)
        holder.dishDetails.text = dishes[position].details

        holder.itemView.setOnClickListener {
            onDishClicked(dishes[position])
        }

    }

    override fun getItemCount(): Int = dishes.size

}