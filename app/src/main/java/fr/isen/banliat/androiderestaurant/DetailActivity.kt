package fr.isen.banliat.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.gson.GsonBuilder
import fr.isen.banliat.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.banliat.androiderestaurant.model.*
import java.io.File

private lateinit var binding: ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dish = intent.getSerializableExtra("dish") as DishModel
        initDetail(dish)
        var quantity = 1

        // Add dish or remove it
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
            if(dish != null)
                saveInBasket(it, quantity, dish)
        }
    }

    private fun initDetail(dish: DishModel) {
        binding.detailTitle.text = dish.name_fr
        binding.dishPhotPager.adapter = DishPictureAdapter(this, dish.pictures)
        binding.dishDescription.text = dish.ingredients.joinToString(", ") { it.name_fr }
        binding.totalButon.text =  "ajouter au panier : " + (dish.prices[0].price.toFloat()) + "€"
    }

// ********* display cart icon on the menu **********
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)
        return true }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.shoppingCart -> { val intent = Intent(this, BasketActivity::class.java)
                            startActivity(intent) }
            R.id.account -> { val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent) }
        }
        return super.onOptionsItemSelected(item)
    }

// **************** add to cart ****************
    private fun saveInBasket(view: View, quantity: Int, dish: DishModel){
        val file = File(cacheDir.absolutePath + "UserCart")

        if (file.exists()){
            val basket = GsonBuilder().create().fromJson(file.readText(), Basket::class.java)

            basket.items.firstOrNull { it.dish == dish }?.let {
                it.quantity += quantity
            } ?: run {
                basket.items.add(ItemBasket(quantity, dish))
            }
            saveInMemory(basket, file)
        } else {
            val basket = Basket(mutableListOf(ItemBasket(quantity, dish)))
            saveInMemory(basket, file)
        }
        Snackbar.make(view, "ajouté au panier", Snackbar.LENGTH_LONG).show()
    }

    private fun saveInMemory(basket: Basket, file: File) {
        val count = basket.items.sumOf { it.quantity }

        val sharedPreferences = getSharedPreferences( "app_prefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("basket_count", count).apply()

        file.writeText(GsonBuilder().create().toJson(basket))
    }

}




    /*
     // ******************** panier **********
     var dishName = (intent.getSerializableExtra("dish") as DishModel)
     var data = ArrayList<CartData>()
     val buttonTot = binding.totalButon
     //click button
     binding.totalButon.setOnClickListener {
         val filename = "/panier.json"
         Snackbar.make(it, "Plat ajouté au panier", Snackbar.LENGTH_LONG).show()

         File(cacheDir.absolutePath + filename).bufferedWriter().use { file ->
             file.write(Gson().toJson(DishCart(intent.getSerializableExtra("dish") as DishModel, quantity))) }

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
      */


