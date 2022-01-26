package com.andreschv.mvvmquote.data

import com.andreschv.mvvmquote.data.model.QuoteModel
import com.andreschv.mvvmquote.data.model.QuoteProvider
import com.andreschv.mvvmquote.data.network.QuoteService
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val api: QuoteService, private val quoteProvider: QuoteProvider){


    suspend fun getAllQuotes(): List<QuoteModel> {
        val response = api.getQuotes()
        quoteProvider.quotes = response
        return response
    }
}