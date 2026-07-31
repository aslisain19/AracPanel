package com.example.myapplication.presentation.reports

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.domain.model.*
import com.example.myapplication.ui.theme.AppTheme
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportFormScreen(
    onBack: () -> Unit,
    onReportCreated: () -> Unit,
    viewModel: ReportFormViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val event by viewModel.events.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val reportCreationFailedError = AppTheme.strings.reportCreationFailedError

    LaunchedEffect(event) {
        when (val e = event) {
            is ReportFormEvent.Submitted -> {
                viewModel.consumeEvent()
                onReportCreated()
            }
            is ReportFormEvent.SubmitFailed -> {
                snackbarHostState.showSnackbar(reportCreationFailedError)
                viewModel.consumeEvent()
            }
            null -> Unit
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(AppTheme.strings.newReportTitle) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            Surface(tonalElevation = 3.dp) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(onClick = onBack, modifier = Modifier.weight(1f)) {
                        Text(AppTheme.strings.cancelButton)
                    }
                    Button(
                        onClick = viewModel::submit,
                        enabled = !state.isSubmitting,
                        modifier = Modifier.weight(1f)
                    ) {
                        if (state.isSubmitting) {
                            CircularProgressIndicator(modifier = Modifier.size(18.dp), strokeWidth = 2.dp)
                        } else {
                            Text(AppTheme.strings.createButton)
                        }
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            RequiredLabel(AppTheme.strings.nameLabel)
            OutlinedTextField(
                value = state.name,
                onValueChange = viewModel::onNameChange,
                modifier = Modifier.fillMaxWidth(),
                isError = state.errorFields.contains(ReportFormField.NAME),
                supportingText = {
                    if (state.errorFields.contains(ReportFormField.NAME)) Text(AppTheme.strings.nameRequiredError)
                },
                singleLine = true
            )

            RequiredLabel(AppTheme.strings.groupByXLabel)
            EnumDropdown(
                options = GroupByOption.xAxisOptions,
                selected = state.groupByX,
                labelOf = { AppTheme.strings.groupByLabel(it) },
                onSelect = viewModel::onGroupByXChange,
                isError = state.errorFields.contains(ReportFormField.GROUP_BY_X)
            )

            RequiredLabel(AppTheme.strings.groupByYLabel)
            EnumDropdown(
                options = GroupByOption.yAxisOptions,
                selected = state.groupByY,
                labelOf = { AppTheme.strings.groupByLabel(it) },
                onSelect = viewModel::onGroupByYChange,
                isError = state.errorFields.contains(ReportFormField.GROUP_BY_Y)
            )

            RequiredLabel(AppTheme.strings.displayValueLabel)
            EnumDropdown(
                options = DisplayValue.entries,
                selected = state.displayValue,
                labelOf = { AppTheme.strings.displayValueLabel(it) },
                onSelect = viewModel::onDisplayValueChange,
                isError = state.errorFields.contains(ReportFormField.DISPLAY_VALUE)
            )

            Column {
                Text(AppTheme.strings.notificationChannelsLabel, style = MaterialTheme.typography.labelLarge)
                Spacer(Modifier.height(4.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    LabeledCheckbox(AppTheme.strings.emailChannelLabel, state.sentEmail, viewModel::onToggleSentEmail)
                    LabeledCheckbox(AppTheme.strings.smsChannelLabel, state.sentSms, viewModel::onToggleSentSms)
                    LabeledCheckbox(AppTheme.strings.pushChannelLabel, state.sentNotification, viewModel::onToggleSentNotification)
                }
            }

            RequiredLabel(AppTheme.strings.dateRangeLabel)
            EnumDropdown(
                options = DateRangeKind.entries,
                selected = state.dateRangeKind,
                labelOf = { AppTheme.strings.dateRangeLabel(it) },
                onSelect = viewModel::onDateRangeChange,
                isError = state.errorFields.contains(ReportFormField.DATE_RANGE)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(AppTheme.strings.scheduledLabel, style = MaterialTheme.typography.bodyLarge)
                Switch(checked = state.isScheduled, onCheckedChange = viewModel::onToggleScheduled)
            }

            if (state.isScheduled) {
                Text(AppTheme.strings.frequencyLabel, style = MaterialTheme.typography.labelLarge)
                EnumDropdown(
                    options = ReportFrequency.entries,
                    selected = state.frequency,
                    labelOf = { AppTheme.strings.frequencyLabel(it) },
                    onSelect = viewModel::onFrequencyChange
                )

                RequiredLabel(AppTheme.strings.scheduledTimeLabel)
                EnumDropdown(
                    options = ScheduledTime.entries,
                    selected = state.scheduledTime,
                    labelOf = { it.label },
                    onSelect = viewModel::onScheduledTimeChange,
                    isError = state.errorFields.contains(ReportFormField.SCHEDULED_TIME),
                    errorMessage = AppTheme.strings.scheduledTimeRequiredError
                )
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun RequiredLabel(text: String) {
    Row {
        Text(text, style = MaterialTheme.typography.labelLarge)
        Text(" *", color = MaterialTheme.colorScheme.error)
    }
}

@Composable
private fun LabeledCheckbox(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Text(label)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun <T> EnumDropdown(
    options: List<T>,
    selected: T?,
    labelOf: @Composable (T) -> String,
    onSelect: (T) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }) {
        OutlinedTextField(
            value = if (selected != null) labelOf(selected) else "",
            onValueChange = {},
            readOnly = true,
            placeholder = { Text(AppTheme.strings.selectPlaceholder) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            isError = isError,
            supportingText = {
                if (isError) Text(errorMessage ?: AppTheme.strings.fieldRequiredError)
            },
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(labelOf(option), textAlign = TextAlign.Start) },
                    onClick = {
                        onSelect(option)
                        expanded = false
                    }
                )
            }
        }
    }
}