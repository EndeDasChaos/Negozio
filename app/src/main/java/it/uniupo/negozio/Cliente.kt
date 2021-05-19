package it.uniupo.negozio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class Cliente : AppCompatActivity() {
    val TAG = "***db"

    var db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente)

        val indirizzo = findViewById<TextView>(R.id.ind)
        val prosegui = findViewById<Button>(R.id.but1)

        prosegui.setOnClickListener {
            if (indirizzo.length() > 0) {

                val user: MutableMap<String, Any> = HashMap()
                user["indirizzo"] = indirizzo.text.toString()

                db.collection("indirizzo")
                        .add(user)
                        .addOnSuccessListener(OnSuccessListener<DocumentReference> { documentReference ->
                            Log.d(
                                    TAG,
                                    "DocumentSnapshot added with ID: " + documentReference.id
                            )
                        })
                        .addOnFailureListener(OnFailureListener { e ->
                            Log.w(
                                    TAG,
                                    "Error adding document",
                                    e
                            )
                        })
                val intent_prenotazione = Intent(this, prenotazione::class.java)
                startActivity(intent_prenotazione)
            } else {
                Toast.makeText(this, "errore nell'inserimento dei dati", Toast.LENGTH_LONG).show()
            }
        }
    }
}