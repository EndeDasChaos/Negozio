package it.uniupo.negozio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Gestore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_gestore)

        val aggiungi_prodotto = findViewById<Button>(R.id.but1)
        aggiungi_prodotto.setOnClickListener {
            val intent_aggiunta = Intent(this, Aggiunta_prodotto::class.java)
            startActivity(intent_aggiunta)
        }

        val modifica_prodotto = findViewById<Button>(R.id.but2)
        modifica_prodotto.setOnClickListener {
            val intent_modifica = Intent(this, Modifica_prodotto::class.java)
            startActivity(intent_modifica)
        }

        val contatta_rider = findViewById<Button>(R.id.but3)
        contatta_rider.setOnClickListener {
            val intent_contatta = Intent(this, Contatta_rider::class.java)
            startActivity(intent_contatta)
        }

        val visualizza_storico = findViewById<Button>(R.id.but4)
        visualizza_storico.setOnClickListener {
            val intent_storico = Intent(this, Visualizza_storico::class.java)
            startActivity(intent_storico)
        }

        val indietro = findViewById<Button>(R.id.but5)
        indietro.setOnClickListener {
            val intent_scelta = Intent(this, Dirigente_o_cliente::class.java)
            startActivity(intent_scelta)
        }

        val aggiungi_locale = findViewById<Button>(R.id.but6)
        aggiungi_locale.setOnClickListener {
            val intent_aggiunta_locale = Intent(this, Aggiunta_locale::class.java)
            startActivity(intent_aggiunta_locale)
        }
    }
}