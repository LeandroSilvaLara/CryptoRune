package com.leandrocourse.core.domain.usecase

import com.leandrocourse.core.domain.model.Exchange
import com.leandrocourse.core.domain.repository.MBCryptoRuneRepository

class SelectAllExchangesUseCase(
    private val repository: MBCryptoRuneRepository
) {

    suspend operator fun invoke(): List<Exchange> {
        return repository.selectAllExchanges()
    }
}
