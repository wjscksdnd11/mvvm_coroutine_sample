package com.jeon.listsample.ui.wheather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jeon.listsample.R
import com.jeon.listsample.ViewModelFactory
import com.jeon.listsample.databinding.WeatherDetailFragmentBinding

class WeatherDetailFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherDetailFragment()
    }

    private lateinit var binding: WeatherDetailFragmentBinding
    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.weather_detail_fragment, container, false)
        return inflater.inflate(R.layout.weather_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.also {
            viewModel = ViewModelProvider(
                it.viewModelStore,
                ViewModelFactory()
            ).get(WeatherViewModel::class.java)
            binding.viewModel = viewModel
        }

    }

}
