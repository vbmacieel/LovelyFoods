package com.project.lovelyfoods.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.lovelyfoods.databinding.FragmentFoodsDetailBinding
import com.project.lovelyfoods.model.Food
import com.project.lovelyfoods.viewmodel.FoodsDetailViewModel
import com.project.lovelyfoods.viewmodel.factory.FoodsViewModelFactory

class FoodsDetailFragment: Fragment() {

    companion object {
        fun newInstance(foodId: String): FoodsDetailFragment {
            val args = Bundle()
            args.putString(foodId, "foodId")
            val fragment = FoodsDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val viewModel by lazy {
        FoodsViewModelFactory().create(FoodsDetailViewModel::class.java)
    }

    private val binding by lazy {
        FragmentFoodsDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val foodId = arguments?.getString("foodId")
        if (foodId != null) {
            viewModel.getFood(foodId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel.food.observe(viewLifecycleOwner) {food ->
            binding.apply {
                this.foodName.text = food.name
                this.foodUrl.text = food.url

                this.deleteFood.setOnClickListener {
                    viewModel.getFood(food.id)
                }

                this.favoriteFood.setOnClickListener {
                    TODO("Ajustar adicionar aos favoritos")
                }

                this.editFood.setOnClickListener {
                    TODO("Ajustar edição da comidinha")
                }
            }
        }


        return binding.root
    }
}