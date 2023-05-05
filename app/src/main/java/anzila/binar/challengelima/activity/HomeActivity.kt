package anzila.binar.challengelima.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import anzila.binar.challengelima.adapter.FilmAdapter
import anzila.binar.challengelima.databinding.ActivityHomeBinding
import anzila.binar.challengelima.model.ResponseFilmItem
import anzila.binar.challengelima.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    lateinit var sharedPref : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = getSharedPreferences("dataUser", Context.MODE_PRIVATE)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getDataFilm()

        val getName = sharedPref.getString("nama", "")
        binding.txtWel.text = "Halo, $getName"

        binding.klikProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    fun getDataFilm(){
        RetrofitClient.instance.getAllFilm().enqueue(object : Callback<List<ResponseFilmItem>> {
            override fun onResponse(
                call: Call<List<ResponseFilmItem>>,
                response: Response<List<ResponseFilmItem>>
            ) {
                if (response.isSuccessful) {
                    binding.rvFilm.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL,false)
                    binding.rvFilm.adapter = FilmAdapter(response.body()!!)
                    //show data
                } else {
                    Toast.makeText(this@HomeActivity, "Failed load data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<ResponseFilmItem>>, t: Throwable) {
                Toast.makeText(this@HomeActivity, "", Toast.LENGTH_SHORT).show()
            }
        })
    }
}