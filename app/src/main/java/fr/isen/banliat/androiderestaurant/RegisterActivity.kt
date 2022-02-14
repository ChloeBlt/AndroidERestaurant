package fr.isen.banliat.androiderestaurant

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.VolleyLog.TAG
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fr.isen.banliat.androiderestaurant.databinding.ActivityRegisterBinding
import fr.isen.banliat.androiderestaurant.model.Basket
import fr.isen.banliat.androiderestaurant.model.RegisterResponse
import fr.isen.banliat.androiderestaurant.model.RegisterResult
import fr.isen.banliat.androiderestaurant.model.User
import org.json.JSONObject


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.createBtn.setOnClickListener {
            register()

        }

        binding.loginTxt.setOnClickListener {
            val intent = Intent (this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun register() {
        if(validateData()) {
            launchRequest()
        }
    }

    private fun launchRequest() {
        val firstname = binding.firstName.text.toString()
        val lastname = binding.lastName.text.toString()
        val address = binding.address.text.toString()
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()

        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/user/register"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")
        jsonObject.put("firstname", firstname)
        jsonObject.put("lastname", lastname)
        jsonObject.put("address", address)
        jsonObject.put("email", email)
        jsonObject.put("password", password)


        val jsonRequest = JsonObjectRequest(Request.Method.POST,
            url,
            jsonObject,
            { response ->
                Gson().fromJson(response["data"].toString(), RegisterResponse::class.java).let {
                    val sharedPreferences =
                        getSharedPreferences(Basket.USER_PREFERENCES_NAME, MODE_PRIVATE)
                    sharedPreferences.edit().putInt(ID_USER, it.id).apply()
                    Log.i(TAG, "gson -> $it")
                    Toast.makeText(applicationContext, "Compte crée!", Toast.LENGTH_SHORT).show()
                    finish()
                    startActivity(Intent(this, BasketActivity::class.java))

                    /*
                val userResult =
                    GsonBuilder().create().fromJson(response.toString(), RegisterResult::class.java)
                saveUser(userResult.data)
                //var gson = Gson()
                */
                }
            },{ error ->
                    Log.d("request", String(error.networkResponse.data))
            }
        )
        queue.add(jsonRequest)
    }

    /*
    private fun saveUser(user: User) {
        val sharedPreferences = getSharedPreferences(USER_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(ID_USER, user.id)
        editor.apply()

        setResult(Activity.RESULT_OK)
        finish()
    }
     */

    private fun onFailure(error: VolleyError) {
        Log.d("request", String(error.networkResponse.data))
    }

    private fun validateData(): Boolean {
        return  validationLastName()  &&
                validationFistName() &&
                validAddress() &&
                validationEmail() &&
                validPassword()
    }

    private fun validationLastName(): Boolean {
        if (binding.lastName.text.toString().trim().isEmpty()) {
            Toast.makeText(this,"Nom invalide",Toast.LENGTH_LONG).show()
            binding.lastName.requestFocus()
            markAsInvalid(binding.lastName)
            return false
        }
        markAsValid(binding.lastName)
        return true
    }

    private fun validationFistName(): Boolean {
        if (binding.firstName.text.toString().trim().isEmpty()) {
            Toast.makeText(this,"Prénom invalide",Toast.LENGTH_LONG).show()
            binding.firstName.requestFocus()
            markAsInvalid(binding.firstName)
            return false
        }
        markAsValid(binding.firstName)
        return true
    }

    private fun validationEmail(): Boolean {
        if (binding.email.text.toString().trim().isEmpty()) {
            Toast.makeText(this,"e-mail invalide",Toast.LENGTH_LONG).show()
            binding.email.requestFocus()
            markAsInvalid(binding.email)
            return false
        }
        markAsValid(binding.email)
        return true
    }

    private fun validAddress(): Boolean {
        if (binding.address.text.toString().trim().isEmpty()) {
            Toast.makeText(this,"Adresse invalide",Toast.LENGTH_LONG).show()
            binding.email.requestFocus()
            markAsInvalid(binding.address)
            return false
        }
        markAsValid(binding.address)
        return true
    }

    private fun validPassword(): Boolean {
        if (binding.password.text.toString().trim().isEmpty()) {
            Toast.makeText(this,"veuillez saisir un mot de passe",Toast.LENGTH_LONG).show()
            binding.password.requestFocus()
            markAsInvalid(binding.password)
            return false
        } else if (binding.password.text.toString().length < 8) {
            Toast.makeText(this, "Le mot de passe est trop court", Toast.LENGTH_LONG).show()
            binding.password.requestFocus()
            markAsInvalid(binding.password)
            return false

        }
        markAsValid(binding.password)
        return true
    }

    private fun markAsInvalid(input: View){
        input.backgroundTintList = ContextCompat.getColorStateList(
            applicationContext,
            R.color.red)
    }
    private fun markAsValid(input: View){
        input.backgroundTintList = ContextCompat.getColorStateList(
            applicationContext,
            R.color.teal_700
        )
    }

    companion object {
        const val ID_USER = "ID_USER"
        const val USER_PREFERENCES_NAME = "USER_PREFERENCES_NAME"
    }
}













