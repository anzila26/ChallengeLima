package anzila.binar.challengelima.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    const val  BASE_URL ="https://64548a2df803f345762b1a74.mockapi.io/"

    val instance : RestfulApi by lazy {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RestfulApi::class.java)
    }
}
