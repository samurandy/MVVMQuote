package com.andreschv.mvvmquote.domain

import com.andreschv.mvvmquote.data.model.QuoteModel
import com.andreschv.mvvmquote.data.model.QuoteProvider
import com.andreschv.mvvmquote.data.QuoteRepository

class GetRandomQuoteUseCase {

    private val repository = QuoteRepository()

    operator fun invoke(): QuoteModel? {
        val quotes = QuoteProvider.quotes
        if (!quotes.isNullOrEmpty()) {
            val randomNumber: Int = (0..quotes.size - 1).random()
            return quotes[randomNumber]
        }
        return null
    }
}