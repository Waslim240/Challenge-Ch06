package waslim.binar.andlima.challengech06v10.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_profile.*
import waslim.binar.andlima.challengech06v10.R
import waslim.binar.andlima.challengech06v10.adapter.AdapterFilm
import waslim.binar.andlima.challengech06v10.datastore.DataUserManager
import waslim.binar.andlima.challengech06v10.viewmodel.ViewModelFilm

class HomeActivity : AppCompatActivity() {
    lateinit var adapterFilm: AdapterFilm
    lateinit var dataUserManager: DataUserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initRecycler()
        getDataFilm()
        goToFavorite()
        goToProfile()
        getDataStore()
    }

// ===================================== GET DATA USERNAME ========================================//
    @SuppressLint("SetTextI18n")
    private fun getDataStore(){
        dataUserManager = DataUserManager(this)

        dataUserManager.username.asLiveData().observe(this){
            welcome_username.text = "Welcome, $it"
        }
    }



// ===================================== MENAMPILKAN LIST FILM =====================================//
    private fun initRecycler(){
        rvFilm.layoutManager = LinearLayoutManager(this)
        adapterFilm = AdapterFilm() {
            val pdh = Intent(this, DetailActivity::class.java)
            pdh.putExtra("detailfilm", it)
            startActivity(pdh)
        }

        rvFilm.adapter = adapterFilm
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getDataFilm(){
        val viewModel = ViewModelProvider(this).get(ViewModelFilm::class.java)

        viewModel.getLiveFilmObserver().observe(this, Observer {
            when {
                it != null -> {
                    adapterFilm.setDataFilm(it)
                    adapterFilm.notifyDataSetChanged()
                }
            }
        })

        viewModel.makeApiFilm()
    }

// ================================================ GO TO PROFILE ==================================//
    private fun goToProfile(){
        account.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }
    }


// ================================================ GO TO FAVORITE ==================================//
    private fun goToFavorite(){
        favorite.setOnClickListener {
            startActivity(Intent(this, FavoriteActivity::class.java))
            finish()
        }
    }


}