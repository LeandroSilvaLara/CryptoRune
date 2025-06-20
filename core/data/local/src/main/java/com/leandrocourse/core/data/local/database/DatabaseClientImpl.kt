package com.leandrocourse.core.data.local.database

import android.content.Context
import androidx.room.Room
import com.leandrocourse.core.data.local.database.room.MBCryptoRuneDatabase

internal class DatabaseClientImpl(
    private val context: Context,
    private val databaseName: String
) : DatabaseClient<MBCryptoRuneDatabase> {

    override fun create(): MBCryptoRuneDatabase {
        return Room.databaseBuilder(
            context,
            MBCryptoRuneDatabase::class.java,
            databaseName
        ).build()
    }
}
