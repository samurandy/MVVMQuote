package com.andreschv.mvvmquote.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreschv.mvvmquote.data.model.QuoteModel
import com.andreschv.mvvmquote.domain.GetQuotesUseCase
import com.andreschv.mvvmquote.domain.GetRandomQuoteUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.NullPointerException
import java.util.logging.Handler

class QuoteViewModel : ViewModel() {
    val quoteModel = MutableLiveData<QuoteModel>()
    var isLoading = MutableLiveData<Boolean>()

    var getQuotesUseCase = GetQuotesUseCase()
    var getRandomQuoteUseCase = GetRandomQuoteUseCase()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result: List<QuoteModel>? = getQuotesUseCase()
            delay(1000) // Simulate a delay in order to show the progressbar

            if (!result.isNullOrEmpty()) {
                quoteModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    fun randomQuote() {
        isLoading.postValue(true)
        val quote: QuoteModel? = getRandomQuoteUseCase()
        if (quote != null) {
            quoteModel.postValue(quote) //Compiler error
        }
        isLoading.postValue(false)

    }


}