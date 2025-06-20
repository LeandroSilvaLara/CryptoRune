package com.leandrocourse.core.data.remote.network.exception.extension

import com.leandrocourse.core.data.remote.model.ErrorType
import com.leandrocourse.core.data.remote.network.exception.model.HttpThrowable
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


private const val ERROR_CODE_BAD_REQUEST = 400
private const val ERROR_CODE_UNAUTHORIZED = 401
private const val ERROR_CODE_FORBIDDEN = 403
private const val ERROR_CODE_TOO_MANY_REQUESTS = 429
private const val ERROR_CODE_NO_DATA = 550

private fun HttpException.parseError(): Throwable {
    val errorType = when (this.code()) {
        ERROR_CODE_BAD_REQUEST -> ErrorType.BadRequest
        ERROR_CODE_UNAUTHORIZED -> ErrorType.Unauthorized
        ERROR_CODE_FORBIDDEN -> ErrorType.Forbidden
        ERROR_CODE_TOO_MANY_REQUESTS -> ErrorType.TooManyRequests
        ERROR_CODE_NO_DATA -> ErrorType.NoData
        else -> ErrorType.Generic
    }
    return HttpThrowable(
        errorType = errorType,
        message = message,
        throwable = this
    )
}

fun Throwable.parseHttpError(): Throwable =
    when (this) {
        is HttpException -> parseError()
        is UnknownHostException,
        is SocketTimeoutException,
        is IOException -> HttpThrowable(
            errorType = ErrorType.Internet,
            message = message,
            throwable = this
        )

        else -> this
    }
