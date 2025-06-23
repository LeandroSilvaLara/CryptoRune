package com.leandrocourse.core.domain.usecase

import com.leandrocourse.core.domain.exchangeStub.exchangeStub
import com.leandrocourse.core.domain.repository.MBCryptoRuneRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertFailsWith


internal class SelectExchangeUseCaseTest {

    private val repository: MBCryptoRuneRepository = mockk()
    private val subject = SelectExchangeUseCase(repository)

    @Test
    fun `When selectExchange is invoked Then should return success`() =
        runTest {
            // Given
            val exchangeId = "MERCADOBITCOIN"
            val expectedExchange = exchangeStub()
            coEvery { repository.selectExchange(exchangeId) } returns expectedExchange

            // When
            val result = subject(exchangeId)

            // Then
            assertEquals(expectedExchange, result)
            coVerify { repository.selectExchange(exchangeId) }
        }

    @Test
    fun `When selectExchange is invoked Then should return failure`() =
        runTest {
            // Given
            val exchangeId = "MERCADOBITCOIN"
            val expectedMessage = "Test exception"
            val cause = Throwable(expectedMessage)
            coEvery { repository.selectExchange(exchangeId) } throws cause

            // Then
            val result = assertFailsWith<Throwable> { subject(exchangeId) }
            assertEquals(expectedMessage, result.message)
            coVerify { repository.selectExchange(exchangeId) }
        }
}
