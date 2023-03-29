package mashup.study.mvi.orbitsample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mashup.study.mvi.orbitsample.own.collectAsState
import mashup.study.mvi.orbitsample.own.collectSideEffect
import mashup.study.mvi.orbitsample.store.NumberPrinter
import mashup.study.mvi.orbitsample.store.OwnStore

class OwnActivity : ComponentActivity() {
    private val ownStore: OwnStore by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ownStore.collectSideEffect { sideEffect ->
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
                number = ownStore.collectAsState().value,
                increase = ownStore::increase,
                decrease = ownStore::decrease,
                printAsToast = ownStore::printAsToast,
            )
        }
    }
}
