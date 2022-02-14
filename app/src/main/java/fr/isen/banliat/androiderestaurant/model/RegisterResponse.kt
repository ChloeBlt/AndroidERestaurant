package fr.isen.banliat.androiderestaurant.model

import java.io.Serializable

data class RegisterResponse(
    val id: Int,
    val code: String,
    val id_shop: Int
) : Serializable