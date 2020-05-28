package com.jeon.listsample

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jeon.listsample.ui.wheather.WeatherDetailFragment
import com.jeon.listsample.ui.wheather.WeatherFragment
import com.jeon.listsample.ui.wheather.WeatherViewModel
import com.jeon.listsample.utils.LocationUtils

class MainActivity : BaseActivity() {
    val viewModel by lazy {
        ViewModelProvider(
            this.viewModelStore,
            ViewModelFactory()
        ).get(WeatherViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPermissionGranted() {
        LocationUtils.setLocation(this.applicationContext)
        moveFragment(WeatherFragment.newInstance())
        viewModel.detailWeather.observe(this, Observer {
            moveFragment(WeatherDetailFragment.newInstance())
        })
    }

    private fun moveFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            if(fragment !is WeatherFragment){
                addToBackStack("tag")
            }
            commit()
        }

    }


}
