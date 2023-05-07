package anzila.binar.challengelima.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import anzila.binar.challengelima.adapter.FilmAdapter
import anzila.binar.challengelima.databinding.ActivityHomeBinding
import anzila.binar.challengelima.network.RetrofitClient
import anzila.binar.challengelima.viewmodel.FilmViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var adapterfilm : FilmAdapter
    lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showDataFilm()

        val getName = sharedPref.getString("nama", "")
        binding.txtWel.text = "Halo, $getName"

        binding.rvFilm.layoutManager = LinearLayoutManager(this)
        adapterfilm = FilmAdapter(){
            val detail = Intent(applicationContext, DetailActivity::class.java)
           // detail.putExtra("detailfilm", it)
            startActivity(detail)
        }
        binding.rvFilm.adapter = adapterfilm


        binding.klikProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    fun showDataFilm() {
    //    val viewModelFilm = ViewModelProvider(this).get(FilmViewModel::class.java)
    //    viewModelFilm.callApiFilm()
     //   viewModelFilm.liveDataFilm.observe(this, Observer {
    //        if (it != null){
     //           binding.rvFilm.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
     //           binding.rvFilm.adapter = FilmAdapter(it)
     //       }
    //    })
        val viewModelFilm = ViewModelProvider(this).get(FilmViewModel::class.java)
        viewModelFilm.getliveDataFilm().observe(this, Observer {
            if (it != null){
                adapterfilm.setDataFilm(it)
                adapterfilm.notifyDataSetChanged()
            }
        })
        viewModelFilm.callApiFilm()
    }
}
