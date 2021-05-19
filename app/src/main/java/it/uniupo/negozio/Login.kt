package it.uniupo.negozio

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import android.view.View;
import com.google.firebase.auth.FirebaseAuth


class Login() : AppCompatActivity() {
    val TAG = "~ Login"
    var db: FirebaseFirestore? = FirebaseFirestore.getInstance()

    private lateinit var autenticazione: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        autenticazione = FirebaseAuth.getInstance()

        val log_in = findViewById<Button>(R.id.but2)

        log_in.setOnClickListener {
            val e_mail = findViewById<EditText>(R.id.id)
            val password = findViewById<EditText>(R.id.pass)

            if (password.length() > 0 && e_mail.length() > 0) {
                autenticazione.signInWithEmailAndPassword(e_mail.toString(), password.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            tipo_utente()

                        } else {
                            Toast.makeText(this, "errore nell'inserimento dei dati", Toast.LENGTH_LONG).show()
                            password.text = null
                        }
                    }
            }
        }

        val register = findViewById<Button>(R.id.but1)
        register.setOnClickListener {
            val intent = Intent(this, Registrazione::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }


    override fun onStart() {
        super.onStart()
        val ut = autenticazione.currentUser
        tipo_utente()
    }/*
        override fun onSaveInstanceState(outState: Bundle) {
            val identificativo = findViewById<EditText>(R.id.id)
            val password = findViewById<EditText>(R.id.pass)
        if(identificativo != null && password != null) {
            outState.putString("e-mail", identificativo.text.toString())
            outState.putString("Password", password.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

        override fun onRestoreInstanceState(savedInstanceState: Bundle) {
            val identificativo = findViewById<EditText>(R.id.id)
            val password = findViewById<EditText>(R.id.pass)
        identificativo.setText(savedInstanceState.getString("e-mail"))
        password.setText(savedInstanceState.getString("Password"))
    }*/
    private fun tipo_utente() {
        db!!.collection("utenti")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        Log.d(TAG, document.id + " => " + document.data)
                        if (document["tipo"] == "dirigente") {
                            val intent_dirigente = Intent(this, Dirigente_o_cliente::class.java)
                            startActivity(intent_dirigente)
                        } else
                            if (document["tipo"] == "cliente") {
                                val intent_cliente = Intent(this, Cliente::class.java)
                                startActivity(intent_cliente)
                            } else
                                if (document["tipo"] == "rider") {
                                val intent_rider = Intent(this, Rider_o_cliente::class.java)
                                startActivity(intent_rider)
                            }

                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            }
    }
}

