package ar.com.mealapp.core

import java.lang.Exception

/**
 * Created by Fernando Moreno on 21/4/2021.
 */
sealed class Resource<out T> {
    class Loading<out T>: Resource<T>()
    data class  Success<out T>(val data: T): Resource<T>()
    data class  Failure(val exception: Exception): Resource<Nothing>()
}