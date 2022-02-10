package fr.isen.banliat.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
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

        binding.totalButon.setOnClickListener{
            val intent = Intent (this, LoginActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"Clicked", Toast.LENGTH_LONG).show()
        }

    }

    private fun initDetail(dish: DishModel) {

        binding.detailTitle.text = dish.name_fr

        binding.dishPhotPager.adapter = DishPictureAdapter(this, dish.pictures)

        binding.dishDescription.text = dish.ingredients.joinToString(", ") { it.name_fr }



        var numberDish = 1
        binding.totalButon.text = "ajouter au panier : " + (dish.prices[0].price.toFloat() * numberDish) + "€"

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
