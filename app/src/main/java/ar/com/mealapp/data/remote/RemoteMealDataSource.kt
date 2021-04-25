package ar.com.mealapp.data.remote

import ar.com.mealapp.core.Resource
import ar.com.mealapp.data.model.Meal
import ar.com.mealapp.data.model.MealList
import ar.com.mealapp.repository.RetrofitClient
import ar.com.mealapp.repository.WebService

/**
 * Created by Fernando Moreno on 21/4/2021.
 */
class RemoteMealDataSource(private val webService: WebService) {

    suspend fun getMeals(): MealList {
        return webService.getMeals()
    }

    suspend fun getMealByName(mealName: String): Resource<List<Meal>> {
        return Resource.Success(RetrofitClient.webservice.getMealByName(mealName).meals)
    }
}