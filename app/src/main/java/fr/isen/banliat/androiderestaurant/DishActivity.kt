package fr.isen.banliat.androiderestaurant


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.banliat.androiderestaurant.databinding.ActivityDishBinding
import fr.isen.banliat.androiderestaurant.model.DishModel
import fr.isen.banliat.androiderestaurant.model.DishResult
import org.json.JSONObject

//class DishActivity : AppCompatActivity() {
class DishActivity : MenuActivity() {

    private lateinit var binding: ActivityDishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryType = intent.getStringExtra("category_type") ?: ""

        binding.mainDishTitle.text = categoryType

        loadDishesFromCategory(categoryType)

    }


    override fun onResume() {
        invalidateOptionsMenu()
        super.onResume()
    }

    // get the data with a POST request + parse the response
    private fun loadDishesFromCategory(categoryType: String) {
        val url = "http://test.api.catering.bluecodegames.com/menu"

        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")

        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            { response ->
                val dishResult: DishResult =
                    Gson().fromJson(response.toString(), DishResult::class.java)
                displayDishes(dishResult.data.firstOrNull { category -> category.name_fr == categoryType }?.items
                    ?: listOf()
                )
            },
            {
                Log.e("DishActivity", "erreur lors de la récupération de la liste des plats")
            })
        jsonRequest.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            0,
            1f
        )
        Volley.newRequestQueue(this).add(jsonRequest)
    }

    // *************** RecyclerView ***************
    private fun displayDishes(dishes: List<DishModel>) {
        // this creates a vertical layout Manager
        binding.dishList.layoutManager = LinearLayoutManager(this)

        // Setting the Adapter with the recyclerview
        binding.dishList.adapter = DishAdapter(dishes) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("dish", it)
            startActivity(intent)
        }
    }
}