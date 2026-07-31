package com.example.myapplication.presentation.maintenance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.myapplication.domain.model.RiskLevel
import com.example.myapplication.domain.model.WorkOrderState
import com.example.myapplication.ui.components.EmptyView
import com.example.myapplication.ui.components.ErrorView
import com.example.myapplication.ui.components.LoadingView
import com.example.myapplication.ui.theme.AppTheme
import com.example.myapplication.ui.theme.OfflineRed
import com.example.myapplication.ui.theme.OnlineGreen

import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.compose.cartesian.data.columnSeries
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MaintenanceScreen(
    viewModel: MaintenanceViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = AppTheme.strings.maintenanceTitle,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when {
                uiState.isLoading -> LoadingView()
                uiState.error != null -> ErrorView(message = uiState.error ?: "Bilinmeyen hata")
                else -> {
                    SectionTitle(AppTheme.strings.dailyAlertCountTitle)

                    val modelProducer = remember { CartesianChartModelProducer() }

                    LaunchedEffect(uiState.dailyAlerts) {
                        modelProducer.runTransaction {
                            columnSeries {
                                series(uiState.dailyAlerts.map { it.count })
                            }
                        }
                    }

                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        CartesianChartHost(
                            chart = rememberCartesianChart(
                                rememberColumnCartesianLayer(),
                                startAxis = VerticalAxis.rememberStart(),
                                bottomAxis = HorizontalAxis.rememberBottom()
                            ),
                            modelProducer = modelProducer,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .padding(12.dp)
                        )
                    }

                    SectionTitle(AppTheme.strings.workOrdersTitle)

                    if (uiState.workOrders.isEmpty()) {
                        EmptyView(message = AppTheme.strings.noWorkOrdersMessage)
                    } else {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            uiState.workOrders.forEach { order ->
                                WorkOrderCard(order)
                            }
                        }
                    }

                    SectionTitle(AppTheme.strings.predictiveMaintenanceTitle)

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        uiState.predictions.forEach { prediction ->
                            PredictionCard(prediction)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
private fun WorkOrderCard(order: com.example.myapplication.domain.model.MaintenanceWorkOrder) {
    val stateLabel = when (order.state) {
        WorkOrderState.PLANNED -> AppTheme.strings.workOrderPlanned
        WorkOrderState.IN_PROGRESS -> AppTheme.strings.workOrderInProgress
        WorkOrderState.COMPLETED -> AppTheme.strings.workOrderCompleted
    }
    val stateColor = when (order.state) {
        WorkOrderState.PLANNED -> MaterialTheme.colorScheme.primary
        WorkOrderState.IN_PROGRESS -> Color(0xFFF59E0B)
        WorkOrderState.COMPLETED -> OnlineGreen
    }
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${AppTheme.strings.serialNumberLabel}: ${order.serialNumber}",
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(text = stateLabel, color = stateColor, fontWeight = FontWeight.Medium)
            }
            Text(
                text = "${AppTheme.strings.durationRatioLabel}: %${(order.durationRatio * 100).toInt()}",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "${AppTheme.strings.planningDateLabel}: ${order.planningDate}",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun PredictionCard(prediction: com.example.myapplication.domain.model.MaintenancePrediction) {
    val riskColor = when (prediction.riskLevel) {
        RiskLevel.HIGH -> OfflineRed
        RiskLevel.MEDIUM -> Color(0xFFF59E0B)
        RiskLevel.LOW -> OnlineGreen
    }
    val riskLabel = when (prediction.riskLevel) {
        RiskLevel.HIGH -> AppTheme.strings.riskHigh
        RiskLevel.MEDIUM -> AppTheme.strings.riskMedium
        RiskLevel.LOW -> AppTheme.strings.riskLow
    }
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${AppTheme.strings.machineLabel}: ${prediction.machineId}",
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(text = riskLabel, color = riskColor, fontWeight = FontWeight.Medium)
            }
            Text(
                text = "${AppTheme.strings.remainingLifeLabel}: ${prediction.remainingUsefulLifeDays} gün",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "${AppTheme.strings.predictedFailureLabel}: ${prediction.predictedFailureDate}",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(text = prediction.recommendation, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}