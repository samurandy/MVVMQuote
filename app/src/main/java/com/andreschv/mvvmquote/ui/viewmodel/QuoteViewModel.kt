package com.andreschv.mvvmquote.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.andreschv.mvvmquote.domain.GetQuotesUseCase
import com.andreschv.mvvmquote.domain.GetRandomQuoteUseCase
import com.andreschv.mvvmquote.domain.model.Quote
import com.andreschv.mvvmquote.utils.ConnectivityLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    app: Application,
    private val getQuotesUseCase: GetQuotesUseCase,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase
) : AndroidViewModel(app) {
    val quoteModel = MutableLiveData<Quote>()
    var isLoading = MutableLiveData<Boolean>()
    val checkConnectivity: ConnectivityLiveData by lazy {
        ConnectivityLiveData(getApplication<Application>())
    }

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getQuotesUseCase()
            delay(1000) // Simulate a delay in order to show the progressbar

            if (!result.isNullOrEmpty()) {
                quoteModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    fun randomQuote() {
        viewModelScope.launch {
        isLoading.postValue(true)
        val quote = getRandomQuoteUseCase()
        if (quote != null) {
            quoteModel.postValue(quote) //Compiler error
        }
        isLoading.postValue(false)
        }
    }


}