package anzila.binar.challengelima.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import anzila.binar.challengelima.R
import anzila.binar.challengelima.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}