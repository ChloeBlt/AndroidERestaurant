package fr.isen.banliat.androiderestaurant

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import fr.isen.banliat.androiderestaurant.model.Basket

open class MenuActivity: AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)

        val menuView = menu?.findItem(R.id.shoppingCart)?.actionView
        val countText = menuView?.findViewById(R.id.nbItems) as? TextView
        val count = getSharedPreferences(Basket.USER_PREFERENCES_NAME, Context.MODE_PRIVATE).getInt(Basket.ITEMS_COUNT, 0)

        countText?.isVisible = count > 0
        countText?.text = count.toString()

        menuView?.setOnClickListener {
            if(count > 0) startActivity(Intent(this, BasketActivity::class.java))
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

    override fun onResume() {
        super.onResume()
        invalidateOptionsMenu()
    }

}