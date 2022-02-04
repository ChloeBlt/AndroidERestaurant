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

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createBtn.setOnClickListener {
            if(validationFistName() && validationLastName() && validationEmail() && validAdress() && validPassword()){

                Toast.makeText(this, "creation du compte", Toast.LENGTH_LONG).show()

                val firstname = binding.firstName.text.toString()
                val lastname = binding.lastName.text.toString()
                val address = binding.address.text.toString()
                val email = binding.email.text.toString()
                val password = binding.password.text.toString()


                val url = "http://test.api.catering.bluecodegames.com/user/register"
                val jsonObject = JSONObject()
                jsonObject.put("id_shop", "1")
                jsonObject.put("firstname", firstname)
                jsonObject.put("lastname", lastname)
                jsonObject.put("address", address)
                jsonObject.put("email", email)
                jsonObject.put("password", password)


                val jsonRequest = JsonObjectRequest(
                    Request.Method.POST, url, jsonObject, { response ->
                        var gson = Gson()

                    }, {
                        Log.e("", "erreur lors de la récupération")
                    })
                Volley.newRequestQueue(this).add(jsonRequest)
            }
        }



    }
    private fun validationFistName(): Boolean {
        if (binding.firstName.text.toString().trim().isEmpty()) {
            Toast.makeText(this,"veuillez saisir votre prénom",Toast.LENGTH_LONG).show()
            binding.firstName.requestFocus()
            return false
        }
        return true
    }

    private fun validationLastName(): Boolean {
        if (binding.lastName.text.toString().trim().isEmpty()) {
            Toast.makeText(this,"veuillez saisir votre nom",Toast.LENGTH_LONG).show()
            binding.lastName.requestFocus()
            return false
        }
        return true
    }

    private fun validationEmail(): Boolean {
        if (binding.email.text.toString().trim().isEmpty()) {
            Toast.makeText(this,"veuillez saisir un email",Toast.LENGTH_LONG).show()
            binding.email.requestFocus()
            return false
        }
        return true
    }

    private fun validAdress(): Boolean {
        if (binding.email.text.toString().trim().isEmpty()) {
            Toast.makeText(this,"veuillez saisir un email",Toast.LENGTH_LONG).show()
            binding.email.requestFocus()
            return false
        }
        return true
    }

    private fun validPassword(): Boolean {
        if (binding.password.text.toString().trim().isEmpty()) {
            Toast.makeText(this,"veuillez saisir un mot de passe",Toast.LENGTH_LONG).show()
            binding.password.requestFocus()
            return false
        } else if (binding.password.text.toString().length < 8) {
            Toast.makeText(this, "Le mot de passe est trop court", Toast.LENGTH_LONG).show()
            binding.password.requestFocus()
            return false
        }
        return true
    }


    }












