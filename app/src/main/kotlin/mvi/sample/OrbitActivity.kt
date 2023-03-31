package mvi.sample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mvi.sample.store.NumberPrinter
import mvi.sample.store.OrbitStore
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class OrbitActivity : ComponentActivity() {
    private val vm: OrbitStore by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            vm.collectSideEffect { sideEffect ->
                when (sideEffect) {
                    is NumberPrinter.Toast -> {
                        Toast.makeText(applicationContext, sideEffect.number.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }

            NumberUi(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp),
                number = vm.collectAsState().value,
                increase = vm::increase,
                decrease = vm::decrease,
                printAsToast = vm::printAsToast,
            )
        }
    }
}
