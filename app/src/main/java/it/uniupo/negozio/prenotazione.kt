package it.uniupo.negozio

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot


class prenotazione : AppCompatActivity() {
    val data = FirebaseFirestore.getInstance()
    val TAG = "***data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prenotazione)

        val pro = findViewById<TextView>(R.id.informazione)
        val visualizza = findViewById<Button>(R.id.but1)

        visualizza.setOnClickListener {
            read(pro)
        }

    }
    private fun read(pro: TextView){
        data.collection("prodotto")
                .get()
                .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                    if (task.isSuccessful) {
                        pro.text = ""
                        for (document in task.result!!) {
                            pro.text = "${pro.text} ${document["nome_prodotto"]} ${document["descrizione"]} ${document["prezzo"]}\n"
                        }
                    } else {
                        Log.w(TAG, "Error getting documents.", task.exception)
                    }
                })
    }
}