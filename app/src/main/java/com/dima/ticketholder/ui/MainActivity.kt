package com.dima.ticketholder.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commitNow
import androidx.lifecycle.ViewModelProvider
import com.dima.ticketholder.R
import com.dima.ticketholder.databinding.MainActivityBinding
import com.dima.ticketholder.ui.fragments.MainFragment
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MainActivityViewModel::class.java)
        val binding: MainActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.commitNow {
                replace(binding.container.id, MainFragment.newInstance())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.day_night_item) {
            viewModel.changeDayNightMode()
        }
        return super.onOptionsItemSelected(item)
    }
}
