package waslim.binar.andlima.challengech06v10.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_listing_film_room.view.*
import waslim.binar.andlima.challengech06v10.R
import waslim.binar.andlima.challengech06v10.roomdatabase.Film

class AdapterFilmRoom(private var onclick : (Film) -> Unit) : RecyclerView.Adapter<AdapterFilmRoom.ViewHolder> () {

    private var listFavorite : List<Film>? = null

    fun setFavorite(fav : List<Film>){
        this.listFavorite = fav
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterFilmRoom.ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_listing_film_room, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: AdapterFilmRoom.ViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(listFavorite!![position].image).into(holder.itemView.imageFilmRoom)
        holder.itemView.tvJudulRoom.text = listFavorite!![position].title
        holder.itemView.tvTglRilisRoom.text = listFavorite!![position].createDate
        holder.itemView.tvSutradaraRoom.text = listFavorite!![position].sutradara

        holder.itemView.cardDetailFilmRoom.setOnClickListener {
            onclick(listFavorite!![position])
        }
    }

    override fun getItemCount(): Int {
        return when (listFavorite) {
            null -> {
                0
            }
            else -> {
                listFavorite!!.size
            }
        }
    }


}