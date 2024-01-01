package com.niresh23.konvert.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SwapHoriz
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niresh23.konvert.model.Rate
import com.niresh23.konvert.ui.basic.SmallOutlinedTextField
import com.niresh23.konvert.ui.theme.AppTheme

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val listState = viewModel.ratesListFlow.collectAsState(initial = emptyList())
    Text("Converter")
    ConverterCard(
        leftValueChanged = viewModel::leftCurrencyChanged,
        rightValueChanged = viewModel::rightCurrencyChanged,
        swapClicked = viewModel::swapClicked
    )

    LazyColumn {
        items(listState.value) { rate ->

        }
    }
}

@Composable
fun ConverterCard(
    leftValueChanged: (String) -> Unit,
    rightValueChanged: (String) -> Unit,
    swapClicked: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CurrencyColumn(
                code = "RUB",
                value = 0.0f,
                onValueChanged = leftValueChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 12.dp, top = 5.dp, bottom = 5.dp)
            )
            Icon(
                imageVector = Icons.Rounded.SwapHoriz,
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        swapClicked.invoke()
                    }
                    .padding(5.dp),
            )
            CurrencyColumn(
                code = "USD",
                value = 0.0f,
                onValueChanged = rightValueChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 12.dp, top = 5.dp, bottom = 5.dp)
            )
        }
    }
}

@Composable
fun CurrencyColumn(code: String, value: Float, onValueChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = code
        )
        Spacer(modifier = Modifier.padding(10.dp))
        SmallOutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value.toString(),
            onValueChange = onValueChanged,
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedSupportingTextColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }
}

@Composable
fun RateItem(rate: Rate, modifier: Modifier = Modifier.fillMaxWidth()) {
    Row(
        modifier = modifier
    ) {
        Column {
            Text(text = rate.code)
            Text(text = rate.name)
        }


    }
}

@Preview
@Composable
fun PreviewConverterCard() {
    AppTheme {
        ConverterCard(leftValueChanged = fun(value: String) {}, rightValueChanged = fun(value: String) {}) {

        }
    }
}