package mvi.sample.store

sealed class NumberPrinter {
    class Toast(val number: Int) : NumberPrinter()
}
