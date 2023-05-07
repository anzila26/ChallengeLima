package anzila.binar.challengelima.model

import android.os.Parcel
import android.os.Parcelable


data class DataFilm(
    val image : String,
    val movie_name : String,
    val movie_rated : String,
    val release : String,
    val synopsis : String
)
