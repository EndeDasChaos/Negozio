package it.uniupo.negozio

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class Registrazione : AppCompatActivity() {
    val TAG = "***db"
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrazione)
        /*val qs = findViewById<EditText>(R.id.text_expl)*/

        val procedi = findViewById<Button>(R.id.but1)
        val Nome = findViewById<TextView>(R.id.nome)
        val Cognome = findViewById<TextView>(R.id.cognome)
        val email = findViewById<TextView>(R.id.e_mail)
        val Password = findViewById<TextView>(R.id.pass)
        val Tipo = findViewById<TextView>(R.id.tipo)

        val docRef = db.collection("utenti")
        /*
        docRef.addSnapshotListener { snapshots, e ->
            if (e != null) {
                Toast.makeText(this, "Cannot listen on firestore!!.", Toast.LENGTH_LONG).show()
                return@addSnapshotListener
            }
            for (dc in snapshots!!.documentChanges) {
                when (dc.type) {
                    DocumentChange.Type.ADDED -> readDB(qs) // Fatta in questo modo, consente al DB di auto aggiornarsi
                    DocumentChange.Type.MODIFIED -> Log.d("quoteListener",
                            "Modified quote: ${dc.document.data}")
                    DocumentChange.Type.REMOVED -> Log.d("quoteListener",
                            "Removed quote: ${dc.document.data}")
                }
            }
        }*/
        procedi.setOnClickListener {
            if(Password.length() > 0 && Nome.length() > 0 && Cognome.length() > 0 && email.length() > 0 && Tipo.length() > 0){
                val user: MutableMap<String, Any> = HashMap()
                user["nome"] = Nome.text.toString()
                user["cognome"] = Cognome.text.toString()
                user["pass"] = Password.text.toString()
                user["email"] = email.text.toString()
                user["tipo"] = Tipo.text.toString()

                db.collection("utenti")
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
        val indietro = findViewById<Button>(R.id.but2)
        indietro.setOnClickListener {
            val intent_log = Intent(this, Login::class.java)
            startActivity(intent_log)
        }

    }
    private fun readDB(qs: TextView) {
        db.collection("utenti")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    qs.text = ""
                    for (document in task.result!!) {
                        qs.text = "${qs.text} ${document["cognome"]}: ${document["nome"]}: ${document["password"]}: ${document["e-mail"]}: ${document["tipo"]}\n"
                    }
                } else {
                    Log.d(TAG, "Error getting documents.", task.exception)
                }
            }
    }


}