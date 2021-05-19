package it.uniupo.negozio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Rider_o_cliente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rider_o_cliente)

        val rider = findViewById<Button>(R.id.but1)
        rider.setOnClickListener {
            val intent_ride = Intent(this, Rider::class.java)
            startActivity(intent_ride)
        }

        val cliente = findViewById<Button>(R.id.but2)
        cliente.setOnClickListener {
            val intent_cli = Intent(this, Cliente::class.java)
            startActivity(intent_cli)
        }
    }
}