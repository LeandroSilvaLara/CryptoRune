package com.leandrocourse.libraries.design.theme.defaults.dark

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.leandrocourse.libraries.design.theme.tokens.PlutoColors
import com.leandrocourse.libraries.design.theme.tokens.blueLinkDark
import com.leandrocourse.libraries.design.theme.tokens.stealthGrayDark

@Immutable
object PlutoColorsDark : PlutoColors {
    override val stealthGray: Color = stealthGrayDark
    override val blueLink: Color = blueLinkDark
}
