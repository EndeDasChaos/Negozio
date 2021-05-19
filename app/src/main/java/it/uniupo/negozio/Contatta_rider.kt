package it.uniupo.negozio

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class Contatta_rider : AppCompatActivity() {

    val data = FirebaseFirestore.getInstance()
    val TAG = "***data"
    var db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contatta_rider)

        val pro = findViewById<TextView>(R.id.informazione)
        val visualizza = findViewById<Button>(R.id.but1)
        val aggiugni = findViewById<Button>(R.id.but2)
        val Nome = findViewById<TextView>(R.id.nome)
        val Cognome = findViewById<TextView>(R.id.cognome)
        val Prodotto = findViewById<TextView>(R.id.prod)
        val Luogo = findViewById<TextView>(R.id.luogo)

        visualizza.setOnClickListener {
            read(pro)
        }

        aggiugni.setOnClickListener {
            if(Prodotto.length() > 0 && Nome.length() > 0 && Cognome.length() > 0 && Luogo.length()>0 ){
                val user: MutableMap<String, Any> = HashMap()
                user["nome"] = Nome.text.toString()
                user["cognome"] = Cognome.text.toString()
                user["prod"] = Prodotto.text.toString()
                user["luogo"] = Luogo.text.toString()

                db.collection("rider_contattati")
                        .add(user)
                        .addOnSuccessListener { documentReference -> Log.d(
                                TAG,
                                "DocumentSnapshot added with ID: " + documentReference.id
                        ) }
                        .addOnFailureListener { e -> Log.w(TAG, "Error adding document", e)
                            /*val intent_log = Intent(this, MainActivity::class.java)
                            startActivity(intent_log)*/
                        }
            }
            else
            {
                Toast.makeText(this, "errore nell'inserimento dei dati", Toast.LENGTH_LONG).show()
            }
        }

        val indietro = findViewById<Button>(R.id.but3)
        indietro.setOnClickListener {
            val intent_log = Intent(this, Gestore::class.java)
            startActivity(intent_log)
        }

    }
    private fun read(pro: TextView){
        data.collection("rider_disponibile")
            .get()
            .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                if (task.isSuccessful) {
                    pro.text = ""
                    for (document in task.result!!) {
                        pro.text = "${pro.text} ${document["nome"]} ${document["cognome"]} ${document["messaggio"]}\n"
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            })
    }
}