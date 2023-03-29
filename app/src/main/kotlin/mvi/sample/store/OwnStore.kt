package mvi.sample.store

import androidx.lifecycle.ViewModel
import mvi.sample.own.MviContainer
import mvi.sample.own.Store
import mvi.sample.own.intent
import mvi.sample.own.store

class OwnStore : ViewModel(), MviContainer<Int, NumberPrinter> {
    override val store: Store<Int, NumberPrinter> = store(initialState = 0)

    fun increase() = intent {
        reduce { currentState + 1 }
    }

    fun decrease() = intent {
        reduce { currentState - 1 }
    }

    fun printAsToast() = intent {
        postSideEffect(NumberPrinter.Toast(number = currentState))
    }
}
