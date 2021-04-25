package ar.com.mealapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ar.com.mealapp.repository.MealRepository

/**
 * Created by Fernando Moreno on 21/4/2021.
 */
class MealViewModelFactory(private val repo: MealRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MealRepository::class.java).newInstance(repo)
    }
}