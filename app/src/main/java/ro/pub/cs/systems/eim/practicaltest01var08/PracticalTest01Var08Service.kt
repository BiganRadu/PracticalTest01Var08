package ro.pub.cs.systems.eim.practicaltest01var08

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log


class ProcessingThread(private val context: Context, private val answer : String) : Thread() {
    private var isRunning = true

    override fun run() {
        while (isRunning) {

            var stars = ""
            for (i in 0..<answer.length) {
                if (i == 0) {
                    stars = "${answer.get(0)}"
                } else {
                    stars = "$stars*"
                }
            }
            Log.d("AplicatiaMea", stars)
            val intent = Intent()
            intent.action = "ro.pub.cs.systems.eim.action_message"
            intent.putExtra("HINT", stars)
            context.sendBroadcast(intent)

            try {
                sleep(5000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    fun stopThread() {
        isRunning = false
    }
}
class PracticalTest01Var08Service : Service() {

    private var processingThread: ProcessingThread? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val answer = intent?.getStringExtra("ANSWER")

        processingThread = ProcessingThread(this, answer?: "")
        processingThread?.start()

        return START_REDELIVER_INTENT
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        processingThread?.stopThread()
        super.onDestroy()
    }
}