package com.leandrocourse.libraries.testing.robot

interface RobotAction<RC : RobotCheck> : RobotScreen

inline fun <T : RobotAction<RC>, reified RC : RobotCheck> T.whenAction(
    noinline block: T.() -> Unit = {}
): RC {
    this.apply(block)
    return RC::class.createInstance(createComposeRule)
}
