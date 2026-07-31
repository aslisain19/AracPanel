package com.example.myapplication.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ReportDto(
    val id: String,
    val name: String,
    val groupByX: String,
    val groupByY: String,
    val displayValue: String,
    val sentEmail: Boolean = false,
    val sentSms: Boolean = false,
    val sentNotification: Boolean = false,
    val dateRangeKind: String
)

@Serializable
data class CreateReportRequestDto(
    val name: String,
    val groupByX: String,
    val groupByY: String,
    val displayValue: String,
    val sentEmail: Boolean,
    val sentSms: Boolean,
    val sentNotification: Boolean,
    val dateRangeKind: String,
    val isScheduled: Boolean,
    val frequency: String,
    val scheduledTime: String?
)