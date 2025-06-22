package com.leandrocourse.features.exchanges.di

import com.leandrocourse.core.data.remote.di.COIN_API_HTTP_CLIENT_QUALIFIER
import com.leandrocourse.core.data.remote.network.HttpClient
import com.leandrocourse.core.domain.usecase.InsertAllExchangesUseCase
import com.leandrocourse.core.domain.usecase.SelectAllExchangesUseCase
import com.leandrocourse.features.exchanges.data.api.CoinAPIService
import com.leandrocourse.features.exchanges.data.repository.ExchangesRepositoryImpl
import com.leandrocourse.features.exchanges.data.source.ExchangesRemoteDataSourceImpl
import com.leandrocourse.features.exchanges.domain.repository.ExchangesRepository
import com.leandrocourse.features.exchanges.domain.usecase.GetExchangesUseCase
import com.leandrocourse.features.exchanges.presentation.viewmodel.ExchangesViewModel
import com.leandrocourse.libraries.arch.koin.koinload.KoinLoad
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module


internal object ExchangesModuleLoad : KoinLoad() {

    override val dataModule: Module = module {
        factory<ExchangesRepository> {
            ExchangesRepositoryImpl(
                ExchangesRemoteDataSourceImpl(
                    service = get<HttpClient>(
                        named(COIN_API_HTTP_CLIENT_QUALIFIER)
                    ).create(CoinAPIService::class.java),
                )
            )
        }
    }

    override val presentationModule: Module = module {
        viewModel {
            ExchangesViewModel(
                insertAllExchangesUseCase = InsertAllExchangesUseCase(repository = get()),
                selectAllExchangesUseCase = SelectAllExchangesUseCase(repository = get()),
                getExchangesUseCase = GetExchangesUseCase(repository = get())
            )
        }
    }
}
