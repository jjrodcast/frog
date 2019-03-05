package pe.com.jjrodcast.frogsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_target.*

class TargetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target)

        val extras = intent.extras

        extras?.let {
            val fromNotification = it.getBoolean("fromNotification", false)
            val name = it.getString("name", "")
            val version = it.getInt("version", 0)

            text.text = "Name:$name\nVersion:$version\nFrom Notification: $fromNotification"
        }
    }
}
