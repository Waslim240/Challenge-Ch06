package waslim.binar.andlima.challengech06v10.roomdatabase

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Film (
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "createdate") var createDate: String,
    @ColumnInfo(name = "sutradara") var sutradara: String,
    @ColumnInfo(name = "description") var description: String

) : Parcelable