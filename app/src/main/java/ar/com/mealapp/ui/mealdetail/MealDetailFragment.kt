package ar.com.mealapp.ui.mealdetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import ar.com.mealapp.R
import ar.com.mealapp.data.local.LocalDatabase
import ar.com.mealapp.data.local.LocalMealDataSource
import ar.com.mealapp.data.model.MealFavorite
import ar.com.mealapp.data.remote.RemoteMealDataSource
import ar.com.mealapp.databinding.FragmentMealDetailBinding
import ar.com.mealapp.presentation.MealViewModel
import ar.com.mealapp.presentation.MealViewModelFactory
import ar.com.mealapp.repository.MealRepositoryImpl
import ar.com.mealapp.repository.RetrofitClient
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch

class MealDetailFragment : Fragment(R.layout.fragment_meal_detail) {

    private lateinit var binding: FragmentMealDetailBinding

    private var isMealFavorited: Boolean? = null

    private val viewModel by viewModels<MealViewModel> {
        MealViewModelFactory(
            MealRepositoryImpl(
                RemoteMealDataSource(RetrofitClient.webservice),
                LocalMealDataSource(LocalDatabase.getDatabase(requireContext()).mealDao())
            )
        )
    }

    private val args by navArgs<MealDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMealDetailBinding.bind(view)

        setHasOptionsMenu(true)

        Glide.with(requireContext()).load(args.strMealThumb).centerCrop()
            .into(binding.imgBackground)
        binding.tvMealTitle.text = args.strMeal
        binding.tvMealInstructions.text = args.strInstructions

        if (args.strMeasure1 == "" || args.strMeasure1 == null || args.strIngredient1 == ""){
            binding.tvMeasure1.visibility = View.GONE
        } else {
            binding.tvMeasure1.visibility = View.VISIBLE
            binding.tvMeasure1.text = "${args.strMeasure1.toString()} ${args.strIngredient1.toString()}"
        }

        if (args.strMeasure2 == ""){
            binding.tvMeasure2.visibility = View.GONE
        } else {
            binding.tvMeasure2.visibility = View.VISIBLE
            binding.tvMeasure2.text = "${args.strMeasure2.toString()} ${args.strIngredient2.toString()}"
        }

        if (args.strMeasure3 == ""){
            binding.tvMeasure3.visibility = View.GONE
        } else {
            binding.tvMeasure3.visibility = View.VISIBLE
            binding.tvMeasure3.text = "${args.strMeasure3.toString()} ${args.strIngredient3.toString()}"
        }

        if (args.strMeasure4 == "") {
            binding.tvMeasure4.visibility = View.GONE
        } else {
            binding.tvMeasure4.visibility = View.VISIBLE
            binding.tvMeasure4.text = "${args.strMeasure4.toString()} ${args.strIngredient4.toString()}"
        }

        if (args.strMeasure5 == ""){
            binding.tvMeasure5.visibility = View.GONE
        } else {
            binding.tvMeasure5.visibility = View.VISIBLE
            binding.tvMeasure5.text = "${args.strMeasure5.toString()} ${args.strIngredient5.toString()}"
        }

        if (args.strMeasure6 == ""){
            binding.tvMeasure6.visibility = View.GONE
        } else {
            binding.tvMeasure6.visibility = View.VISIBLE
            binding.tvMeasure6.text = "${args.strMeasure6.toString()} ${args.strIngredient6.toString()}"
        }

        if (args.strMeasure7 == ""){
            binding.tvMeasure7.visibility = View.GONE
        } else {
            binding.tvMeasure7.visibility = View.VISIBLE
            binding.tvMeasure7.text = "${args.strMeasure7.toString()} ${args.strIngredient7.toString()}"
        }

        if (args.strMeasure8 == "" || args.strMeasure8 == null || args.strIngredient8 == ""){
            binding.tvMeasure8.visibility = View.GONE
        } else {
            binding.tvMeasure8.visibility = View.VISIBLE
            binding.tvMeasure8.text = "${args.strMeasure8.toString()} ${args.strIngredient8.toString()}"
        }

