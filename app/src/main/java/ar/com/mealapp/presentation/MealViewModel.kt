package ar.com.mealapp.presentation

import androidx.lifecycle.*
import ar.com.mealapp.core.Resource
import ar.com.mealapp.data.model.MealFavorite
import ar.com.mealapp.repository.MealRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Fernando Moreno on 21/4/2021.
 */
class MealViewModel(
    private val repo: MealRepository
) : ViewModel() {

    private val mealsData = MutableLiveData<String>()

    fun setMeal(mealName: String) {
        mealsData.value = mealName
    }

    init {
        setMeal("Arrabiata")
    }

    val fetchMealsList = mealsData.distinctUntilChanged().switchMap { nameMeal ->
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repo.getMealList(nameMeal))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }

    fun getFavoritesMeals() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getFavoritesMeals())
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun saveOrDeleteFavoriteMeal(mealFavorite: MealFavorite) {
        viewModelScope.launch {
            if (repo.isMealFavorite(mealFavorite)) {
                repo.deleteFavoriteMeal(mealFavorite)
            } else {
                repo.saveFavoriteMeal(mealFavorite)
            }
        }
    }

    suspend fun isMealFavorite(mealFavorite: MealFavorite): Boolean =
        repo.isMealFavorite(mealFavorite)

}