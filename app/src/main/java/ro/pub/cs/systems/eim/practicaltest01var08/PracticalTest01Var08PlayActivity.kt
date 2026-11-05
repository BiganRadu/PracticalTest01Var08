package ro.pub.cs.systems.eim.practicaltest01var08

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var08PlayActivity : AppCompatActivity() {
    lateinit var text1 : TextView
    lateinit var answer : EditText
    lateinit  var check : Button
    lateinit var back : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var08_play)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        text1 = findViewById(R.id.text1)
        answer = findViewById(R.id.edit1)
        check = findViewById(R.id.check)
        back = findViewById(R.id.back)

        val parentIntent = intent
        if (parentIntent.getStringExtra("riddle") != null) {
            text1.text = parentIntent.getStringExtra("riddle")
        }

        check.setOnClickListener {
            val answerParent = parentIntent.getStringExtra("answer")
            if (answerParent.equals(answer.text.toString())) {
                //setResult(RESULT_OK, Intent())
                Toast.makeText(this, "VICTORY", Toast.LENGTH_LONG).show()
            } else {
                //setResult(RESULT_CANCELED, Intent())
                Toast.makeText(this, "FAIL", Toast.LENGTH_LONG).show()
            }
            //finish()
        }

       /* back.setOnClickListener {
            setResult(RESULT_CANCELED, Intent())
            finish()
        }*/
    }
}