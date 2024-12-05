package com.selcanasirova.foodapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.selcanasirova.foodapp.data.Category
import com.selcanasirova.foodapp.data.CategoryList
import com.selcanasirova.foodapp.databinding.CategoryMealsBinding
import com.selcanasirova.foodapp.databinding.FragmentCategoriesBinding
import com.selcanasirova.foodapp.ui.fragments.page.HomeFragmentDirections

class CategoriesAdapter(val onClick : (category:String)-> Unit):RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {
    inner class CategoriesViewHolder (val itemCategoriesBinding: CategoryMealsBinding):ViewHolder(itemCategoriesBinding.root)

    private var categoryMealList = arrayListOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        var layout = CategoryMealsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoriesViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return categoryMealList.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        var categorymealModel = categoryMealList[position]

        Glide.with(holder.itemView).load(categorymealModel.strCategoryThumb).into(holder.itemCategoriesBinding.imgCategory)
        holder.itemCategoriesBinding.tvCategoryName.text = categorymealModel.strCategory

        holder.itemView.setOnClickListener(){
            Navigation.findNavController(it).navigate(
                HomeFragmentDirections.actionHomeFragmentToMealCategoryFragment(categorymealModel.strCategory.toString())
            )
        }
    }

    fun updateCategory (list: List<Category>){
        categoryMealList.clear()
        categoryMealList.addAll(list)
        notifyDataSetChanged()
    }
}