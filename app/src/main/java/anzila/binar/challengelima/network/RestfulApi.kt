package anzila.binar.challengelima.network

import anzila.binar.challengelima.model.DataFilm
import anzila.binar.challengelima.model.ResponseFilmItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RestfulApi {
    @GET("film")
    fun getAllFilm(): Call<List<ResponseFilmItem>>
}