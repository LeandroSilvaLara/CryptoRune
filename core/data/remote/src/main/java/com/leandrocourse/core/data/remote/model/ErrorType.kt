package com.leandrocourse.core.data.remote.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.leandrocourse.cryptorune.core.data.remote.R


enum class ErrorType(
    @DrawableRes val illuRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val messageRes: Int,
) {
    Internet(
        illuRes = com.leandrocourse.cryptorune.libraries.design.R.drawable.illu_error_connection,
        titleRes = R.string.failure_internet_title,
        messageRes = R.string.failure_internet_message,
    ),
    BadRequest(
        illuRes = com.leandrocourse.cryptorune.libraries.design.R.drawable.illu_error_generic,
        titleRes = R.string.failure_bad_request_title,
        messageRes = R.string.failure_bad_request_message,
    ),
    Unauthorized(
        illuRes = com.leandrocourse.cryptorune.libraries.design.R.drawable.illu_error_analyze,
        titleRes = R.string.failure_unauthorized_title,
        messageRes = R.string.failure_unauthorized_message,
    ),
    Forbidden(
        illuRes = com.leandrocourse.cryptorune.libraries.design.R.drawable.illu_error_generic,
        titleRes = R.string.failure_forbidden_title,
        messageRes = R.string.failure_forbidden_message,
    ),
    TooManyRequests(
        illuRes = com.leandrocourse.cryptorune.libraries.design.R.drawable.illu_error_generic,
        titleRes = R.string.failure_too_many_requests_title,
        messageRes = R.string.failure_too_many_requests_message,
    ),
    NoData(
        illuRes = com.leandrocourse.cryptorune.libraries.design.R.drawable.illu_error_generic,
        titleRes = R.string.failure_no_data_title,
        messageRes = R.string.failure_no_data_message,
    ),
    Generic(
        illuRes = com.leandrocourse.cryptorune.libraries.design.R.drawable.illu_error_generic,
        titleRes = R.string.failure_generic_title,
        messageRes = R.string.failure_generic_message,
    )
}
