package com.andreschv.mvvmquote.domain

import com.andreschv.mvvmquote.data.QuoteRepository
import com.andreschv.mvvmquote.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(private val repository: QuoteRepository){

    suspend operator fun invoke(): Quote? {
        val quotes = repository.getAllQuotesFromDatabase()
        if (!quotes.isNullOrEmpty()) {
            val randomNumber: Int = (0..quotes.size - 1).random()
            return quotes[randomNumber]
        }
        return null
    }
}