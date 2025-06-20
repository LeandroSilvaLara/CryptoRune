package com.leandrocourse.features.details.di

import com.leandrocourse.core.domain.usecase.SelectExchangeUseCase
import com.leandrocourse.features.details.presentation.viewmodel.DetailsViewModel
import com.leandrocourse.libraries.arch.koin.koinload.KoinLoad
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

internal object DetailsModuleLoad : KoinLoad() {

    override val presentationModule: Module = module {
        viewModel { (exchangeId: String) ->
            DetailsViewModel(
                exchangeId = exchangeId,
                selectExchangeUseCase = SelectExchangeUseCase(repository = get())
            )
        }
    }
}