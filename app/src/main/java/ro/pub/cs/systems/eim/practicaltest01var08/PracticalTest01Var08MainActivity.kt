package ro.pub.cs.systems.eim.practicaltest01var08

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var victory = 0
    var fail = 0
    lateinit var play : Button
    lateinit var riddle: EditText
    lateinit var answer: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.acitivity_practical_test01_var08_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        play = findViewById(R.id.btn1)
        riddle = findViewById(R.id.riddle)
        answer = findViewById(R.id.answer)

        play.setOnClickListener {
            if (!riddle.text.isEmpty() && !answer.text.isEmpty()) {
                var intent = Intent(this, PracticalTest01Var08PlayActivity::class.java)
                intent.putExtra("riddle", riddle.text.toString())
                intent.putExtra("answer", answer.text.toString())
                startActivityForResult(intent, 1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (resultCode == RESULT_OK) {
            Toast.makeText(this, "The activity returned with result VICTORY", Toast.LENGTH_LONG).show()
            victory++
        } else {
            Toast.makeText(this, "The activity returned with result FAIL", Toast.LENGTH_LONG).show()
            fail++
        }

        Log.d("AplicatiaMea", "Scor : $victory victorii si $fail failuri")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("VICTORY", victory)
        outState.putInt("FAIL", fail)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.containsKey("VICTORY")) {
            victory = savedInstanceState.getInt("VICTORY")
        }
        if (savedInstanceState.containsKey("FAIL")) {
            fail = savedInstanceState.getInt("FAIL")
        }
    }
}