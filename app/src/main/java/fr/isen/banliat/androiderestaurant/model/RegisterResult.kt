package fr.isen.banliat.androiderestaurant.model

import java.io.Serializable

class RegisterResult(val data: User) {}

class User(val id: Int): Serializable {}