package it.uniupo.negozio

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class Organizza_rider : AppCompatActivity() {

    val TAG = "***db"
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_organizza_rider)

        val Nome = findViewById<TextView>(R.id.nome)
        val Cognome = findViewById<TextView>(R.id.cognome)
        val Messaggio = findViewById<TextView>(R.id.info)

        val prosegui = findViewById<Button>(R.id.but1)

        prosegui.setOnClickListener {
            if(Nome.length() > 0 && Nome.length() > 0 && Cognome.length() > 0){
                val user: MutableMap<String, Any> = HashMap()
                user["nome"] = Nome.text.toString()
                user["cognome"] = Cognome.text.toString()
                user["messaggio"] = Messaggio.text.toString()


                db.collection("rider_disponibile")
                    .add(user)
                    .addOnSuccessListener { documentReference -> Log.d(
                        TAG,
                        "DocumentSnapshot added with ID: " + documentReference.id
                    ) }
                    .addOnFailureListener { e -> Log.w(TAG, "Error adding document", e)

                    }
            }
            else
            {
                Toast.makeText(this, "errore nell'inserimento dei dati", Toast.LENGTH_LONG).show()
            }
        }
        val indietro = findViewById<Button>(R.id.but2)
        indietro.setOnClickListener {
            val intent_log = Intent(this, Login::class.java)
            startActivity(intent_log)
        }
    }
}