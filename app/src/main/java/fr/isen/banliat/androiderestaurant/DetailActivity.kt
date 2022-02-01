package fr.isen.banliat.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import fr.isen.banliat.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.banliat.androiderestaurant.model.DishModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dish = intent.getSerializableExtra("dish") as DishModel
        initDetail(dish)

    }

    private fun initDetail(dish: DishModel) {
        var nbInBucket = 1

        binding.detailTitle.text = dish.name_fr

        binding.dishPhotPager.adapter = DishPictureAdapter(this, dish.pictures)

        binding.dishDescription.text = dish.ingredients.joinToString(", ") { it.name_fr }


        //button
        var numberDish = 1
        binding.totalButon.text =
            "ajouter au panier : " + (dish.prices[0].price.toFloat() * numberDish) + "€"

        //add
        binding.addBtn.setOnClickListener {
            numberDish += 1
            binding.quantiteText.text = "" + numberDish
            binding.totalButon.text = "ajouter au panier : " + (dish.prices[0].price.toFloat() * numberDish) + "€"

        }

        //less
        binding.removeBtn.setOnClickListener {
            if (numberDish > 0) {
                numberDish -= 1
                binding.quantiteText.text = "" + numberDish
                binding.totalButon.text = "ajouter au panier : " + (dish.prices[0].price.toFloat() * numberDish) + "€"

            }
        }
    }
}