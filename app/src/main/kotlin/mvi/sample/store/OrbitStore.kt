package mvi.sample.store

import kotlinx.coroutines.CoroutineScope
import mvi.sample.store.NumberPrinter
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

class OrbitStore(
    scope: CoroutineScope,
    initialNumber: Int = 0,
) : ContainerHost<Int, NumberPrinter> {
    override val container = scope.container<Int, NumberPrinter>(initialNumber)

    fun increase() = intent {
        reduce { state + 1 }
    }

    fun decrease() = intent {
        reduce { state - 1 }
    }

    fun printAsToast() = intent {
        postSideEffect(NumberPrinter.Toast(number = state))
    }
}
