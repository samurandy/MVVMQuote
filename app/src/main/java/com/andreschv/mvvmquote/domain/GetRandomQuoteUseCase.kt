package com.andreschv.mvvmquote.domain

import com.andreschv.mvvmquote.data.model.QuoteModel
import com.andreschv.mvvmquote.data.model.QuoteProvider
import com.andreschv.mvvmquote.data.QuoteRepository
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(private val quoteProvider: QuoteProvider){

    operator fun invoke(): QuoteModel? {
        val quotes = quoteProvider.quotes
        if (!quotes.isNullOrEmpty()) {
            val randomNumber: Int = (0..quotes.size - 1).random()
            return quotes[randomNumber]
        }
        return null
    }
}