package com.example.myapplication.presentation.machinedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.components.EmptyView
import com.example.myapplication.ui.components.ErrorView
import com.example.myapplication.ui.components.LoadingView
import com.example.myapplication.ui.theme.AppTheme
import com.example.myapplication.presentation.sensorchart.SensorChartScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import com.example.myapplication.presentation.machinedetail.components.MachineHeaderCard
import com.example.myapplication.presentation.machinedetail.components.SensorChartCard
@Composable
fun MachineDetailScreen(machineId: String) {
    val viewModel: MachineDetailViewModel = koinViewModel(
        parameters = { parametersOf(machineId) }
    )
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(AppTheme.strings.machineDetailTitle) })
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
                .padding(16.dp)
        ) {
            when {
                uiState.isLoading -> LoadingView()
                uiState.error != null -> ErrorView(message = uiState.error ?: "Bilinmeyen hata")
                uiState.machine == null -> EmptyView(message = AppTheme.strings.machineNotFoundMessage)
                else -> {

                    val machine = uiState.machine!!

                    MachineHeaderCard(machine)

                    Spacer(modifier = Modifier.height(20.dp))

                    Spacer(modifier = Modifier.height(16.dp))

                    SensorChartCard(machine.serialNumber)
                }
            }
        }
    }
}