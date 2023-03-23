package mashup.study.mvi.orbitsample.state

sealed class NumberPrinter {
    class Toast(val number: Int) : NumberPrinter()
}
