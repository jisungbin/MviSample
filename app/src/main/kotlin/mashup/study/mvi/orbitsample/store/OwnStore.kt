package mashup.study.mvi.orbitsample.store

import androidx.lifecycle.ViewModel
import mashup.study.mvi.orbitsample.own.MviContainer
import mashup.study.mvi.orbitsample.own.Store
import mashup.study.mvi.orbitsample.own.intent
import mashup.study.mvi.orbitsample.own.store

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
