package fr.isen.banliat.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.isen.banliat.androiderestaurant.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

            if (binding.edMail.text.trim().isNotEmpty() || binding.edPassword.text.trim().isNotEmpty()) {
                Toast.makeText(this, "entrer les informations nécessaires", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "champ vide", Toast.LENGTH_SHORT).show()
            }
        }
        binding.registerTxt.setOnClickListener {
            val intent = Intent (this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}