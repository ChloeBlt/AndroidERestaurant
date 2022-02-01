package fr.isen.banliat.androiderestaurant


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.banliat.androiderestaurant.databinding.ActivityDishBinding
import fr.isen.banliat.androiderestaurant.model.DishModel
import fr.isen.banliat.androiderestaurant.model.DishResult
import org.json.JSONObject

class DishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDishBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val categoryType = intent.getStringExtra("category_type") ?: ""
        binding.mainDishTitle.text = categoryType

        loadDishesFromCategory(categoryType)

    }

    private fun loadDishesFromCategory(categoryType: String){
        val url = "http://test.api.catering.bluecodegames.com/menu"

        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")
        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonObject, { response ->
                val dishResult: DishResult = Gson().fromJson(response.toString(), DishResult::class.java)
                displayDishes(dishResult.data.firstOrNull{ category -> category.name_fr == categoryType }?.items ?: listOf())
            }, {
                Log.e("DishActivity", "erreur lors de la récupération de la liste des plats")
            })
        Volley.newRequestQueue(this).add(jsonRequest)
    }

// *************** RecyclerView ***************
    private fun displayDishes(dishes : List<DishModel>) {
        binding.dishList.layoutManager = LinearLayoutManager(this)

        /*
        val dishes = ArrayList<Dish>()
        for (i in 1..20) { dishes.add(Dish("Item" + i, R.draw able.totonocuisine, "description")) }
         */

        // Setting the Adapter with the recyclerview
        binding.dishList.adapter = DishAdapter(dishes) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("dish", it)
            startActivity(intent)
        }
    }
}