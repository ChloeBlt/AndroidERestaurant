package fr.isen.banliat.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var button1 = findViewById(R.id.button2) as Button

        button1.setOnClickListener {
            // toast on button click event
            Toast.makeText(this, "vous avez choisiie les entrées.", Toast.LENGTH_LONG).show()
        }
    }

}