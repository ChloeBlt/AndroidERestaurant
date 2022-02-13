package fr.isen.banliat.androiderestaurant

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import fr.isen.banliat.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.banliat.androiderestaurant.model.*

lateinit var binding: ActivityDetailBinding

class DetailActivity : MenuActivity() {

    var quantity = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dish = intent.getSerializableExtra("dish") as DishModel
        initDetail(dish)

        binding.addBtn.setOnClickListener {
            quantity += 1
            if (quantity >= 1) {
                binding.totalButon.visibility = View.VISIBLE
            }
            binding.quantiteText.text = "" + quantity
            binding.totalButon.text =
                "Total: " + (dish.prices[0].price.toFloat() * quantity) + "€"
        }
        binding.removeBtn.setOnClickListener {
            if (quantity > 0) {
                quantity -= 1
                if (quantity == 0) {
                    binding.totalButon.visibility = View.GONE
                }
                binding.quantiteText.text = "" + quantity
                binding.totalButon.text =
                    "Total : " + (dish.prices[0].price.toFloat() * quantity) + "€"
                }
        }

        binding.totalButon.setOnClickListener {
            val basket = Basket.getBasket(this)
            basket.addItem(dish, quantity)
            basket.save(this)
            Snackbar.make(binding.root, "ajouté au panier", Snackbar.LENGTH_LONG).show()
            invalidateOptionsMenu()
        }
    }

    private fun initDetail(dish: DishModel) {
        binding.detailTitle.text = dish.name_fr
        binding.dishPhotPager.adapter = DishPictureAdapter(this, dish.pictures)
        binding.dishDescription.text = dish.ingredients.joinToString(", ") { it.name_fr }
        binding.totalButon.text = "ajouter au panier : " + (dish.prices[0].price.toFloat()) + "€"
    }
}

