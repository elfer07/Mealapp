package ar.com.mealapp.repository

import ar.com.mealapp.data.model.MealList
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Fernando Moreno on 21/4/2021.
 */
interface WebService {

    @GET("search.php?s=")
    suspend fun getMeals(): MealList

    @GET("search.php")
    suspend fun getMealByName(@Query("s") mealName: String): MealList
}