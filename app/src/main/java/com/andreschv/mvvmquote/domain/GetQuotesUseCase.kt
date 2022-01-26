package com.andreschv.mvvmquote.domain

import com.andreschv.mvvmquote.data.model.QuoteModel
import com.andreschv.mvvmquote.data.QuoteRepository
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(private val respository: QuoteRepository) {

    suspend operator fun invoke(): List<QuoteModel>? {
        return respository.getAllQuotes()
    }
}