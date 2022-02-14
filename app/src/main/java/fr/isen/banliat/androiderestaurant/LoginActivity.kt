package fr.isen.banliat.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.VolleyLog.TAG
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.banliat.androiderestaurant.databinding.ActivityLoginBinding
import fr.isen.banliat.androiderestaurant.model.Basket
import fr.isen.banliat.androiderestaurant.model.RegisterResponse
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
                login()
            }
        }
        binding.registerTxt.setOnClickListener {
            val intent = Intent (this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login() {
        val email = binding.edMail.text.toString()
        val password = binding.edPassword.text.toString()

        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/user/login"

        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")
        jsonObject.put("email", email)
        jsonObject.put("password", password)

        val jsonRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            { response ->
                Gson().fromJson(response["data"].toString(), RegisterResponse::class.java).let {

                    val sharedPreferences = getSharedPreferences(Basket.USER_PREFERENCES_NAME, MODE_PRIVATE)
                    sharedPreferences.edit().putInt(RegisterActivity.ID_USER, it.id).apply()
                    Log.i(TAG, "gson -> $it")
                    Toast.makeText(applicationContext, "vous êtes connecté", Toast.LENGTH_SHORT).show()
                    finish()
                    startActivity(Intent(this, BasketActivity::class.java))
                }
            }, {
                Log.e("LoginActivity", "erreur")
            })
        queue.add(jsonRequest)
        Toast.makeText(this, "connection...", Toast.LENGTH_SHORT).show()
    }
}