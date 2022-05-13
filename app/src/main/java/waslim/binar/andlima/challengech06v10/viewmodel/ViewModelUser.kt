package waslim.binar.andlima.challengech06v10.viewmodel

import android.content.Intent
import android.widget.Toast
import androidx.datastore.preferences.protobuf.Api
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import waslim.binar.andlima.challengech06v10.model.datauser.DataUserResponseItem
import waslim.binar.andlima.challengech06v10.model.datauser.PostRequest
import waslim.binar.andlima.challengech06v10.model.datauser.PutRequest
import waslim.binar.andlima.challengech06v10.network.ApiClient

class ViewModelUser : ViewModel(){
    var liveDataUserRegister : MutableLiveData<DataUserResponseItem?> = MutableLiveData()
    var liveDataUserLogin : MutableLiveData<List<DataUserResponseItem>?> = MutableLiveData()
    var liveDataUserUpdate : MutableLiveData<List<DataUserResponseItem>?> = MutableLiveData()


//================================== LIVE DATA USER ===============================================//
    fun getLiveRegister() : MutableLiveData<DataUserResponseItem?> {
        return liveDataUserRegister
    }

    fun getLiveLogin() : MutableLiveData<List<DataUserResponseItem>?> {
        return liveDataUserLogin
    }

    fun getLiveUpdate() : MutableLiveData<List<DataUserResponseItem>?> {
        return liveDataUserUpdate
    }


//================================= API CLIENT USER ===============================================//
    fun makeApiRegister(username : String, email : String, password : String){
        ApiClient.instance.postDataUser(PostRequest(email, password, username))
            .enqueue(object : Callback<DataUserResponseItem>{
                override fun onResponse(
                    call: Call<DataUserResponseItem>,
                    response: Response<DataUserResponseItem>
                ) {
                    if (response.isSuccessful){
                        liveDataUserRegister.postValue(response.body())
                    } else {
                        liveDataUserRegister.postValue(null)
                    }
                }

                override fun onFailure(call: Call<DataUserResponseItem>, t: Throwable) {
                    liveDataUserRegister.postValue(null)
                }

            })
    }


    fun makeApiLogin(){
        ApiClient.instance.getDataUser()
            .enqueue(object : Callback<List<DataUserResponseItem>>{
                override fun onResponse(
                    call: Call<List<DataUserResponseItem>>,
                    response: Response<List<DataUserResponseItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataUserLogin.postValue(response.body())
                    } else {
                        liveDataUserLogin.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<DataUserResponseItem>>, t: Throwable) {
                    liveDataUserLogin.postValue(null)
                }

            })
    }


    fun makeApiUpdate(id : String, address : String, dateOfBirth : String, fullName : String, image : String, username : String){
        ApiClient.instance.updateDataUser(id, PutRequest(address, dateOfBirth, fullName, image, username))
            .enqueue(object : Callback<List<DataUserResponseItem>>{
                override fun onResponse(
                    call: Call<List<DataUserResponseItem>>,
                    response: Response<List<DataUserResponseItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataUserUpdate.postValue(response.body())
                    } else {
                        liveDataUserUpdate.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<DataUserResponseItem>>, t: Throwable) {
                    liveDataUserUpdate.postValue(null)
                }

            })
    }

}