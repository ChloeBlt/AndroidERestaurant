package fr.isen.banliat.androiderestaurant

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible

open class MenuActivity: AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        val menuView = menu?.findItem(R.id.shoppingCart)?.actionView
        val count = menuView?.findViewById<TextView>(R.id.nbItems)
        val sharedPrefrences = getSharedPreferences("app_prefs", MODE_PRIVATE)
        val quantity = sharedPrefrences.getInt("basket_count", 0)

        if (quantity == 0) {
            count?.isVisible = false
        } else {
            count?.text = quantity.toString()
            count?.isVisible = true
        }


        menuView?.setOnClickListener{
            if(quantity > 0) startActivity(Intent(this, BasketActivity::class.java))
            else {
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setMessage("Votre panier est vide...")
                    .setCancelable(false)
                    .setPositiveButton("Ok", DialogInterface.OnClickListener {
                            dialog, _ -> dialog.cancel()
                    })
                val alert = dialogBuilder.create()
                alert.setTitle("Oups !")
                alert.show()
            }
        }

        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){ R.id.account -> { val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent) }
        }
        return super.onOptionsItemSelected(item)
    }


}