package com.andreschv.mvvmquote.domain

import com.andreschv.mvvmquote.data.model.QuoteModel
import com.andreschv.mvvmquote.data.QuoteRepository

class GetQuotesUseCase {
    private val respository = QuoteRepository()
    suspend operator fun invoke(): List<QuoteModel>? {
        return respository.getAllQuotes()
    }
}