package com.leandrocourse.core.data.local.di

import com.leandrocourse.core.data.local.dao.ExchangesDao
import com.leandrocourse.core.data.local.database.DatabaseClient
import com.leandrocourse.core.data.local.database.DatabaseClientImpl
import com.leandrocourse.core.data.local.database.room.MBCryptoRuneDatabase
import com.leandrocourse.core.data.local.repository.LocalRepositoryImpl
import com.leandrocourse.core.data.local.source.LocalDataSourceImpl
import com.leandrocourse.core.domain.repository.MBCryptoRuneRepository
import com.leandrocourse.cryptorune.core.data.local.BuildConfig
import com.leandrocourse.libraries.arch.koin.koinshot.KoinShot
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

internal class LocalModuleShot : KoinShot() {

    override val dataModule: Module = module {
        single<DatabaseClient<MBCryptoRuneDatabase>> {
            DatabaseClientImpl(
                context = androidApplication(),
                databaseName = BuildConfig.DATABASE_NAME
            )
        }
        single<ExchangesDao> {
            get<DatabaseClient<MBCryptoRuneDatabase>>().create().exchangesDao()
        }


        factory<MBCryptoRuneRepository> {
            LocalRepositoryImpl(
                dataSource = LocalDataSourceImpl(dao = get()),
            )
        }
    }
}
