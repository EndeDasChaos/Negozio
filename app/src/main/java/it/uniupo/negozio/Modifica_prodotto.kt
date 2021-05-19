package it.uniupo.negozio

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot


class Modifica_prodotto : AppCompatActivity() {

    val TAG = "***db"
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modifica_prodotto)

        val modifica = findViewById<Button>(R.id.but2)
        modifica.setOnClickListener {
            showAllData()
        }
    }

    fun showAllData() {

        val nome_prodotto = findViewById<TextView>(R.id.nome)
        val prezzo = findViewById<TextView>(R.id.euro)
        val quantitativo = findViewById<TextView>(R.id.numero)
        val descrizione = findViewById<TextView>(R.id.descr)

        db.collection("prodotto")
                .get()
                .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                    if (task.isSuccessful) {
                        for (document in task.result!!) {
                            Log.d(TAG, document.id + " => " + document.data)
                            if (document["descrizione"] != descrizione || document["nome_prodotto"] != nome_prodotto || document["prezzo"] != prezzo || document["quantitativo"] != quantitativo) {
                                val user: MutableMap<String, Any> = HashMap()
                                user["descrizione"] = findViewById<TextView>(R.id.descr)
                                user["nome_prodotto"] = findViewById<TextView>(R.id.nome)
                                user["prezzo"] = findViewById<TextView>(R.id.euro)
                                user["quantitativo"] = findViewById<TextView>(R.id.numero)
                                up(user,document.id)

                            } else {
                                Log.w(TAG, "Error getting documents.", task.exception)
                            }
                        }

                    }
                })
    }

    fun up(user: MutableMap<String, Any>, id: String) {
        db.collection("prodotto")
                .document(id)
                .update(user)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }

    }
}

