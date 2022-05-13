package waslim.binar.andlima.challengech06v10.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Film::class], version = 1)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao() : FilmDao

    companion object{
        private var INSTANCE : FilmDatabase? = null
        fun getInstance(context: Context) : FilmDatabase? {
            if (INSTANCE == null){
                synchronized(FilmDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FilmDatabase::class.java, "Film.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}