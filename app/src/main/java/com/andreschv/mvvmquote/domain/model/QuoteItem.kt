package com.andreschv.mvvmquote.domain.model

import com.andreschv.mvvmquote.data.database.entities.QuoteEntity
import com.andreschv.mvvmquote.data.model.QuoteModel


data class Quote(val quote: String, val author: String)

fun QuoteModel.toDomain() = Quote(quote, author)
fun QuoteEntity.toDomain() = Quote(quote, author)
