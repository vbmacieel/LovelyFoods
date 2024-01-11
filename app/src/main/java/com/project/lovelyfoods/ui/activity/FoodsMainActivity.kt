package com.project.lovelyfoods.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.project.lovelyfoods.R
import com.project.lovelyfoods.databinding.ActivityFoodsMainBinding
import com.project.lovelyfoods.ui.fragment.dialog.FoodsFormDialog
import com.project.lovelyfoods.viewmodel.factory.FoodsViewModelFactory
import com.project.lovelyfoods.viewmodel.FoodsGenerateViewModel

class FoodsMainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFoodsMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        FoodsViewModelFactory().create(FoodsGenerateViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnNewFood.setOnClickListener {
            val formDialog = FoodsFormDialog()
            formDialog.show(supportFragmentManager, "formFragment")
        }

        binding.btnGenerateFood.setOnClickListener {
            try {
                viewModel.generateRandomFood()
                createDialogWithFood()
            } catch (_: Exception) {
                viewModel.errorMessage.observe(this) {error ->
                    Toast.makeText(this,
                        error,
                        Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun createDialogWithFood() {
        viewModel.food.observe(this) { foodResult ->
            val builder = AlertDialog.Builder(this@FoodsMainActivity)
            builder.setTitle("Generated food!")
            builder.setMessage(foodResult.name)
            builder.setPositiveButton("Go to video") { _, _ ->
                createIntentToShowVideo(foodResult.url)
            }
            builder.create().show()
        }
    }

    private fun createIntentToShowVideo(foodUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(foodUrl))
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val startActivityIntent = FoodsOptionsActivity.newIntentFragment(this, item.itemId)
        startActivity(startActivityIntent)
        return true
    }
}