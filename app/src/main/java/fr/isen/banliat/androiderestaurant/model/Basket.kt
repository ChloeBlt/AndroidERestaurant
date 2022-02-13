package fr.isen.banliat.androiderestaurant.model

import java.io.Serializable
import android.content.Context
import com.google.gson.GsonBuilder
import java.io.File

class Basket(val items: MutableList<BasketItem>): Serializable {

    var itemsCount: Int = 0
        get() {
            var count = 0
            items.forEach { count += it.quantity }
            return count
        }

    fun addItem(item: DishModel, quantity: Int) {
        val existingItem = items.firstOrNull { it.dish.name_fr == item.name_fr}
        existingItem?.let {
            existingItem.quantity += quantity
        } ?: run {
            val basketItem = BasketItem(item, quantity)
            items.add(basketItem)
        }
    }

    fun removeItem(basketItem: BasketItem) {
        items.remove(basketItem)
    }

    fun save(context: Context) {
        val jsonFile = File(context.cacheDir.absolutePath + BASKET_FILE)
        val json = GsonBuilder().create().toJson(this)
        jsonFile.writeText(json)
        updateCounter(context)
    }

    private fun updateCounter(context: Context) {
        val sharedPreferences = context.getSharedPreferences(USER_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(ITEMS_COUNT, itemsCount)
        editor.apply()
    }

    companion object {
        fun getBasket(context: Context): Basket {
            //val count = Basket(mutableListOf()).itemsCount
            val jsonFile = File(context.cacheDir.absolutePath + BASKET_FILE)
            if (jsonFile.exists()){
                val json = jsonFile.readText()
                return GsonBuilder().create().fromJson(json, Basket::class.java)
            } else {
                return Basket(mutableListOf())
            }
        }
        const val BASKET_FILE = "basket.json"
        const val ITEMS_COUNT = "ITEMS_COUNT"
        const val USER_PREFERENCES_NAME = "USER_PREFERENCES_NAME"
    }

}

class BasketItem(val dish: DishModel, var quantity: Int): Serializable




/*
data class Basket (val items: MutableList<ItemBasket>) : Serializable
data class ItemBasket (var quantity: Int, var dish: DishModel )
data class Category (val name: String, val dishes: List<Dish>) : Serializable
*/
