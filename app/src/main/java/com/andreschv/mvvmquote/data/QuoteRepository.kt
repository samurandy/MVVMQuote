package com.andreschv.mvvmquote.data

import com.andreschv.mvvmquote.data.model.QuoteModel
import com.andreschv.mvvmquote.data.model.QuoteProvider
import com.andreschv.mvvmquote.data.network.QuoteService

class QuoteRepository {
    private val api = QuoteService()

    suspend fun getAllQuotes(): List<QuoteModel> {
        val response = api.getQuotes()
        QuoteProvider.quotes = response
        return response
    }
}