package fr.isen.banliat.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.banliat.androiderestaurant.databinding.ActivityLoginBinding
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

            if (binding.edMail.text.trim().isEmpty() || binding.edPassword.text.trim().isEmpty()) {
                Toast.makeText(this, "entrer les informations nécessaires", Toast.LENGTH_SHORT).show()
            } else {
                val email = binding.edMail.text.toString()
                val password = binding.edPassword.text.toString()

                //Requete POST
                val url = "http://test.api.catering.bluecodegames.com/user/login"
                val jsonObject = JSONObject()
                jsonObject.put("id_shop", "1")
                jsonObject.put("email", email)
                jsonObject.put("password", password)

                val jsonRequest = JsonObjectRequest(
                    Request.Method.POST, url, jsonObject, { response ->
                        var gson = Gson()
                    }, {
                        Log.e("", "erreur lors de la récupération")
                    })
                Volley.newRequestQueue(this).add(jsonRequest)
                Toast.makeText(this, "connection...", Toast.LENGTH_SHORT).show()
            }
        }
        binding.registerTxt.setOnClickListener {
            val intent = Intent (this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}