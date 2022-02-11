package fr.isen.banliat.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import fr.isen.banliat.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.banliat.androiderestaurant.model.CartData
import fr.isen.banliat.androiderestaurant.model.DishCart
import fr.isen.banliat.androiderestaurant.model.DishModel
import fr.isen.banliat.androiderestaurant.BasketActivity
import java.io.File

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dish = intent.getSerializableExtra("dish") as DishModel
        initDetail(dish)




        /*
        binding.totalButon.setOnClickListener{
            //Toast.makeText(applicationContext, "Plat ajouté au panier", Toast.LENGTH_SHORT).show()
            Snackbar.make(it,"Plat ajouté au panier", Snackbar.LENGTH_LONG).show()


            //val intent = Intent (this, CartActivity::class.java)
            //startActivity(intent)
        }

         */

    }


    private fun initDetail(dish: DishModel) {

        binding.detailTitle.text = dish.name_fr

        binding.dishPhotPager.adapter = DishPictureAdapter(this, dish.pictures)

        binding.dishDescription.text = dish.ingredients.joinToString(", ") { it.name_fr }


        var numberDish = 1
        binding.totalButon.text =
            "ajouter au panier : " + (dish.prices[0].price.toFloat() * numberDish) + "€"

        //add
        binding.addBtn.setOnClickListener {
            numberDish += 1
            binding.quantiteText.text = "" + numberDish
            binding.totalButon.text =
                "Total: " + (dish.prices[0].price.toFloat() * numberDish) + "€"
        }

        //less
        binding.removeBtn.setOnClickListener {
            if (numberDish > 0) {
                numberDish -= 1
                binding.quantiteText.text = "" + numberDish
                binding.totalButon.text =
                    "Total : " + (dish.prices[0].price.toFloat() * numberDish) + "€"
            }
        }

// ******************** panier **********
        var dishName = (intent.getSerializableExtra("dish") as DishModel)
        var data = ArrayList<CartData>()
        val buttonTot = binding.totalButon
        //click button
        binding.totalButon.setOnClickListener {
            val filename = "/panier.json"
            Snackbar.make(it, "Plat ajouté au panier", Snackbar.LENGTH_LONG).show()

            File(cacheDir.absolutePath + filename).bufferedWriter().use { file ->
                file.write(Gson().toJson(DishCart(intent.getSerializableExtra("dish") as DishModel, numberDish))) }

            val recup = File(cacheDir.absolutePath + filename).bufferedReader().readText()

            val resultat = Gson().fromJson(recup, DishCart::class.java)
            Log.d("panier", "$recup")

            var bool = false
            for (i in data.indices)
                if(resultat.dishName.name_fr==data[i].DishName){
                    data[i].quantity += resultat.quantity
                    bool = true
                } else {
                    data.add(CartData(resultat.dishName.name_fr, resultat.quantity))
                    bool = true }
                if (!bool) data.add(CartData(resultat.dishName.name_fr,resultat.quantity))
            Log.e("test", resultat.toString())

        }

    }


// ********* display cart icon on the menu **********
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.cart -> { val intent = Intent(this, BasketActivity::class.java)
                            startActivity(intent) }
            R.id.account -> { val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent) }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addToCart(dishes : List<DishModel>){

    }

}
