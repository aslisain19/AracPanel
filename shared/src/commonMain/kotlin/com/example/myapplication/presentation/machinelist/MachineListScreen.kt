package com.example.myapplication.presentation.machinelist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.myapplication.domain.model.Machine
import com.example.myapplication.ui.components.EmptyView
import com.example.myapplication.ui.components.ErrorView
import com.example.myapplication.ui.components.LoadingView
import com.example.myapplication.ui.theme.AppTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MachineListScreen(
    viewModel: MachineListViewModel = koinViewModel(),
    onMachineClick: (Machine) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(AppTheme.strings.machineListTitle) })
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            when {
                uiState.isLoading -> LoadingView()
                uiState.error != null -> ErrorView(message = uiState.error ?: "Bilinmeyen hata")
                uiState.machines.isEmpty() -> EmptyView(message = "Henüz makine bulunamadı")
                else -> {
                    LazyColumn {
                        items(uiState.machines) { machine ->
                            MachineCard(machine, onClick = { onMachineClick(machine) })
                        }
                    }
                }
            }
        }
    }
}