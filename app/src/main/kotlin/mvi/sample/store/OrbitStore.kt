package mvi.sample.store

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class OrbitStore : ContainerHost<Int, NumberPrinter>, ViewModel() {
    override val container = container<Int, NumberPrinter>(0)

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
