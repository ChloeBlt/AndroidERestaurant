package fr.isen.banliat.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.widget.TextView

open class MenuActivity: AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        val menuView = menu?.findItem(R.id.shoppingCart)?.actionView
        val count = menuView?.findViewById<TextView>(R.id.nbItems)
        val sharedPrefrences = getSharedPreferences("app_prefs", MODE_PRIVATE)
        count?.text = sharedPrefrences.getInt("basket_count", 0).toString()

        menuView?.setOnClickListener{
            startActivity(Intent(this, BasketActivity::class.java))
        }

        return super.onCreateOptionsMenu(menu)
    }
}