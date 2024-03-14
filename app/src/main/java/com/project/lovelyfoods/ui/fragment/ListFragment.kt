package com.project.lovelyfoods.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.lovelyfoods.adapter.FoodsListAdapter
import com.project.lovelyfoods.databinding.FragmentListBinding
import com.project.lovelyfoods.viewmodel.FoodsListViewModel
import com.project.lovelyfoods.viewmodel.factory.FoodsViewModelFactory

class ListFragment : Fragment() {

    companion object {
        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }

    private val binding by lazy {
        FragmentListBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: FoodsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = FoodsViewModelFactory()
            .create(FoodsListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val adapter = FoodsListAdapter()
        binding.foodsListRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        viewModel.foodList.observe(viewLifecycleOwner) { foods ->
            adapter.foodsList = foods
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }

        viewModel.getAllFoods()
        return binding.root
    }
}