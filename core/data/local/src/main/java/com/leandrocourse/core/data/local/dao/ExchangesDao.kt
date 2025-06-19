package com.leandrocourse.core.data.local.dao

@Dao
internal interface ExchangesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllExchanges(exchanges: List<ExchangeEntity>)

    @Query("SELECT * FROM exchange")
    fun selectAllExchanges(): List<ExchangeEntity>

    @Query("SELECT * FROM exchange WHERE exchange_id = :exchangeId")
    fun selectExchange(exchangeId: String): ExchangeEntity
}