        if (args.strMeasure9 == "" || args.strMeasure9 == null || args.strIngredient9 == ""){
            binding.tvMeasure9.visibility = View.GONE
        } else {
            binding.tvMeasure9.visibility = View.VISIBLE
            binding.tvMeasure9.text = "${args.strMeasure9.toString()} ${args.strIngredient9.toString()}"
        }

        if (args.strMeasure10 == "" || args.strMeasure10 == null || args.strIngredient10 == ""){
            binding.tvMeasure10.visibility = View.GONE
        } else {
            binding.tvMeasure10.visibility = View.VISIBLE
            binding.tvMeasure10.text =
                "${args.strMeasure10.toString()} ${args.strIngredient10.toString()}"
        }

        if (args.strMeasure11 == "" || args.strMeasure11 == null || args.strIngredient11 == ""){
            binding.tvMeasure11.visibility = View.GONE
        } else {
            binding.tvMeasure11.visibility = View.VISIBLE
            binding.tvMeasure11.text =
                "${args.strMeasure11} ${args.strIngredient11}"
        }

        if (args.strMeasure12 == "" || args.strMeasure12 == null || args.strIngredient12 == ""){
            binding.tvMeasure12.visibility = View.GONE
        } else {
            binding.tvMeasure12.visibility = View.VISIBLE
            binding.tvMeasure12.text =
                "${args.strMeasure12} ${args.strIngredient12}"
        }

        if (args.strMeasure13 == "" || args.strMeasure13 == null || args.strIngredient13 == ""){
            binding.tvMeasure13.visibility = View.GONE
        } else {
            binding.tvMeasure13.visibility = View.VISIBLE
            binding.tvMeasure13.text =
                "${args.strMeasure13} ${args.strIngredient13}"
        }

        if (args.strMeasure14 == "" || args.strMeasure14 == null || args.strIngredient14 == ""){
            binding.tvMeasure14.visibility = View.GONE
        } else {
            binding.tvMeasure14.visibility = View.VISIBLE
            binding.tvMeasure14.text =
                "${args.strMeasure14} ${args.strIngredient14}"
        }

        if (args.strMeasure15 == "" || args.strMeasure15 == null || args.strIngredient15 == ""){
            binding.tvMeasure15.visibility = View.GONE
        } else {
            binding.tvMeasure15.visibility = View.VISIBLE
            binding.tvMeasure15.text =
                "${args.strMeasure15} ${args.strIngredient15}"
        }

        if (args.strMeasure16 == "" || args.strMeasure16 == null || args.strIngredient16 == "") {
            binding.tvMeasure16.visibility = View.GONE
        } else {
            binding.tvMeasure16.visibility = View.VISIBLE
            binding.tvMeasure16.text = "${args.strMeasure16} ${args.strIngredient16}"
        }

        if (args.strMeasure17 == "" || args.strMeasure17 == null || args.strIngredient17 == "") {
            binding.tvMeasure17.visibility = View.GONE
        } else {
            binding.tvMeasure17.visibility = View.VISIBLE
            binding.tvMeasure17.text = "${args.strMeasure17} ${args.strIngredient17}"
        }

        if (args.strMeasure18 == "" || args.strMeasure18 == null || args.strIngredient18 == "") {
            binding.tvMeasure18.visibility = View.GONE
        } else {
            binding.tvMeasure18.visibility = View.VISIBLE
            binding.tvMeasure18.text = "${args.strMeasure18} ${args.strIngredient18}"
        }

        if (args.strMeasure19 == "" || args.strMeasure19 == null || args.strIngredient19 == "") {
            binding.tvMeasure19.visibility = View.GONE
        } else {
            binding.tvMeasure19.visibility = View.VISIBLE
            binding.tvMeasure19.text = "${args.strMeasure19} ${args.strIngredient19}"
        }

