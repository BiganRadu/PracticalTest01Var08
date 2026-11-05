package ro.pub.cs.systems.eim.practicaltest01var08

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
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
            Toast.makeText(this, "OK", Toast.LENGTH_LONG)
        }
    }
}