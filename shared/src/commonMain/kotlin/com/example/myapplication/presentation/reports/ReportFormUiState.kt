package com.example.myapplication.presentation.reports

import com.example.myapplication.domain.model.*

data class ReportFormUiState(
    val name: String = "",
    val groupByX: GroupByOption? = null,
    val groupByY: GroupByOption? = null,
    val displayValue: DisplayValue? = null,
    val sentEmail: Boolean = false,
    val sentSms: Boolean = false,
    val sentNotification: Boolean = false,
    val dateRangeKind: DateRangeKind? = null,
    val isScheduled: Boolean = false,
    val frequency: ReportFrequency = ReportFrequency.DAILY,
    val scheduledTime: ScheduledTime? = null,
    val isSubmitting: Boolean = false,
    val errorFields: Set<ReportFormField> = emptySet()
)

enum class ReportFormField { NAME, GROUP_BY_X, GROUP_BY_Y, DISPLAY_VALUE, DATE_RANGE, SCHEDULED_TIME }

fun ReportFormUiState.validate(): Set<ReportFormField> {
    val errors = mutableSetOf<ReportFormField>()
    if (name.isBlank()) errors += ReportFormField.NAME
    if (groupByX == null) errors += ReportFormField.GROUP_BY_X
    if (groupByY == null) errors += ReportFormField.GROUP_BY_Y
    if (displayValue == null) errors += ReportFormField.DISPLAY_VALUE
    if (dateRangeKind == null) errors += ReportFormField.DATE_RANGE
    if (isScheduled && scheduledTime == null) errors += ReportFormField.SCHEDULED_TIME
    return errors
}

fun ReportFormUiState.toCommandOrNull(): CreateReportCommand? {
    val x = groupByX ?: return null
    val y = groupByY ?: return null
    val value = displayValue ?: return null
    val range = dateRangeKind ?: return null
    if (isScheduled && scheduledTime == null) return null
    return CreateReportCommand(
        name = name, groupByX = x, groupByY = y, displayValue = value,
        sentEmail = sentEmail, sentSms = sentSms, sentNotification = sentNotification,
        dateRangeKind = range, isScheduled = isScheduled, frequency = frequency,
        scheduledTime = scheduledTime
    )
}