package ar.com.mealapp.ui.mealfavorite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.mealapp.R
import ar.com.mealapp.core.Resource
import ar.com.mealapp.data.local.LocalDatabase
import ar.com.mealapp.data.local.LocalMealDataSource
import ar.com.mealapp.data.model.MealFavorite
import ar.com.mealapp.data.remote.RemoteMealDataSource
import ar.com.mealapp.databinding.FragmentFavoriteMealBinding
import ar.com.mealapp.presentation.MealViewModel
import ar.com.mealapp.presentation.MealViewModelFactory
import ar.com.mealapp.repository.MealRepositoryImpl
import ar.com.mealapp.repository.RetrofitClient
import ar.com.mealapp.ui.meal.MealFragmentDirections
import ar.com.mealapp.ui.mealfavorite.adapter.MealFavoriteAdapter

class FavoriteMealFragment : Fragment(R.layout.fragment_favorite_meal), MealFavoriteAdapter.OnMealFavoriteClickListener {

    private lateinit var binding: FragmentFavoriteMealBinding

    private val viewModel by viewModels<MealViewModel> {
        MealViewModelFactory(
            MealRepositoryImpl(
            RemoteMealDataSource(RetrofitClient.webservice),
            LocalMealDataSource(LocalDatabase.getDatabase(requireContext()).mealDao())
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFavoriteMealBinding.bind(view)
        binding.rvFavoriteMeal.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getFavoritesMeals().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    if (it.data.isEmpty()) {
                        binding.emptyContainer.root.visibility = View.VISIBLE
                    }
                    binding.rvFavoriteMeal.adapter = MealFavoriteAdapter(it.data, this)
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Error: ${it.exception}", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    override fun onMealFavoriteClick(mealFavorite: MealFavorite) {
        val action = FavoriteMealFragmentDirections.actionFavoriteMealFragmentToMealDetailFragment(
            mealFavorite.idMeal,
            mealFavorite.strMeal,
            mealFavorite.strCategory,
            mealFavorite.strArea,
            mealFavorite.strInstructions,
            mealFavorite.strMealThumb,
            mealFavorite.strYoutube,
            mealFavorite.strIngredient1,
            mealFavorite.strIngredient2,
            mealFavorite.strIngredient3,
            mealFavorite.strIngredient4,
            mealFavorite.strIngredient5,
            mealFavorite.strIngredient6,
            mealFavorite.strIngredient7,
            mealFavorite.strIngredient8,
            mealFavorite.strIngredient9,
            mealFavorite.strIngredient10,
            mealFavorite.strIngredient11,
            mealFavorite.strIngredient12,
            mealFavorite.strIngredient13,
            mealFavorite.strIngredient14,
            mealFavorite.strIngredient15,
            mealFavorite.strMeasure1,
            mealFavorite.strMeasure2,
            mealFavorite.strMeasure3,
            mealFavorite.strMeasure4,
            mealFavorite.strMeasure5,
            mealFavorite.strMeasure6,
            mealFavorite.strMeasure7,
            mealFavorite.strMeasure8,
            mealFavorite.strMeasure9,
            mealFavorite.strMeasure10,
            mealFavorite.strMeasure11,
            mealFavorite.strMeasure12,
            mealFavorite.strMeasure13,
            mealFavorite.strMeasure14,
            mealFavorite.strMeasure15,
            mealFavorite.strMeasure16,
            mealFavorite.strIngredient16,
            mealFavorite.strIngredient17,
            mealFavorite.strIngredient18,
            mealFavorite.strIngredient19,
            mealFavorite.strIngredient20,
            mealFavorite.strMeasure17,
            mealFavorite.strMeasure18,
            mealFavorite.strMeasure19,
            mealFavorite.strMeasure20
        )
        findNavController().navigate(action)
    }
}