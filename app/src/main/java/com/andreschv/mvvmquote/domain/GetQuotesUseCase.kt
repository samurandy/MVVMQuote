package com.andreschv.mvvmquote.domain

import com.andreschv.mvvmquote.data.model.QuoteModel
import com.andreschv.mvvmquote.data.QuoteRepository
import com.andreschv.mvvmquote.data.database.entities.toDatabase
import com.andreschv.mvvmquote.domain.model.Quote
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(private val repository: QuoteRepository) {

    suspend operator fun invoke(): List<Quote>? {
        val quotes = repository.getAllQuotesFromApi()

        return if (quotes.isNotEmpty()){
            repository.clearQuotes()
            repository.insertQuotes(quotes.map { it.toDatabase() })
            quotes
        }else{
            repository.getAllQuotesFromDatabase()
        }

    }
}