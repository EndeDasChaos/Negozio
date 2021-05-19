package it.uniupo.negozio

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class Aggiunta_prodotto : AppCompatActivity() {

    val TAG = "***db"
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aggiunta_prodotto)

        val nome_prodotto = findViewById<TextView>(R.id.nome)
        val prezzo = findViewById<TextView>(R.id.euro)
        val quantitativo = findViewById<TextView>(R.id.numero)
        val descrizione = findViewById<TextView>(R.id.descr)

        val indietro = findViewById<Button>(R.id.but2)
        indietro.setOnClickListener {
            val intent_dietro = Intent(this, Gestore::class.java)
            startActivity(intent_dietro)
        }

        val aggiunta = findViewById<Button>(R.id.but1)
        aggiunta.setOnClickListener {

                val user: MutableMap<String, Any> = HashMap()
                user["nome_prodotto"] = nome_prodotto.text.toString()
                user["prezzo"] = prezzo.text.toString()
                user["quantitativo"] = quantitativo.text.toString()
                user["descrizione"] = descrizione.text.toString()

                db.collection("prodotto")
                        .add(user)
                        .addOnSuccessListener { documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.id) }
                        .addOnFailureListener { e -> Log.w(TAG, "Error adding document", e) }

        }
    }
}