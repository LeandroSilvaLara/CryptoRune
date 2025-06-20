package com.leandrocourse.libraries.testing.robot

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel

interface RobotArrange<RA : RobotAction<*>, VM : ViewModel> : RobotScreen {
    val subject: @Composable (VM) -> Unit
    val fakeViewModel: VM
}

inline fun <T : RobotArrange<RA, VM>, VM : ViewModel, reified RA : RobotAction<*>> T.givenArrange(
    noinline block: T.() -> Unit = {}
): RA {
    createComposeRule.setContent {
        subject(fakeViewModel)
    }
    this.apply(block)
    return RA::class.createInstance(createComposeRule)
}
