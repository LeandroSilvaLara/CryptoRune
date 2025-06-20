package com.leandrocourse.libraries.design.theme.defaults.light

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.leandrocourse.libraries.design.theme.tokens.PlutoText
import com.leandrocourse.libraries.design.theme.tokens.onBackgroundLight
import com.leandrocourse.libraries.design.theme.tokens.primaryLight
import com.leandrocourse.libraries.design.theme.tokens.secondaryLight

@Immutable
object PlutoTextLight : PlutoText {
    override val colorPrimary: Color = primaryLight
    override val colorSecondary: Color = secondaryLight
    override val colorPlaceholder: Color = onBackgroundLight.copy(alpha = 0.7f)
}