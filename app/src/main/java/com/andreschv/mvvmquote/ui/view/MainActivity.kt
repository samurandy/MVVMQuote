package com.andreschv.mvvmquote.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.andreschv.mvvmquote.databinding.ActivityMainBinding
import com.andreschv.mvvmquote.ui.viewmodel.QuoteViewModel
import com.andreschv.mvvmquote.utils.ConnectivityLiveData
import com.andreschv.mvvmquote.utils.Extensions.Companion.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var connectivityLiveData: ConnectivityLiveData
    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityLiveData = ConnectivityLiveData(application)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialConnectionCheck()
        onConnectionObserve()


    }

    private fun observeAll() {
        quoteViewModel.quoteModel.observe(this, { currentQuote -> //instead of it by default
            binding.tvQuote.text = currentQuote.quote
            binding.tvAuthor.text = currentQuote.author
        })

        quoteViewModel.isLoading.observe(this, {
            binding.progress.isVisible = it
        })

        binding.viewContainer.setOnClickListener {
            quoteViewModel.randomQuote()
        }
    }

    private fun initialConnectionCheck() {
        lifecycleScope.launch {
            delay(1500)
            if (quoteViewModel.checkConnectivity.value == true) {

                quoteViewModel.onCreate()

            } else {
                toast("No network connection available")
            }
        }
    }

    private fun onConnectionObserve() {
        quoteViewModel.checkConnectivity.observe(this, { isAvailable ->
            when (isAvailable) {
                true -> {
                    lifecycleScope.launch {
                        observeAll()
                    }
                }
                false -> toast("No network connection available")
            }
        })
    }
}