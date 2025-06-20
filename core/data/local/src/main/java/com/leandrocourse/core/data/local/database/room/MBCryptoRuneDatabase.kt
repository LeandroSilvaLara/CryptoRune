package com.leandrocourse.core.data.local.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leandrocourse.core.data.local.dao.ExchangesDao
import com.leandrocourse.core.data.local.model.ExchangeEntity

@Database(entities = [ExchangeEntity::class], version = 1, exportSchema = false)
internal abstract class MBCryptoRuneDatabase : RoomDatabase() {
    abstract fun exchangesDao(): ExchangesDao
}