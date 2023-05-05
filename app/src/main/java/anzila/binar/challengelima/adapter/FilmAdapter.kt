package anzila.binar.challengelima.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import anzila.binar.challengelima.databinding.ItemFilmBinding
import anzila.binar.challengelima.model.ResponseFilmItem
import com.bumptech.glide.Glide

class FilmAdapter(var listFilm : List<ResponseFilmItem>): RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemFilmBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmAdapter.ViewHolder {
        var view = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmAdapter.ViewHolder, position: Int) {
        holder.binding.nameFilm.text = listFilm[position].movieName
        holder.binding.dateFilm.text = listFilm[position].createdAt
        Glide.with(holder.itemView).load(listFilm[position].image).into(holder.binding.imgFilm)
    }

    override fun getItemCount(): Int {
        return listFilm.size
    }

}