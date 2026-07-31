package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.CreateReportCommand
import com.example.myapplication.domain.model.Report

interface ReportRepository {
    suspend fun getReports(): Result<List<Report>>
    suspend fun createReport(command: CreateReportCommand): Result<Unit>
    suspend fun deleteReport(id: String): Result<Unit>
}