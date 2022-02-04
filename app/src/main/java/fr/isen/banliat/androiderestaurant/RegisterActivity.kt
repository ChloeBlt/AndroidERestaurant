package fr.isen.banliat.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONObject
import fr.isen.banliat.androiderestaurant.databinding.ActivityRegisterBinding



class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lastName = binding.lastName.text.toString()
        val firstName = binding.firstName.text.toString()
        val address = binding.address.text.toString()
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()


        binding.createBtn.setOnClickListener {
            if (lastName.isEmpty()) {
                Toast.makeText(this, "Entrer un nom", Toast.LENGTH_LONG).show()
            } else if (firstName.isEmpty()) {
                Toast.makeText(this, "Entrer un prénom", Toast.LENGTH_LONG).show()
            } else if (address.isEmpty()) {
                Toast.makeText(this, "Entrer une adress", Toast.LENGTH_LONG).show()
            } else if (email.isEmpty()) {
                Toast.makeText(this, "Entrer une adresse mail", Toast.LENGTH_LONG).show()
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Entrer un mot de passe", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Compte créer", Toast.LENGTH_LONG).show()
            }
        }



        /*
        //request POST

        val url = "http://test.api.catering.bluecodegames.com/user/register"
        val jsonObject = JSONObject()
            jsonObject.put("id_shop", 1)
            jsonObject.put("firstname", firstName)
            jsonObject.put("lastname", lastName)
            jsonObject.put("address", address)
            jsonObject.put("email", email)
            jsonObject.put("password", password)

            val jsonRequest = JsonObjectRequest(
                Request.Method.POST, url, jsonObject, { response ->
                    var gson = Gson()
                    Log.d("", "message")
                }, {
                    Log.e("", "erreur lors de la récupération de la liste des plats")
                })
            Volley.newRequestQueue(this).add(jsonRequest)

         */
        }


    }


