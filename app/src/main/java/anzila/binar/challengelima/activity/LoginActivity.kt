package anzila.binar.challengelima.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import anzila.binar.challengelima.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    lateinit var sharedPref : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val uname = binding.etUnameLog.text.toString()
            val pass = binding.etPassLog.text.toString()
            val dataUname = sharedPref.getString("username", "")
            val dataPass = sharedPref.getString("password", "")
            if(uname == dataUname && pass == dataPass) {
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                Toast.makeText(this, "Login Tidak Berhasil", Toast.LENGTH_SHORT).show()
            }
        }

        binding.txtNoReg.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        sharedPref = getSharedPreferences("dataUser", Context.MODE_PRIVATE)
    }
}