package com.project.lovelyfoods.ui.fragment.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.project.lovelyfoods.databinding.FragmentFoodsFormBinding
import com.project.lovelyfoods.viewmodel.factory.FoodsViewModelFactory
import com.project.lovelyfoods.viewmodel.FoodsFormViewModel

class FoodsFormDialog : DialogFragment() {

    private val binding by lazy {
        FragmentFoodsFormBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        FoodsViewModelFactory().create(FoodsFormViewModel::class.java)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            builder.setTitle("Save new video")
                .setView(binding.root)
                .setPositiveButton("Save") { _, _ ->
                    saveNewFood(it)
                }
                .setNegativeButton("Cancel") { _, _ ->
                    dismiss()
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null!")
    }

    private fun saveNewFood(context: Context) {
        val foodName = binding.textFoodName.editText?.text.toString()
        val foodUrl = binding.textFoodUrl.editText?.text.toString()

        if (foodName.isBlank() || foodUrl.isBlank()) {
            Toast.makeText(context, "Neither labels should be blank!", Toast.LENGTH_LONG).show()
        } else {
            viewModel.addNewFood(foodName, foodUrl, onSuccess = {
                Toast.makeText(context, "$foodName saved!", Toast.LENGTH_LONG).show()
            }, onFailure = { exception ->
                Toast.makeText(context, exception.message, Toast.LENGTH_LONG).show()
            })
        }
    }
}