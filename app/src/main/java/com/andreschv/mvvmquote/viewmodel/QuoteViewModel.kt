package com.andreschv.mvvmquote.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andreschv.mvvmquote.model.QuoteModel
import com.andreschv.mvvmquote.model.QuoteProvider

class QuoteViewModel: ViewModel(){
    val quoteModel = MutableLiveData<QuoteModel>()

    fun randomQuote(){
        val currentQuote = QuoteProvider.random()
        quoteModel.postValue(currentQuote)
    }
}