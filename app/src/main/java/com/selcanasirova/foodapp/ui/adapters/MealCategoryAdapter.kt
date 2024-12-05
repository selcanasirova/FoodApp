package com.selcanasirova.foodapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.selcanasirova.foodapp.data.MealsByCategory
import com.selcanasirova.foodapp.databinding.MealItemBinding
import com.selcanasirova.foodapp.ui.fragments.MealCategoryFragment


class MealCategoryAdapter: RecyclerView.Adapter<MealCategoryAdapter.MealCategoryAdapyerHolderView>() {
    inner class MealCategoryAdapyerHolderView (val itemMealCategoryBinding: MealItemBinding)
        :ViewHolder(itemMealCategoryBinding.root)

    private val mealByCategoryList = arrayListOf<MealsByCategory>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MealCategoryAdapyerHolderView {
        val layout = MealItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MealCategoryAdapyerHolderView(layout)
    }

    override fun getItemCount(): Int {
       return mealByCategoryList.size
    }

    override fun onBindViewHolder(holder: MealCategoryAdapyerHolderView, position: Int) {
        val mealsByCategoryModel = mealByCategoryList[position]

        holder.itemMealCategoryBinding.textView.text = mealsByCategoryModel.strMeal
        Glide.with(holder.itemView).load(mealsByCategoryModel.strMealThumb)
            .into(holder.itemMealCategoryBinding.imgMeal)
    }

    fun updateMealByCategory(list: List<MealsByCategory>){
        mealByCategoryList.clear()
        mealByCategoryList.addAll(list)
        notifyDataSetChanged()
    }
}