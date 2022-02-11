package fr.isen.banliat.androiderestaurant.model

import java.io.Serializable

data class DishCart(val dishName: DishModel,
                      val quantity: Int): Serializable

data class CartData(val DishName: String,
                      var quantity : Int): Serializable

