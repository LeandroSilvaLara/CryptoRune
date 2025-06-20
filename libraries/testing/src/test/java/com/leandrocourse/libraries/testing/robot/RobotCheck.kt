package com.leandrocourse.libraries.testing.robot

interface RobotCheck : RobotScreen

fun <T : RobotCheck> T.thenCheck(block: T.() -> Unit): T {
    return this.apply(block)
}
