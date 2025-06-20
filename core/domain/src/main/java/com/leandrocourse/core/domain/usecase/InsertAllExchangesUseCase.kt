package com.leandrocourse.core.domain.usecase

import com.leandrocourse.core.domain.model.Exchange
import com.leandrocourse.core.domain.repository.MBCryptoRuneRepository

class InsertAllExchangesUseCase(
    private val repository: MBCryptoRuneRepository
) {

    suspend operator fun invoke(exchanges: List<Exchange>) {
        repository.insertAllExchanges(exchanges)
    }
}
