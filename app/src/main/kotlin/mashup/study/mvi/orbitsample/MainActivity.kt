package mashup.study.mvi.orbitsample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import mashup.study.mvi.orbitsample.state.NumberPrinter
import mashup.study.mvi.orbitsample.state.NumberStore
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val coroutineScope = rememberCoroutineScope()
            val numberStore = remember { NumberStore(scope = coroutineScope) }

            numberStore.collectSideEffect { sideEffect ->
                when (sideEffect) {
                    is NumberPrinter.Toast -> {
                        Toast.makeText(applicationContext, sideEffect.number.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    space = 30.dp,
                    alignment = Alignment.CenterVertically,
                ),
            ) {
                ShowNumber(number = numberStore.collectAsState().value)
                NumberController(
                    increase = numberStore::increase,
                    decrease = numberStore::decrease,
                    printAsToast = numberStore::printAsToast,
                )
            }
        }
    }
}

@Composable
private fun ShowNumber(
    modifier: Modifier = Modifier,
    number: Int,
) {
    Text(
        modifier = modifier,
        text = "Current number: $number",
    )
}

@Composable
private fun NumberController(
    modifier: Modifier = Modifier,
    increase: () -> Unit,
    decrease: () -> Unit,
    printAsToast: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Button(
            modifier = Modifier.weight(1f),
            onClick = increase,
        ) {
            Text(text = stringResource(R.string.numbercontroller_increase))
        }
        Button(
            modifier = Modifier.weight(1f),
            onClick = decrease,
        ) {
            Text(text = stringResource(R.string.numbercontroller_decrease))
        }
        Button(
            modifier = Modifier.weight(1f),
            onClick = printAsToast,
        ) {
            Text(text = stringResource(R.string.numbercontroller_print_as_toast))
        }
    }
}
