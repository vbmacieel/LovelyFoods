package com.project.lovelyfoods.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.project.lovelyfoods.R
import com.project.lovelyfoods.databinding.ActivityFragmentBinding
import com.project.lovelyfoods.ui.fragment.ListFragment

class OptionsActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_FRAGMENT_CLASS = "extra.fragment.class"

        fun newIntentFragment(packageContext: Context, fragmentId: Int): Intent =
            Intent(packageContext, OptionsActivity::class.java)
                .putExtra(EXTRA_FRAGMENT_CLASS, fragmentId)
    }

    private val binding by lazy {
        ActivityFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val optionSelected = intent.getIntExtra(EXTRA_FRAGMENT_CLASS, 0)
        var fragment = supportFragmentManager.findFragmentById(binding.fragmentContainer.id)
        if (fragment == null) {
            fragment = createFragment(optionSelected)
            supportFragmentManager.beginTransaction()
                .add(binding.fragmentContainer.id, fragment)
                .commit()
        }
    }

    private fun createFragment(optionSelected: Int): Fragment {
        when (optionSelected) {
            R.id.foods_list -> return ListFragment.newInstance()
            else -> throw IllegalArgumentException("This fragment don't exist!")
        }
    }
}