package anzila.binar.challengelima.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import anzila.binar.challengelima.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    lateinit var binding : ActivityRegisterBinding
    lateinit var sharedPref : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnReg.setOnClickListener {
            val nama = binding.etNameReg.text.toString()
            val uname = binding.etUnameReg.text.toString()
            val pass = binding.etPassReg.text.toString()
            val rePass = binding.etRepass.text.toString()

            if (pass == rePass) {
                val rPref = sharedPref.edit()
                rPref.putString("nama", nama)
                rPref.putString("username", uname)
                rPref.putString("password", pass)
                rPref.apply()
                startActivity(Intent(this, LoginActivity::class.java))
                Toast.makeText(this,"Berhasil Regist", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Password Harus Sama", Toast.LENGTH_SHORT).show()
            }
        }

        sharedPref = getSharedPreferences("dataUser", Context.MODE_PRIVATE)
    }
}