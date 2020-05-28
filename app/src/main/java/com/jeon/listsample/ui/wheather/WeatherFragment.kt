package com.jeon.listsample.ui.wheather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jeon.listsample.R
import com.jeon.listsample.ViewModelFactory
import com.jeon.listsample.databinding.FragmentWeatherBinding
import kotlinx.android.synthetic.main.fragment_weather.*

class WeatherFragment : Fragment() {
    private lateinit var viewModel: WeatherViewModel
    private lateinit var binding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.also {
            viewModel = ViewModelProvider(
                it.viewModelStore,
                ViewModelFactory()
            ).get(WeatherViewModel::class.java)
            binding.viewModel = viewModel
            viewModel.headerData.observe(this, Observer {weather->
                weather?.also { data->
                    binding.headerData =data
                }
            })
            viewModel.weatherList.observe(this, Observer {list->
                if (recyclerview.adapter == null) {
                    recyclerview.adapter = WeatherAdapter(viewModel)
                }
                (recyclerview.adapter as? WeatherAdapter)?.submitList(list)
            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            WeatherFragment()
    }
}
