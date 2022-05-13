package waslim.binar.andlima.challengech06v10.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import waslim.binar.andlima.challengech06v10.R
import waslim.binar.andlima.challengech06v10.adapter.AdapterFilmRoom
import waslim.binar.andlima.challengech06v10.datastore.DataUserManager
import waslim.binar.andlima.challengech06v10.roomdatabase.Film
import waslim.binar.andlima.challengech06v10.roomdatabase.FilmDatabase

class FavoriteActivity : AppCompatActivity() {
    var dB : FilmDatabase? = null
    var filmFav : Film? = null
    lateinit var adapterFav : AdapterFilmRoom
    lateinit var dataUserManager: DataUserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        dB = FilmDatabase.getInstance(this)

        getDataStore()
        goToHome()
        init()
        get()

    }


//====================================== GET DATA USERNAME ========================================//
    @SuppressLint("SetTextI18n")
    private fun getDataStore(){
        dataUserManager = DataUserManager(this)

        dataUserManager.username.asLiveData().observe(this){
            welcome_usernamefav.text = "Welcome, $it"
        }
    }


//====================================== LIST DATA FAVORITE ========================================//
    private fun init(){
        GlobalScope.async {
            filmFav = dB?.filmDao()?.getFilmId(filmFav!!.id!!.toInt())
            dB?.filmDao()?.getAllFilm()
        }
    }

    private fun get(){
        rvFilm_favorite.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        GlobalScope.launch {
            val listdata = dB?.filmDao()?.getAllFilm()
            runOnUiThread {
                listdata.let {
                    adapterFav = AdapterFilmRoom(){
                        val pdh = Intent(this@FavoriteActivity, DetailActivity::class.java)
                        pdh.putExtra("detailfilmfavorite", it)
                        startActivity(pdh)
                    }
                    rvFilm_favorite.adapter = adapterFav
                    adapterFav.setFavorite(it!!)
                }
            }
        }
    }


//====================================== GO TO HOME ===============================================//
    private fun goToHome(){
        imageHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}