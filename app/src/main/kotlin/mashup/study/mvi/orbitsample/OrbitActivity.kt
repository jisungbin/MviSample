package mashup.study.mvi.orbitsample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mashup.study.mvi.orbitsample.store.NumberPrinter
import mashup.study.mvi.orbitsample.store.OrbitStore
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class OrbitActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val coroutineScope = rememberCoroutineScope()
            val orbitStore = remember { OrbitStore(scope = coroutineScope) }

            orbitStore.collectSideEffect { sideEffect ->
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
                number = orbitStore.collectAsState().value,
                increase = orbitStore::increase,
                decrease = orbitStore::decrease,
                printAsToast = orbitStore::printAsToast,
            )
        }
    }
}
