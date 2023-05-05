package anzila.binar.challengelima.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import anzila.binar.challengelima.R
import anzila.binar.challengelima.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    lateinit var binding : ActivityProfileBinding
    lateinit var sharedPref : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUpdate.setOnClickListener {
            sharedPref = getSharedPreferences("dataUser", Context.MODE_PRIVATE)
            val dataLama = sharedPref.getString("update", "")
            val dataBaru = "new update"

            val prof = sharedPref.edit()
            prof.putString("update", dataBaru)
            prof.apply()
        }

        binding.btnLogout.setOnClickListener {
            sharedPref = getSharedPreferences("dataUser", Context.MODE_PRIVATE)
            val delete = sharedPref?.edit()
            delete?.clear()
            delete?.apply()
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }
}