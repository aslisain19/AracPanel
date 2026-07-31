package com.example.myapplication.presentation.reports

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.domain.model.Report
import com.example.myapplication.ui.theme.AppTheme
import org.koin.compose.viewmodel.koinViewModel
import com.example.myapplication.ui.components.EmptyView
import com.example.myapplication.ui.components.ErrorView
import com.example.myapplication.ui.components.LoadingView

@Composable
fun ReportListScreen(
    onCreateReport: () -> Unit,
    onOpenReport: (Report) -> Unit,
    viewModel: ReportListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(AppTheme.strings.reportsTitle) }) },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = onCreateReport) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text(AppTheme.strings.createReportButton)
            }
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            OutlinedTextField(
                value = state.query,
                onValueChange = viewModel::onQueryChange,
                placeholder = { Text(AppTheme.strings.searchPlaceholder) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            when {
                state.isLoading -> LoadingView()
                state.error != null -> ErrorView(message = state.error!!)
                state.pagedItems.isEmpty() -> EmptyView(message = AppTheme.strings.noRecordsMessage)
                else -> LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.pagedItems, key = { it.id }) { item ->
                        ReportRow(
                            item = item,
                            onClick = { onOpenReport(item) },
                            onDelete = { viewModel.deleteReport(item.id) }
                        )
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            PaginationBar(
                page = state.page,
                totalPages = state.totalPages,
                totalCount = state.totalCount,
                pageSize = state.pageSize,
                canGoPrevious = state.canGoPrevious,
                canGoNext = state.canGoNext,
                onFirst = viewModel::goToFirstPage,
                onPrevious = viewModel::goToPreviousPage,
                onNext = viewModel::goToNextPage,
                onLast = viewModel::goToLastPage
            )
        }
    }
}

@Composable
private fun ReportRow(item: Report, onClick: () -> Unit, onDelete: () -> Unit) {
    var menuExpanded by remember { mutableStateOf(false) }

    ElevatedCard(onClick = onClick, modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Surface(shape = MaterialTheme.shapes.small, color = MaterialTheme.colorScheme.primaryContainer) {
                Box(Modifier.size(40.dp), contentAlignment = Alignment.Center) {
                    Text(item.name.take(1).uppercase(), fontWeight = FontWeight.Bold)
                }
            }

            Spacer(Modifier.width(12.dp))

            Column(Modifier.weight(1f)) {
                Text(item.name, fontWeight = FontWeight.SemiBold)
                Text(
                    "${AppTheme.strings.groupByLabel(item.groupByX)} · ${AppTheme.strings.groupByLabel(item.groupByY)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                AssistChip(onClick = {}, label = { Text(AppTheme.strings.displayValueLabel(item.displayValue)) })
                Spacer(Modifier.height(4.dp))
                Text(
                    AppTheme.strings.dateRangeLabel(item.dateRangeKind),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Box {
                IconButton(onClick = { menuExpanded = true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = AppTheme.strings.moreOptionsDescription)
                }
                DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text(AppTheme.strings.deleteMenuItem) },
                        leadingIcon = { Icon(Icons.Default.Delete, contentDescription = null) },
                        onClick = {
                            menuExpanded = false
                            onDelete()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun PaginationBar(
    page: Int,
    totalPages: Int,
    totalCount: Int,
    pageSize: Int,
    canGoPrevious: Boolean,
    canGoNext: Boolean,
    onFirst: () -> Unit,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onLast: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val start = if (totalCount == 0) 0 else (page - 1) * pageSize + 1
        val end = minOf(page * pageSize, totalCount)
        Text("${AppTheme.strings.showingPaginationLabel} $start - $end / $totalCount", style = MaterialTheme.typography.bodySmall)

        Row {
            TextButton(onClick = onFirst, enabled = canGoPrevious) { Text("«") }
            TextButton(onClick = onPrevious, enabled = canGoPrevious) { Text("‹") }
            TextButton(onClick = onNext, enabled = canGoNext) { Text("›") }
            TextButton(onClick = onLast, enabled = canGoNext) { Text("»") }
        }
    }
}