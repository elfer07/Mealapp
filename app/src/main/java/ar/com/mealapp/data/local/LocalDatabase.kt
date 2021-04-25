package ar.com.mealapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ar.com.mealapp.application.Constants
import ar.com.mealapp.data.model.MealFavorite

/**
 * Created by Fernando Moreno on 23/4/2021.
 */
@Database(entities = [MealFavorite::class], version = 1)
abstract class LocalDatabase: RoomDatabase() {

    abstract fun mealDao(): MealDao

    companion object {

        private var INSTANCE: LocalDatabase? = null

        fun getDatabase(context: Context): LocalDatabase{
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    Constants.TABLE
            )
                .build()
            return INSTANCE!!
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}