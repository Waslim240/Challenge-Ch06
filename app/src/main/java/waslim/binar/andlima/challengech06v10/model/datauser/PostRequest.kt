package waslim.binar.andlima.challengech06v10.model.datauser

import com.google.gson.annotations.SerializedName

data class PostRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)