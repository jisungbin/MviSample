package mashup.study.mvi.orbitsample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NumberUi(
    modifier: Modifier = Modifier,
    number: Int,
    increase: () -> Unit,
    decrease: () -> Unit,
    printAsToast: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 30.dp,
            alignment = Alignment.CenterVertically,
        ),
    ) {
        ShowNumber(number = number)
        NumberController(
            increase = increase,
            decrease = decrease,
            printAsToast = printAsToast,
        )
    }
}

@Composable
private fun ShowNumber(
    modifier: Modifier = Modifier,
    number: Int,
) {
    Text(
        modifier = modifier,
        text = stringResource(R.string.main_currentnumber, number),
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
            Text(
                text = stringResource(R.string.numbercontroller_increase),
                fontSize = 11.sp,
            )
        }
        Button(
            modifier = Modifier.weight(1f),
            onClick = decrease,
        ) {
            Text(
                text = stringResource(R.string.numbercontroller_decrease),
                fontSize = 11.sp,
            )
        }
        Button(
            modifier = Modifier.weight(1f),
            onClick = printAsToast,
        ) {
            Text(
                text = stringResource(R.string.numbercontroller_print_as_toast),
                fontSize = 11.sp,
            )
        }
    }
}
