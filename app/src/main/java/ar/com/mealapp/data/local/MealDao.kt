package ar.com.mealapp.data.local

import androidx.room.*
import ar.com.mealapp.data.model.MealFavorite

/**
 * Created by Fernando Moreno on 23/4/2021.
 */
@Dao
interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavoriteMeal(meal: MealFavorite)

    @Delete
    suspend fun deleteFavoriteMeal(meal: MealFavorite)

    @Query("SELECT * FROM favorites")
    suspend fun getAllFavoritesMeals(): List<MealFavorite>

    @Query("SELECT * FROM favorites WHERE idMeal = :mealId")
    suspend fun getMealById(mealId: Int): MealFavorite
}