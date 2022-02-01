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

        binding.dishPhotPager.adapter = DishPictureAdapter (this, dish.pictures)

        binding.dishDescription.text = dish.ingredients.joinToString(",") { it.name_fr}

        //quand je clique sur le bouton plus
        // j'increment nbInBasket
        // je met à jour le texte

        //quand je clique sur le bouton moins
        //je décrement nbInbasket tout en verifiant qu'on ne puisse pas avoir nbInBas < 0
        // je met à jour le texte
    }
}