package waslim.binar.andlima.challengech06v10.model.datauser

import com.google.gson.annotations.SerializedName

data class PutRequest(
    @SerializedName("address")
    val address: String,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("username")
    val username: String
)
