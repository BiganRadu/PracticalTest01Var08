package ro.pub.cs.systems.eim.practicaltest01var08

import android.R.attr.data
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class StartedServiceBroadcastReceiver() : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {
        val hint = intent.getStringExtra("HINT")
        Toast.makeText(context, "The hint is $hint", Toast.LENGTH_LONG).show()
    }
}
class PracticalTest01Var08PlayActivity : AppCompatActivity() {
    lateinit var text1 : TextView
    lateinit var answer : EditText
    lateinit  var check : Button
    lateinit var back : Button

    private lateinit var startedServiceBroadcastReceiver: StartedServiceBroadcastReceiver
    private lateinit var startedServiceIntentFilter: IntentFilter

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

        val serviceIntent = Intent(this, PracticalTest01Var08Service::class.java)
        serviceIntent.putExtra("ANSWER", answer.text.toString())
        startService(serviceIntent)

        startedServiceBroadcastReceiver = StartedServiceBroadcastReceiver()
        startedServiceIntentFilter = IntentFilter().apply {
            addAction("ro.pub.cs.systems.eim.action_message")
        }

        check.setOnClickListener {
            val answerParent = parentIntent.getStringExtra("answer")
            if (answerParent.equals(answer.text.toString())) {
                setResult(RESULT_OK, Intent())
                Toast.makeText(this, "VICTORY", Toast.LENGTH_LONG).show()
            } else {
                setResult(RESULT_CANCELED, Intent())
                Toast.makeText(this, "FAIL", Toast.LENGTH_LONG).show()
            }
            finish()
        }

        back.setOnClickListener {
            setResult(RESULT_CANCELED, Intent())
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        intent = Intent(this, PracticalTest01Var08Service::class.java)
        stopService(intent)
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(startedServiceBroadcastReceiver, startedServiceIntentFilter, Context.RECEIVER_EXPORTED)
        } else {
            @Suppress("DEPRECATION")
            registerReceiver(startedServiceBroadcastReceiver, startedServiceIntentFilter)
        }
    }

    override fun onPause() {
        unregisterReceiver(startedServiceBroadcastReceiver)

        super.onPause()
    }

}