package com.selcanasirova.foodapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.selcanasirova.foodapp.data.MealsByCategory
import com.selcanasirova.foodapp.databinding.PopularItemBinding
import com.selcanasirova.foodapp.ui.fragments.page.HomeFragmentDirections

class PopularItemAdapter(val onClick: (category: String) -> Unit) : RecyclerView.Adapter<PopularItemAdapter.PopularItemAdatperViewHolder>() {

    private val populatItemList = arrayListOf<MealsByCategory>()

    inner class PopularItemAdatperViewHolder (val itemPopularItemBinding: PopularItemBinding)
        : ViewHolder(itemPopularItemBinding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularItemAdatperViewHolder {
        val layout = PopularItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PopularItemAdatperViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return populatItemList.size
    }

    override fun onBindViewHolder(holder: PopularItemAdatperViewHolder, position: Int) {
        val popularItemModel = populatItemList[position]

        Glide.with(holder.itemView).load(popularItemModel.strMealThumb)
            .into(holder.itemPopularItemBinding.imgPopularMealItem)


        holder.itemView.setOnClickListener {
            Navigation.findNavController(it).navigate(
                HomeFragmentDirections.actionHomeFragmentToRandomMealDetailFragment(popularItemModel.idMeal.toString())
            )
        }
    }

    fun updateaPopularitem(list: List<MealsByCategory>){
        populatItemList.clear()
        populatItemList.addAll(list)
        notifyDataSetChanged()
    }
}