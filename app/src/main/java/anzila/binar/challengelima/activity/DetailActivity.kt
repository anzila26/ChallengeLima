package anzila.binar.challengelima.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import anzila.binar.challengelima.databinding.ActivityDetailBinding
import anzila.binar.challengelima.model.ResponseFilmItem
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailFilm = intent.getSerializableExtra("detailfilm") as ResponseFilmItem?

        binding.titleDet.text = detailFilm?.movieName
        binding.ratedDet.text = detailFilm?.movieRated
        binding.releaseDet.text = detailFilm?.release
        binding.synopDet.text = detailFilm?.synopsis
        Glide.with(this).load(detailFilm?.image).into(binding.imgDet)
    }
}