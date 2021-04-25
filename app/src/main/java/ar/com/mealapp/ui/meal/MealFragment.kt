package ar.com.mealapp.ui.meal

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.mealapp.R
import ar.com.mealapp.core.Resource
import ar.com.mealapp.data.local.LocalDatabase
import ar.com.mealapp.data.local.LocalMealDataSource
import ar.com.mealapp.data.model.Meal
import ar.com.mealapp.data.remote.RemoteMealDataSource
import ar.com.mealapp.databinding.FragmentMealBinding
import ar.com.mealapp.presentation.MealViewModel
import ar.com.mealapp.presentation.MealViewModelFactory
import ar.com.mealapp.repository.MealRepositoryImpl
import ar.com.mealapp.repository.RetrofitClient
import ar.com.mealapp.ui.meal.adapter.MealAdapter

class MealFragment : Fragment(R.layout.fragment_meal), MealAdapter.OnMealClickListener {

    private lateinit var binding: FragmentMealBinding

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

        binding = FragmentMealBinding.bind(view)

        setupSearchView()

        setHasOptionsMenu(true)

        binding.rvMeals.layoutManager = LinearLayoutManager(requireContext())

        viewModel.fetchMealsList.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvMeals.adapter =
                        MealAdapter(requireContext(), it.data.toMutableList(), this)
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("ERROR", "error: ${it.exception}")
                }
            }
        })
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setMeal(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onMealClick(meal: Meal, position: Int) {
        val action = MealFragmentDirections.actionMealFragmentToMealDetailFragment(
            meal.idMeal,
            meal.strMeal,
            meal.strCategory,
            meal.strArea,
            meal.strInstructions,
            meal.strMealThumb,
            meal.strYoutube,
            meal.strIngredient1,
            meal.strIngredient2,
            meal.strIngredient3,
            meal.strIngredient4,
            meal.strIngredient5,
            meal.strIngredient6,
            meal.strIngredient7,
            meal.strIngredient8,
            meal.strIngredient9,
            meal.strIngredient10,
            meal.strIngredient11,
            meal.strIngredient12,
            meal.strIngredient13,
            meal.strIngredient14,
            meal.strIngredient15,
            meal.strMeasure1,
            meal.strMeasure2,
            meal.strMeasure3,
            meal.strMeasure4,
            meal.strMeasure5,
            meal.strMeasure6,
            meal.strMeasure7,
            meal.strMeasure8,
            meal.strMeasure9,
            meal.strMeasure10,
            meal.strMeasure11,
            meal.strMeasure12,
            meal.strMeasure13,
            meal.strMeasure14,
            meal.strMeasure15,
            meal.strMeasure16,
            meal.strIngredient16,
            meal.strIngredient17,
            meal.strIngredient18,
            meal.strIngredient19,
            meal.strIngredient20,
            meal.strMeasure17,
            meal.strMeasure18,
            meal.strMeasure19,
            meal.strMeasure20
        )
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite -> {
                findNavController().navigate(R.id.action_mealFragment_to_favoriteMealFragment)
                false
            }
            else -> false
        }
    }

}