package com.example.myapplication.data.repository

import com.example.myapplication.data.mapper.toDomain
import com.example.myapplication.data.mapper.toDto
import com.example.myapplication.data.remote.ReportApi
import com.example.myapplication.domain.model.CreateReportCommand
import com.example.myapplication.domain.model.Report
import com.example.myapplication.domain.repository.ReportRepository

class ReportRepositoryImpl(private val api: ReportApi) : ReportRepository {

    override suspend fun getReports(): Result<List<Report>> = runCatching {
        api.getReports().map { it.toDomain() }
    }

    override suspend fun createReport(command: CreateReportCommand): Result<Unit> = runCatching {
        api.createReport(command.toDto())
    }

    override suspend fun deleteReport(id: String): Result<Unit> = runCatching {
        api.deleteReport(id)
    }
}