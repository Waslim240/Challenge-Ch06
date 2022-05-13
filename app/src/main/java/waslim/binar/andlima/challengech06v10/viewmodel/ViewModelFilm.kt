package waslim.binar.andlima.challengech06v10.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import waslim.binar.andlima.challengech06v10.model.datafilm.DataFilmResponseItem
import waslim.binar.andlima.challengech06v10.network.ApiClient

class ViewModelFilm : ViewModel() {
    var liveDataFilm : MutableLiveData<List<DataFilmResponseItem>?> = MutableLiveData()


//================================= LIVE DATA FILM ================================================//
    fun getLiveFilmObserver() : MutableLiveData<List<DataFilmResponseItem>?> {
        return liveDataFilm
    }


//================================= API CLIENT FILM ===============================================//
    fun makeApiFilm(){
        ApiClient.instance.getAllFilm()
            .enqueue(object : Callback<List<DataFilmResponseItem>> {
                override fun onResponse(
                    call: Call<List<DataFilmResponseItem>>,
                    response: Response<List<DataFilmResponseItem>>
                ) {
                    when {
                        response.isSuccessful -> {
                            liveDataFilm.postValue(response.body())
                        }
                        else -> {
                            liveDataFilm.postValue(null)
                        }
                    }
                }

                override fun onFailure(call: Call<List<DataFilmResponseItem>>, t: Throwable) {
                    liveDataFilm.postValue(null)
                }
            })
    }

}