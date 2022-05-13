package waslim.binar.andlima.challengech06v10.datastore

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataUserManager (context: Context) {

    private val dataStore : DataStore<Preferences> = context.createDataStore(name = "data_user")

    companion object {
        val ID = preferencesKey<String>("ID")
        val USERNAME = preferencesKey<String>("USERNAME")
        val EMAIL = preferencesKey<String>("EMAIL")
        val PASSWORD = preferencesKey<String>("PASSWORD")
        val FULLNAME = preferencesKey<String>("FULLNAME")
        val DATEOFBIRTH = preferencesKey<String>("DATEOFBIRTH")
        val ADDRESS = preferencesKey<String>("ADDRESS")
        val IMAGE = preferencesKey<String>("IMAGE")
        val BOOLEAN = preferencesKey<Boolean>("BOOLEAN")
    }

    suspend fun saveData(id : String, username : String, email : String, password : String, fullName : String, dateOfbirth : String, address : String, image : String){
        dataStore.edit {
            it[ID] = id
            it[USERNAME] = username
            it[EMAIL] = email
            it[PASSWORD] = password
            it[FULLNAME] = fullName
            it[DATEOFBIRTH] = dateOfbirth
            it[ADDRESS] = address
            it[IMAGE] = image
        }
    }

    suspend fun checkData(boolean : Boolean){
        dataStore.edit {
            it[BOOLEAN] = boolean
        }
    }

    suspend fun logout(){
        dataStore.edit {
            it.clear()
        }
    }


    val id : Flow<String> = dataStore.data.map {
        it[ID] ?: ""
    }

    val username : Flow<String> = dataStore.data.map {
        it[USERNAME] ?: ""
    }

    val email : Flow<String> = dataStore.data.map {
        it[EMAIL] ?: ""
    }

    val password : Flow<String> = dataStore.data.map {
        it[PASSWORD] ?: ""
    }

    val fullName : Flow<String> = dataStore.data.map {
        it[FULLNAME] ?: ""
    }

    val dateOfbirth : Flow<String> = dataStore.data.map {
        it[DATEOFBIRTH] ?: ""
    }

    val address : Flow<String> = dataStore.data.map {
        it[ADDRESS] ?: ""
    }

    val image : Flow<String> = dataStore.data.map {
        it[IMAGE] ?: ""
    }

    val boolean : Flow<Boolean> = dataStore.data.map {
        it[BOOLEAN] ?: false
    }


}