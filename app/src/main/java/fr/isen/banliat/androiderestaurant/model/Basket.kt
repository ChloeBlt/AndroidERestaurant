package fr.isen.banliat.androiderestaurant.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Basket (@SerializedName("items") val items: MutableList<ItemBasket>) : Serializable

data class ItemBasket (
                        @SerializedName("quantity") var quantity: Int,
                       @SerializedName("dish") var dish: DishModel  )


//data class Category (@SerializedName("name_fr") val name: String, @SerializedName("items") val dishes: List<Dish>) : Serializable

//class Basket (val items: List<BasketItem>): Serializable {}

//class BasketItem(val dish: Dish, val count: Int): Serializable {}