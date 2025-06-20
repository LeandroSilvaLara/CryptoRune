package com.leandrocourse.libraries.arch.navigation

import androidx.navigation.NavGraphBuilder

interface FeatureRoute {
    fun register(navGraphBuilder: NavGraphBuilder)
}