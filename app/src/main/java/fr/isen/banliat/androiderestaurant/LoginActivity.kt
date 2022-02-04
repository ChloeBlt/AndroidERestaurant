package fr.isen.banliat.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.isen.banliat.androiderestaurant.databinding.ActivityHomeBinding

import fr.isen.banliat.androiderestaurant.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

            if(binding.edUsername.text.trim().isNotEmpty() || binding.edPassword.text.trim().isNotEmpty()){
                Toast.makeText(this, "Input provided", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Input required", Toast.LENGTH_SHORT).show()

            }

        }

    }
}