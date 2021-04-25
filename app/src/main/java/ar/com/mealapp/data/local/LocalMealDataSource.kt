package ar.com.mealapp.data.local

import ar.com.mealapp.core.Resource
import ar.com.mealapp.data.model.MealFavorite

/**
 * Created by Fernando Moreno on 23/4/2021.
 */
class LocalMealDataSource(private val mealDao: MealDao) {

    suspend fun saveFavorite(meal: MealFavorite){
        mealDao.saveFavoriteMeal(meal)
    }

    suspend fun deleteFavoriteMeal(meal: MealFavorite){
        mealDao.deleteFavoriteMeal(meal)
    }

    suspend fun getFavoritesMeals(): Resource<List<MealFavorite>> = Resource.Success(mealDao.getAllFavoritesMeals())

    suspend fun isMealFavorite(meal: MealFavorite): Boolean = mealDao.getMealById(meal.idMeal) != null
}