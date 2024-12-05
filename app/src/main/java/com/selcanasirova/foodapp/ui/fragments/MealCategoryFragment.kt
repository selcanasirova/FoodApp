package com.selcanasirova.foodapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.selcanasirova.foodapp.R
import com.selcanasirova.foodapp.data.MealsByCategory
import com.selcanasirova.foodapp.databinding.FragmentMealCategoryBinding
import com.selcanasirova.foodapp.ui.adapters.CategoriesAdapter
import com.selcanasirova.foodapp.ui.adapters.MealCategoryAdapter
import com.selcanasirova.foodapp.viewModel.CategoryViewModel

class MealCategoryFragment : Fragment() {
   private var _binding : FragmentMealCategoryBinding ?= null
   private val binding get() = _binding!!

   private lateinit var categoryMvvm: CategoryViewModel
   private lateinit var categoryAdapter: MealCategoryAdapter

   private val args: com.selcanasirova.foodapp.ui.fragments.MealCategoryFragmentArgs by navArgs()

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      categoryMvvm = ViewModelProvider(this)[CategoryViewModel::class.java]

      categoryAdapter = MealCategoryAdapter()

   }

   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View? {
      _binding= FragmentMealCategoryBinding.inflate(inflater,container,false)
      return binding.root
   }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      setRecyclerView()

      val mealCategory = args.category

      categoryMvvm.getMealsByCategory(mealCategory)
      observedMealsByCategory()
   }

   private fun setRecyclerView() {
      binding.rvMeals.adapter = categoryAdapter
      binding.rvMeals.layoutManager = GridLayoutManager(context,2)
   }

   private fun observedMealsByCategory() {
      categoryMvvm.observerMealsByCategoryLiveData().observe(viewLifecycleOwner){
         categoryAdapter.updateMealByCategory(list = it)
      }
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }


}