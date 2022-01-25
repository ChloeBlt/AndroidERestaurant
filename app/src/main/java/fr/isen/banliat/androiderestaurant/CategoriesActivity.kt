package fr.isen.banliat.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.banliat.androiderestaurant.databinding.ActivityCategoriesBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainDishTitle.text = intent.getStringExtra("category_type")


        //setContentView(R.layout.activity_home)

    //RecyclerView

        // getting the recyclerview by its id
        //val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        //recyclerview.layoutManager = LinearLayoutManager(this)
        binding.mainDishRecylcerView.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemsViewModel("Item" + i))
        }
        data.add(ItemsViewModel("miam"))
        data.add(ItemsViewModel("nomnom"))


        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        binding.mainDishRecylcerView.adapter = adapter

    }
}