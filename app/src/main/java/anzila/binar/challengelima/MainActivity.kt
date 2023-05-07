package anzila.binar.challengelima

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup
import android.widget.Button
import anzila.binar.challengelima.activity.HomeActivity
import anzila.binar.challengelima.activity.LoginActivity
import anzila.binar.challengelima.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val crashButton = Button(this)
        crashButton.text = "Test Crash"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(crashButton, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))

        Handler().postDelayed({
            if (getSharedPreferences("dataUser", Context.MODE_PRIVATE)!!.contains("userName")){
                startActivity((Intent(this, HomeActivity::class.java)))
            }else{
                startActivity(Intent(this, LoginActivity::class.java))
            }

        },3000)
    }
} 