package com.leandrocourse.features.exchanges.domain.usecase

import com.leandrocourse.core.domain.model.Exchange
import com.leandrocourse.features.exchanges.domain.repository.ExchangesRepository

internal class GetExchangesUseCase(
    private val repository: ExchangesRepository
) {

    suspend operator fun invoke(): List<Exchange> {
        return repository.getExchanges()
    }
}
