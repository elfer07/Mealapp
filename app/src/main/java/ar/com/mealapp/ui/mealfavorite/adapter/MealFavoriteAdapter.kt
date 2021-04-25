package ar.com.mealapp.ui.mealfavorite.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ar.com.mealapp.core.BaseViewHolder
import ar.com.mealapp.data.model.MealFavorite
import ar.com.mealapp.databinding.MealItemBinding
import com.bumptech.glide.Glide

/**
 * Created by Fernando Moreno on 23/4/2021.
 */
class MealFavoriteAdapter(
    private val mealFavoriteList: List<MealFavorite>,
    private val itemClickListener: OnMealFavoriteClickListener
): RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnMealFavoriteClickListener{
        fun onMealFavoriteClick(mealFavorite: MealFavorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = MealItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MealViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf {
                it != DiffUtil.DiffResult.NO_POSITION} ?: return@setOnClickListener
            itemClickListener.onMealFavoriteClick(mealFavoriteList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MealViewHolder -> holder.bind(mealFavoriteList[position])
        }
    }

    override fun getItemCount(): Int = mealFavoriteList.size

    private inner class MealViewHolder(val binding: MealItemBinding, val context: Context): BaseViewHolder<MealFavorite>(binding.root) {
        override fun bind(item: MealFavorite) {
            Glide.with(context).load(item.strMealThumb).centerCrop().into(binding.ivMeal)
            binding.tvTitle.text = item.strMeal
            binding.tvDescription.text = item.strInstructions
        }
    }
}