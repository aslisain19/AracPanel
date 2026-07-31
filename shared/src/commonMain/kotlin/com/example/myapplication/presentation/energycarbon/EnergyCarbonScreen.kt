package com.example.myapplication.presentation.energycarbon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.domain.model.EnergyCarbonSummary
import com.example.myapplication.domain.model.EnergyPeriod
import com.example.myapplication.domain.model.EnergySource
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
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.compose.cartesian.data.columnSeries
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.data.ExtraStore
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.roundToInt
import com.example.myapplication.domain.model.MaintenanceImpact
import com.example.myapplication.domain.model.TirePressureStatus

private val energyLabelsKey = ExtraStore.Key<List<String>>()

@Composable
fun EnergyCarbonScreen(
    viewModel: EnergyCarbonViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val strings = AppTheme.strings

    LaunchedEffect(Unit) {
        viewModel.load()
    }

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
                    text = AppTheme.strings.energyCarbonTitle,
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
            FilterRow(
                selectedPeriod = state.period,
                onPeriodChange = viewModel::onPeriodChange,
                selectedSource = state.source,
                onSourceChange = viewModel::onSourceChange
            )

            when {
                state.isLoading -> LoadingView()
                state.error != null -> ErrorView(message = state.error ?: "Bilinmeyen hata")
                state.readings.isEmpty() -> EmptyView(message = "Veri bulunamadı")
                else -> {
                    state.summary?.let {
                        SummaryCards(it)
                    }

                    SectionTitle(AppTheme.strings.consumptionChartTitle)

                    val modelProducer = remember { CartesianChartModelProducer() }

                    LaunchedEffect(state.readings) {
                        modelProducer.runTransaction {
                            columnSeries {
                                series(state.readings.map { it.consumptionKwh })
                            }
                            extras {
                                it[energyLabelsKey] = state.readings.map { reading -> strings.energyLabelFor(reading.label) }
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
                                bottomAxis = HorizontalAxis.rememberBottom(
                                    valueFormatter = CartesianValueFormatter { context, x, _ ->
                                        context.model.extraStore[energyLabelsKey].getOrElse(x.toInt()) { "" }
                                    }
                                )
                            ),
                            modelProducer = modelProducer,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp)
                                .padding(12.dp)
                        )
                    }

                    state.maintenanceImpact?.let { impact ->
                        Spacer(Modifier.height(4.dp))
                        SectionTitle(AppTheme.strings.maintenanceImpactTitle)
                        MaintenanceImpactCard(impact)
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
private fun periodLabel(period: EnergyPeriod): String = when (period) {
    EnergyPeriod.DAILY -> AppTheme.strings.periodDaily
    EnergyPeriod.WEEKLY -> AppTheme.strings.periodWeekly
    EnergyPeriod.MONTHLY -> AppTheme.strings.periodMonthly
}

@Composable
private fun sourceLabel(source: EnergySource): String = when (source) {
    EnergySource.ALL -> AppTheme.strings.sourceAll
    EnergySource.ELECTRICITY -> AppTheme.strings.sourceElectricity
    EnergySource.GAS -> AppTheme.strings.sourceGas
    EnergySource.WATER -> AppTheme.strings.sourceWater
}

@Composable
private fun FilterRow(
    selectedPeriod: EnergyPeriod,
    onPeriodChange: (EnergyPeriod) -> Unit,
    selectedSource: EnergySource,
    onSourceChange: (EnergySource) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            EnergyPeriod.entries.forEach { period ->
                FilterChip(
                    selected = period == selectedPeriod,
                    onClick = { onPeriodChange(period) },
                    label = { Text(periodLabel(period)) }
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            EnergySource.entries.forEach { source ->
                FilterChip(
                    selected = source == selectedSource,
                    onClick = { onSourceChange(source) },
                    label = { Text(sourceLabel(source)) }
                )
            }
        }
    }
}

@Composable
private fun SummaryCards(summary: EnergyCarbonSummary) {
    val changeIsPositive = summary.changeVsPreviousPercent <= 0
    val changeColor = if (changeIsPositive) OnlineGreen else OfflineRed
    val changeText = (if (summary.changeVsPreviousPercent > 0) "+" else "") +
            "${summary.changeVsPreviousPercent.roundToInt()}%"

    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        SummaryCard(Modifier.weight(1f), AppTheme.strings.totalConsumptionLabel, "${summary.totalConsumptionKwh.roundToInt()} L", MaterialTheme.colorScheme.primary)
        SummaryCard(Modifier.weight(1f), AppTheme.strings.carbonFootprintLabel, "${summary.totalCarbonKg.roundToInt()} kg CO₂", MaterialTheme.colorScheme.tertiary)
    }
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        SummaryCard(Modifier.weight(1f), AppTheme.strings.vsPreviousPeriodLabel, changeText, changeColor)
        SummaryCard(Modifier.weight(1f), AppTheme.strings.treeEquivalentLabel, "${summary.equivalentTreesPlanted} ${AppTheme.strings.treesPerYearUnit}", OnlineGreen)
    }
}

@Composable
private fun SummaryCard(modifier: Modifier, title: String, value: String, statusColor: Color) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(title, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(color = statusColor, shape = RoundedCornerShape(5.dp))
                )
            }
            Text(value, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
        }
    }
}
@Composable
private fun MaintenanceImpactCard(impact: MaintenanceImpact) {
    val tireColor = when (impact.tirePressureStatus) {
        TirePressureStatus.OK -> OnlineGreen
        TirePressureStatus.LOW, TirePressureStatus.HIGH -> OfflineRed
    }
    val tireLabel = when (impact.tirePressureStatus) {
        TirePressureStatus.OK -> AppTheme.strings.tirePressureOk
        TirePressureStatus.LOW -> AppTheme.strings.tirePressureLow
        TirePressureStatus.HIGH -> AppTheme.strings.tirePressureHigh
    }

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ImpactRow(AppTheme.strings.lastServiceLabel, impact.lastServiceDate)
            ImpactRow(
                AppTheme.strings.estimatedFuelSavingLabel,
                "%${impact.estimatedFuelSavingPercent}",
                valueColor = OnlineGreen
            )
            ImpactRow(AppTheme.strings.nextMaintenanceLabel, impact.nextMaintenanceDate)
            ImpactRow(AppTheme.strings.tirePressureLabel, tireLabel, valueColor = tireColor)
        }
    }
}

@Composable
private fun ImpactRow(label: String, value: String, valueColor: Color = MaterialTheme.colorScheme.onSurface) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = valueColor)
    }
}