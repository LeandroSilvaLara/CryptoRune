package com.leandrocourse.core.data.local.database.room

@Database(entities = [ExchangeEntity::class], version = 1, exportSchema = false)
internal abstract class MBCryptoRuneDatabase : RoomDatabase() {
    abstract fun exchangesDao(): ExchangesDao
}