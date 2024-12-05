package com.selcanasirova.foodapp.ui.fragments.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.selcanasirova.foodapp.R
import com.selcanasirova.foodapp.databinding.FragmentRandomMealDetailBinding
import com.selcanasirova.foodapp.viewModel.MealViewModel

class RandomMealDetailFragment : Fragment() {
    private var _binding: FragmentRandomMealDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var mealMvvm: MealViewModel
    private val args: RandomMealDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRandomMealDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mealMvvm = ViewModelProvider(this@RandomMealDetailFragment)[MealViewModel::class.java]


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observedRandomMeal()
        val meal = args.id
        mealMvvm.getMealDetail(meal)

    }

    private fun observedRandomMeal() {
        mealMvvm.observeMealDetailsLiveData().observe(viewLifecycleOwner) { value ->
            Glide.with(requireContext()).load(value.strMealThumb).placeholder(R.color.g_black)
                .into(binding.imgMealDetail)
            binding.apply {
                collasingToolbar.title = value.strMeal
                category.text = value.strCategory
                mealText.text = value.strInstructions
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}