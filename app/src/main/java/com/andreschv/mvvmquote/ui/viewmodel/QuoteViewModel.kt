package com.andreschv.mvvmquote.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andreschv.mvvmquote.data.model.QuoteModel
import com.andreschv.mvvmquote.domain.GetQuotesUseCase
import com.andreschv.mvvmquote.domain.GetRandomQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    val getQuotesUseCase: GetQuotesUseCase,
    val getRandomQuoteUseCase: GetRandomQuoteUseCase
) : ViewModel() {
    val quoteModel = MutableLiveData<QuoteModel>()
    var isLoading = MutableLiveData<Boolean>()

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