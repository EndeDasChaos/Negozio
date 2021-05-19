package it.uniupo.negozio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class Aggiunta_locale : AppCompatActivity() {

    val TAG = "***db"
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aggiunta_locale)

        val nome_locale = findViewById<TextView>(R.id.nome)
        val città = findViewById<TextView>(R.id.città)
        val paese = findViewById<TextView>(R.id.paese)
        val via = findViewById<TextView>(R.id.via)

        val indietro = findViewById<Button>(R.id.but2)
        indietro.setOnClickListener {
            val intent_dietro = Intent(this, Gestore::class.java)
            startActivity(intent_dietro)
        }

        val aggiungi = findViewById<Button>(R.id.but1)
        aggiungi.setOnClickListener {
            if (nome_locale.length() > 0 && paese.length() > 0 && città.length() > 0 && via.length() > 0) {
                // Create a new user with a first and last name
                // Create a new user with a first and last name
                val entry: MutableMap<String, Any> = HashMap()
                entry["nome_locale"] = nome_locale.text.toString()
                entry["città"] = città.text.toString()
                entry["paese"] = paese.text.toString()
                entry["via"] = via.text.toString()

                db.collection("locale")
                    .add(entry)
                    .addOnSuccessListener { documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.id) }
                    .addOnFailureListener { e -> Log.w(TAG, "Error adding document", e) }
            }
            else
            {
                Toast.makeText(this, "errore nell'inserimento dei dati", Toast.LENGTH_LONG).show()
            }

        }
    }
}