        if (args.strMeasure20 == "" || args.strMeasure20 == null || args.strIngredient20 == "") {
            binding.tvMeasure20.visibility = View.GONE
        } else {
            binding.tvMeasure20.visibility = View.VISIBLE
            binding.tvMeasure20.text = "${args.strMeasure20} ${args.strIngredient20}"
        }


        fun changeButtonIcon() {
            val isMealFavorited = isMealFavorited ?: return
            binding.btnFavorite.setImageResource(
                when {
                    isMealFavorited -> {
                        R.drawable.ic_baseline_delete_24
                    }
                    else -> {
                        R.drawable.ic_baseline_favorite_24
                    }
                }
            )
        }

        binding.btnFavorite.setOnClickListener {
            val isMealFavorite = isMealFavorited ?: return@setOnClickListener

            viewModel.saveOrDeleteFavoriteMeal(
                MealFavorite(
                    args.idMeal,
                    args.strMeal,
                    args.strCategory,
                    args.strArea,
                    args.strInstructions,
                    args.strMealThumb,
                    args.strYoutube,
                    args.strIngredient1,
                    args.strIngredient2,
                    args.strIngredient3,
                    args.strIngredient4,
                    args.strIngredient5,
                    args.strIngredient6,
                    args.strIngredient7,
                    args.strIngredient8,
                    args.strIngredient9,
                    args.strIngredient10,
                    args.strIngredient11,
                    args.strIngredient12,
                    args.strIngredient13,
                    args.strIngredient14,
                    args.strIngredient15,
                    args.strMeasure1,
                    args.strMeasure2,
                    args.strMeasure3,
                    args.strMeasure4,
                    args.strMeasure5,
                    args.strMeasure6,
                    args.strMeasure7,
                    args.strMeasure8,
                    args.strMeasure9,
                    args.strMeasure10,
                    args.strMeasure11,
                    args.strMeasure12,
                    args.strMeasure13,
                    args.strMeasure14,
                    args.strMeasure15,
                    args.strIngredient16,
                    args.strMeasure16,
                    args.strIngredient17,
                    args.strMeasure17,
                    args.strIngredient18,
                    args.strMeasure18,
                    args.strIngredient19,
                    args.strMeasure19,
                    args.strIngredient20,
                    args.strMeasure20
                )
            )
            this.isMealFavorited = !isMealFavorite
            changeButtonIcon()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            isMealFavorited = viewModel.isMealFavorite(
                MealFavorite(args.idMeal,
                    args.strMeal,
                    args.strCategory,
                    args.strArea,
                    args.strInstructions,
                    args.strMealThumb,
                    args.strYoutube,
                    args.strIngredient1,
                    args.strIngredient2,
                    args.strIngredient3,
                    args.strIngredient4,
                    args.strIngredient5,
                    args.strIngredient6,
                    args.strIngredient7,
                    args.strIngredient8,
                    args.strIngredient9,
                    args.strIngredient10,
                    args.strIngredient11,
                    args.strIngredient12,
                    args.strIngredient13,
                    args.strIngredient14,
                    args.strIngredient15,
                    args.strMeasure1,
                    args.strMeasure2,
                    args.strMeasure3,
                    args.strMeasure4,
                    args.strMeasure5,
                    args.strMeasure6,
                    args.strMeasure7,
                    args.strMeasure8,
                    args.strMeasure9,
                    args.strMeasure10,
                    args.strMeasure11,
                    args.strMeasure12,
                    args.strMeasure13,
                    args.strMeasure14,
                    args.strMeasure15,
                    args.strIngredient16,
                    args.strMeasure16,
                    args.strIngredient17,
                    args.strMeasure17,
                    args.strIngredient18,
                    args.strMeasure18,
                    args.strIngredient19,
                    args.strMeasure19,
                    args.strIngredient20,
                    args.strMeasure20
                )
            )
            changeButtonIcon()

        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.second_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.youtube -> {
                val uri: Uri = Uri.parse(args.strYoutube)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
                false
            }
            else -> false
        }
    }
}