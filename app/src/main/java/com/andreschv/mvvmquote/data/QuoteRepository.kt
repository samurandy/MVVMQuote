package com.andreschv.mvvmquote.data

import com.andreschv.mvvmquote.data.database.dao.QuoteDao
import com.andreschv.mvvmquote.data.database.entities.QuoteEntity
import com.andreschv.mvvmquote.data.model.QuoteModel
import com.andreschv.mvvmquote.data.network.QuoteService
import com.andreschv.mvvmquote.domain.model.Quote
import com.andreschv.mvvmquote.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao
) {

    suspend fun getAllQuotesFromApi(): List<Quote> {
        val response: List<QuoteModel> = api.getQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase(): List<Quote>{
        val response: List<QuoteEntity> = quoteDao.getAllQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes:List<QuoteEntity>){
        quoteDao.insertAllQuotes(quotes)
    }

    suspend fun clearQuotes() {
        quoteDao.deleteAllQuotes()
    }
}