package it.uniupo.negozio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Rider : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rider)

        val si = findViewById<Button>(R.id.but1)
        val no = findViewById<Button>(R.id.but2)
        val visualizza = findViewById<Button>(R.id.but3)

        si.setOnClickListener {
            val intent_organizza_rider = Intent(this, Organizza_rider::class.java)
            startActivity(intent_organizza_rider)
        }
        no.setOnClickListener {
            val intent_log = Intent(this, Login::class.java)
            startActivity(intent_log)
        }
        visualizza.setOnClickListener {
            val intent_visua = Intent(this, Rider_consegne::class.java)
            startActivity(intent_visua)
        }
    }
}