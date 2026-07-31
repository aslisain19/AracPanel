package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.CreateReportCommand
import com.example.myapplication.domain.model.Report
import com.example.myapplication.domain.repository.ReportRepository

class GetReportsUseCase(private val repository: ReportRepository) {
    suspend operator fun invoke(): Result<List<Report>> = repository.getReports()
}

class CreateReportUseCase(private val repository: ReportRepository) {
    suspend operator fun invoke(command: CreateReportCommand): Result<Unit> =
        repository.createReport(command)
}

class DeleteReportUseCase(private val repository: ReportRepository) {
    suspend operator fun invoke(id: String): Result<Unit> = repository.deleteReport(id)
}