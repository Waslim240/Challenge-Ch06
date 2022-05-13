package waslim.binar.andlima.challengech06v10.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import waslim.binar.andlima.challengech06v10.R
import waslim.binar.andlima.challengech06v10.model.datafilm.DataFilmResponseItem
import waslim.binar.andlima.challengech06v10.roomdatabase.Film
import waslim.binar.andlima.challengech06v10.roomdatabase.FilmDatabase

class DetailActivity : AppCompatActivity() {
    private var dB : FilmDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        dB = FilmDatabase.getInstance(this)

        getData()
        addAndDeleteData()

    }


//=================================== GET DATA DETAIL =============================================//
    private fun getData(){
        val detailfilm = intent.getParcelableExtra<DataFilmResponseItem>("detailfilm")
        val detailFilmFav = intent.getParcelableExtra<Film>("detailfilmfavorite")

        if (detailfilm != null){
            Glide.with(applicationContext).load(detailfilm.image).into(imageFilmDetail)
            tvJudulDetail.text = detailfilm.title
            tvTglRilisDetail.text = detailfilm.createdAt
            tvSutradaraDetail.text = detailfilm.director
            description.text = detailfilm.description
        } else if (detailFilmFav != null){
            Glide.with(applicationContext).load(detailFilmFav.image).into(imageFilmDetail)
            tvJudulDetail.text = detailFilmFav.title
            tvTglRilisDetail.text = detailFilmFav.createDate
            tvSutradaraDetail.text = detailFilmFav.sutradara
            description.text = detailFilmFav.description
        }
    }


//=========================== INSERT AND DELETE DATA DETAIL =======================================//
    private fun addAndDeleteData(){
        imageAddDelFavoriteDetail.setOnClickListener {
            val detailfilm = intent.getParcelableExtra<DataFilmResponseItem>("detailfilm")
            val detailFilmFav = intent.getParcelableExtra<Film>("detailfilmfavorite")

            GlobalScope.async {
                if (detailfilm != null){
                    val favorite = dB?.filmDao()?.insertFilm(Film(null, detailfilm.image, detailfilm.title, detailfilm.createdAt, detailfilm.director, detailfilm.description))
                    runOnUiThread {
                        if (favorite != 0.toLong()){
                            Toast.makeText(this@DetailActivity, "Berhasil Menambahkan Ke Favorite", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this@DetailActivity, "Gagal Menambahkan Ke Favorite", Toast.LENGTH_LONG).show()
                        }
                    }
                } else if (detailFilmFav != null){
                    val favorite = dB?.filmDao()?.deleteFilm(Film(detailFilmFav.id, detailFilmFav.image, detailFilmFav.title, detailFilmFav.createDate, detailFilmFav.sutradara, detailFilmFav.description))
                    runOnUiThread {
                        if (favorite != 0){
                            Toast.makeText(this@DetailActivity, "Berhasil Dihapus Dari Favorite  ", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this@DetailActivity, FavoriteActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this@DetailActivity, "Gagal Dihapus Dari Favorite  ", Toast.LENGTH_LONG).show()
                            finish()
                        }
                    }
                }
            }
        }
    }


}