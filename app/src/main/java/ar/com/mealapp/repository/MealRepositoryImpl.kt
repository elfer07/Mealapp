package ar.com.mealapp.repository

import ar.com.mealapp.core.Resource
import ar.com.mealapp.data.local.LocalMealDataSource
import ar.com.mealapp.data.model.Meal
import ar.com.mealapp.data.model.MealFavorite
import ar.com.mealapp.data.model.MealList
import ar.com.mealapp.data.remote.RemoteMealDataSource

/**
 * Created by Fernando Moreno on 21/4/2021.
 */
class MealRepositoryImpl(
    private val dataSourceRemote: RemoteMealDataSource,
    private val dataSourceLocal: LocalMealDataSource
): MealRepository {
    override suspend fun getMeals(): MealList = dataSourceRemote.getMeals()

    override suspend fun getMealList(mealName: String): Resource<List<Meal>> = dataSourceRemote.getMealByName(mealName)

    override suspend fun getFavoritesMeals(): Resource<List<MealFavorite>> = dataSourceLocal.getFavoritesMeals()

    override suspend fun saveFavoriteMeal(mealFavorite: MealFavorite) {
        dataSourceLocal.saveFavorite(mealFavorite)
    }

    override suspend fun deleteFavoriteMeal(mealFavorite: MealFavorite) {
        dataSourceLocal.deleteFavoriteMeal(mealFavorite)
    }

    override suspend fun isMealFavorite(mealFavorite: MealFavorite): Boolean = dataSourceLocal.isMealFavorite(mealFavorite)
}