package waslim.binar.andlima.challengech06v10.network

import retrofit2.Call
import retrofit2.http.*
import waslim.binar.andlima.challengech06v10.model.datafilm.DataFilmResponseItem
import waslim.binar.andlima.challengech06v10.model.datauser.DataUserResponseItem
import waslim.binar.andlima.challengech06v10.model.datauser.PostRequest
import waslim.binar.andlima.challengech06v10.model.datauser.PutRequest

interface ApiService {

    // get data film
    @GET("film")
    fun getAllFilm() : Call<List<DataFilmResponseItem>>

    // post data register
    @POST("user")
    fun postDataUser (
        @Body request : PostRequest
    ) : Call<DataUserResponseItem>

    // get login
    @GET("user")
    fun getDataUser() : Call<List<DataUserResponseItem>>

    // update user
    @PUT("user/{id}")
    fun updateDataUser(
        @Path ("id") id : String,
        @Body requestUpdate : PutRequest) : Call<List<DataUserResponseItem>>

}