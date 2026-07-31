package com.example.myapplication.data.mapper

import com.example.myapplication.data.dto.CreateReportRequestDto
import com.example.myapplication.data.dto.ReportDto
import com.example.myapplication.domain.model.*

fun ReportDto.toDomain(): Report = Report(
    id = id,
    name = name,
    groupByX = GroupByOption.entries.first { it.apiValue == groupByX },
    groupByY = GroupByOption.entries.first { it.apiValue == groupByY },
    displayValue = DisplayValue.entries.first { it.apiValue == displayValue },
    dateRangeKind = DateRangeKind.entries.first { it.apiValue == dateRangeKind }
)

fun CreateReportCommand.toDto(): CreateReportRequestDto = CreateReportRequestDto(
    name = name,
    groupByX = groupByX.apiValue,
    groupByY = groupByY.apiValue,
    displayValue = displayValue.apiValue,
    sentEmail = sentEmail,
    sentSms = sentSms,
    sentNotification = sentNotification,
    dateRangeKind = dateRangeKind.apiValue,
    isScheduled = isScheduled,
    frequency = frequency.apiValue,
    scheduledTime = scheduledTime?.apiValue
)