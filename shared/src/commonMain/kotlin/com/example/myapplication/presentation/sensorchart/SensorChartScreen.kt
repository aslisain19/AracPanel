package com.example.myapplication.presentation.sensorchart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.components.EmptyView
import com.example.myapplication.ui.components.ErrorView
import com.example.myapplication.ui.components.LoadingView
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.compose.cartesian.data.lineModel
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import com.example.myapplication.ui.theme.AppTheme

@Composable
fun SensorChartScreen(
    machineId: String,
    viewModel: SensorChartViewModel = koinViewModel(
        parameters = { parametersOf(machineId) }
    )
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
            .padding(16.dp)
    ) {
        when {
            uiState.isLoading -> LoadingView()
            uiState.error != null -> ErrorView(message = uiState.error ?: "Bilinmeyen hata")
            uiState.readings.isEmpty() -> EmptyView(message = AppTheme.strings.noSensorDataMessage)
            else -> {
                Text(text = AppTheme.strings.temperatureChartTitle)

                val modelProducer = remember { CartesianChartModelProducer() }

                LaunchedEffect(uiState.readings) {
                    modelProducer.runTransaction {
                        lineModel {
                            series(uiState.readings.map { it.temperature })
                        }
                    }
                }

                CartesianChartHost(
                    chart = rememberCartesianChart(
                        rememberLineCartesianLayer(),
                        startAxis = VerticalAxis.rememberStart(),
                        bottomAxis = HorizontalAxis.rememberBottom()
                    ),
                    modelProducer = modelProducer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                )
            }
        }
    }
}