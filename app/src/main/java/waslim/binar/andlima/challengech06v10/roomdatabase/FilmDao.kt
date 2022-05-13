package waslim.binar.andlima.challengech06v10.roomdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FilmDao {

    @Query("SELECT * FROM Film")
    fun getAllFilm() : List<Film>

    @Query("SELECT * FROM Film WHERE Film.id = :id")
    fun getFilmId(id:Int) : Film

    @Insert
    fun insertFilm(film: Film) : Long

    @Delete
    fun deleteFilm(film: Film) : Int

}