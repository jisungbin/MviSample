package mashup.study.mvi.orbitsample.store

sealed class NumberPrinter {
    class Toast(val number: Int) : NumberPrinter()
}
