package com.bisipaul.currencyconverter.components.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bisipaul.currencyconverter.R
import com.bisipaul.currencyconverter.components.error.ErrorActivity
import com.bisipaul.currencyconverter.components.noInternet.NoInternetActivity
import com.bisipaul.currencyconverter.components.rates.RatesAdapter
import com.bisipaul.currencyconverter.components.rates.RatesAdapterCallback
import com.bisipaul.currencyconverter.databinding.MainFragmentBinding
import com.bisipaul.currencyconverter.structure.BaseFragment
import com.bisipaul.currencyconverter.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment(), RatesAdapterCallback {

    private lateinit var binding: MainFragmentBinding
    private val ratesAdapter = RatesAdapter(this)

    private val viewModel by viewModels<MainViewModel>()

    @SuppressLint("ClickableViewAccessibility")
    private val touchListener = View.OnTouchListener { v, event ->
        if (event != null && event.action == MotionEvent.ACTION_MOVE) {
            hideKeyboard(v)
            v?.clearFocus()
        }
        false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getRates()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configView()
        observe()
    }

    private fun configView() {
        binding.ratesRV.apply {
            adapter = ratesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            setOnTouchListener(touchListener)
        }
    }

    private fun observe() = with(viewModel) {
        super.observe(viewModel)
        rates.observe {
            ratesAdapter.submitNewList(it)
        }

        navigateToError.observe {
            val intentToError = Intent()
            intentToError.setClassName(
                requireContext().packageName,
                ErrorActivity::class.java.name
            )
            intentToError.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            requireContext().startActivity(intentToError)
        }
    }

    override fun onCurrencySelected(newCurrency: String, newAmount: String) {
        viewModel.stopFetchingRates()
        viewModel.setNewBaseValues(newCurrency, newAmount)
        binding.ratesRV.scrollToPosition(0)
        viewModel.getRates()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}