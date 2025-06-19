package com.leandrocourse.core.data.local.di


internal class LocalModuleShot : KoinShot() {

    override val dataModule: Module = module {
        single<DatabaseClient<MBChallengeDatabase>> {
            DatabaseClientImpl(
                context = androidApplication(),
                databaseName = BuildConfig.DATABASE_NAME
            )
        }
        single<ExchangesDao> {
            get<DatabaseClient<MBChallengeDatabase>>().create().exchangesDao()
        }


        factory<MBChallengeRepository> {
            LocalRepositoryImpl(
                dataSource = LocalDataSourceImpl(dao = get()),
            )
        }
    }
}
