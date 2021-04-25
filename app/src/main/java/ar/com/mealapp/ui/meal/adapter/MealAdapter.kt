package ar.com.mealapp.ui.meal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ar.com.mealapp.core.BaseViewHolder
import ar.com.mealapp.data.model.Meal
import ar.com.mealapp.databinding.MealItemBinding
import com.bumptech.glide.Glide

/**
 * Created by Fernando Moreno on 21/4/2021.
 */
class MealAdapter(
    private val context: Context,
    private val mealList: List<Meal>,
    private val itemClickListener: OnMealClickListener
): RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMealClickListener{
        fun onMealClick(meal: Meal, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = MealItemBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = MealViewHolder(itemBinding)

        itemBinding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf {
                it != DiffUtil.DiffResult.NO_POSITION} ?: return@setOnClickListener
            itemClickListener.onMealClick(mealList[position], position)
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MealViewHolder -> holder.bind(mealList[position])
        }
    }

    override fun getItemCount(): Int = mealList.size

    private inner class MealViewHolder(val binding: MealItemBinding): BaseViewHolder<Meal>(binding.root){
        override fun bind(item: Meal) {
            Glide.with(context).load(item.strMealThumb).centerCrop().into(binding.ivMeal)
            binding.tvTitle.text = item.strMeal
            binding.tvDescription.text = item.strInstructions
        }

    }
}