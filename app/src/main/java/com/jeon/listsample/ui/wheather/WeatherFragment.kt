package com.jeon.listsample.ui.wheather

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
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
            viewModel.headerData.observe(this, Observer { weather ->
                weather?.also { data ->
                    binding.headerData = data
                }
            })
            viewModel.loading.observe(this, Observer { isShow ->
                pr_loading.visibility = if (isShow) View.VISIBLE else View.GONE
            })
            viewModel.weatherList.observe(this, Observer { list ->
                if (recyclerview.adapter == null) {
                    recyclerview.adapter = WeatherAdapter(viewModel)
                    recyclerview.itemAnimator = DefaultItemAnimator()
                }
                (recyclerview.adapter as? WeatherAdapter)?.submitList(list)
            })
            viewModel.errorMessage.observe(this, Observer { msg ->
                Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
                AlertDialog.Builder(activity)
                    .setMessage(msg)
                    .setNegativeButton("종료") { dialog, _ ->
                        dialog.dismiss()
                        dialog.dismiss()
                        activity?.finish()
                    }
                    .setPositiveButton("재시도")
                    { dialog, _ ->
                        viewModel.loadData()
                        dialog.dismiss()
                    }
                    .show()
            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            WeatherFragment()
    }
}
