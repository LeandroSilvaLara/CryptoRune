package com.leandrocourse.features.details.presentation.viewmodel

internal sealed interface DetailsViewIntent {
    data object OnInitView : DetailsViewIntent
    data object OnBackClicked : DetailsViewIntent
    data class OnNavigateToWebsite(val url: String) : DetailsViewIntent
}