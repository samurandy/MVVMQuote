package com.andreschv.mvvmquote.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andreschv.mvvmquote.data.database.dao.QuoteDao
import com.andreschv.mvvmquote.data.database.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDatabase: RoomDatabase() {

    abstract fun getQuoteDAO(): QuoteDao
}