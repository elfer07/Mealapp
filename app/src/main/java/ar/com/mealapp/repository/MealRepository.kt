package ar.com.mealapp.repository

import ar.com.mealapp.core.Resource
import ar.com.mealapp.data.model.Meal
import ar.com.mealapp.data.model.MealFavorite
import ar.com.mealapp.data.model.MealList

/**
 * Created by Fernando Moreno on 21/4/2021.
 */
interface MealRepository {

    suspend fun getMeals(): MealList
    suspend fun getMealList(mealName: String): Resource<List<Meal>>
    suspend fun getFavoritesMeals(): Resource<List<MealFavorite>>
    suspend fun saveFavoriteMeal(mealFavorite: MealFavorite)
    suspend fun deleteFavoriteMeal(mealFavorite: MealFavorite)
    suspend fun isMealFavorite(mealFavorite: MealFavorite): Boolean
}