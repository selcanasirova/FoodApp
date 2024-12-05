package com.selcanasirova.foodapp.ui.fragments.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.selcanasirova.foodapp.ui.adapters.PopularItemAdapter
import com.selcanasirova.foodapp.databinding.FragmentHomeBinding
import com.selcanasirova.foodapp.ui.adapters.CategoriesAdapter
import com.selcanasirova.foodapp.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding ?= null
    private val binding get() = _binding!!
    private lateinit var homeMvvm:HomeViewModel
    private lateinit var popularMealAdapter:PopularItemAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter

    private var id= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeMvvm = ViewModelProvider(this@HomeFragment)[HomeViewModel::class.java]

        popularMealAdapter =  PopularItemAdapter(onClick = {})
        categoriesAdapter= CategoriesAdapter(onClick = {})
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()

        homeMvvm.getRandomMeal()
        observerRandomMeal()

        homeMvvm.getPopularMealItems()
        observerPopularItemLiveData()

        homeMvvm.getCategories()
        observerCategotyMeal()


        binding.randomMealCv.setOnClickListener(){
            findNavController().navigate(
                com.selcanasirova.foodapp.ui.fragments.page.HomeFragmentDirections.actionHomeFragmentToRandomMealDetailFragment(
                    id
                )
            )
        }
    }

    private fun observerCategotyMeal() {
        homeMvvm.observerCategoriesmealLiveData().observe(viewLifecycleOwner){category->
            categoriesAdapter.updateCategory(category)
        }
    }

    fun setRecycler(){
        binding.rvViewMealsPopular.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.rvViewMealsPopular.adapter = popularMealAdapter
        binding.rvViewCategories.layoutManager= GridLayoutManager(context,3)
        binding.rvViewCategories.adapter = categoriesAdapter
    }

    private fun observerPopularItemLiveData() {
        homeMvvm.observePopularMealLiveData().observe(viewLifecycleOwner) { mealList ->
            mealList?.let {
                popularMealAdapter.updateaPopularitem(list = it)
            } ?: run {
                // Boş siyahını adapterə ötür
                popularMealAdapter.updateaPopularitem(list = emptyList())
            }
        }
    }


    private fun observerRandomMeal() {
        homeMvvm.observeRandomMealLiveData().observe(viewLifecycleOwner
        ) { value ->
            Glide.with(this@HomeFragment)
                .load(value.strMealThumb)
                .into(binding.imgRandomMeal)

            id = value?.idMeal ?: ""
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}