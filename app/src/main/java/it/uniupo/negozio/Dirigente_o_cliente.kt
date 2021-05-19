package it.uniupo.negozio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Dirigente_o_cliente : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dirigente_o_cliente)

        val gestore = findViewById<Button>(R.id.but1)
        gestore.setOnClickListener {
            val intent_gest = Intent(this, Gestore::class.java)
            startActivity(intent_gest)
        }

        val cliente = findViewById<Button>(R.id.but2)
        cliente.setOnClickListener {
            val intent_cli = Intent(this, Cliente::class.java)
            startActivity(intent_cli)
        }

    }
}