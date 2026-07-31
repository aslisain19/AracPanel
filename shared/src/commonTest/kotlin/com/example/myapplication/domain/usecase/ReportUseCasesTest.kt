package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.*
import com.example.myapplication.domain.repository.ReportRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ReportUseCasesTest {

    private val sampleReport = Report(
        id = "1",
        name = "awedf",
        groupByX = GroupByOption.LOCATION,
        groupByY = GroupByOption.ONE_DAY,
        displayValue = DisplayValue.GAS_CONSUMPTION,
        dateRangeKind = DateRangeKind.LAST_MONTH
    )

    private val sampleCommand = CreateReportCommand(
        name = "Yeni Rapor",
        groupByX = GroupByOption.MACHINE,
        groupByY = GroupByOption.ONE_HOUR,
        displayValue = DisplayValue.WATER_CONSUMPTION,
        sentEmail = false,
        sentSms = false,
        sentNotification = false,
        dateRangeKind = DateRangeKind.CUSTOM,
        isScheduled = false,
        frequency = ReportFrequency.DAILY,
        scheduledTime = null
    )

    @Test
    fun `GetReportsUseCase repository sonucunu oldugu gibi donduruyor`() = runTest {
        val fakeRepository = object : ReportRepository {
            override suspend fun getReports(): Result<List<Report>> = Result.success(listOf(sampleReport))
            override suspend fun createReport(command: CreateReportCommand): Result<Unit> = Result.success(Unit)
            override suspend fun deleteReport(id: String): Result<Unit> = Result.success(Unit)
        }

        val useCase = GetReportsUseCase(fakeRepository)
        val result = useCase()

        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
        assertEquals("awedf", result.getOrNull()?.first()?.name)
    }

    @Test
    fun `CreateReportUseCase komutu repositorye iletiyor`() = runTest {
        var receivedCommand: CreateReportCommand? = null
        val fakeRepository = object : ReportRepository {
            override suspend fun getReports(): Result<List<Report>> = Result.success(emptyList())
            override suspend fun createReport(command: CreateReportCommand): Result<Unit> {
                receivedCommand = command
                return Result.success(Unit)
            }
            override suspend fun deleteReport(id: String): Result<Unit> = Result.success(Unit)
        }

        val useCase = CreateReportUseCase(fakeRepository)
        val result = useCase(sampleCommand)

        assertTrue(result.isSuccess)
        assertEquals("Yeni Rapor", receivedCommand?.name)
    }

    @Test
    fun `DeleteReportUseCase dogru idyi repositorye iletiyor`() = runTest {
        var receivedId: String? = null
        val fakeRepository = object : ReportRepository {
            override suspend fun getReports(): Result<List<Report>> = Result.success(emptyList())
            override suspend fun createReport(command: CreateReportCommand): Result<Unit> = Result.success(Unit)
            override suspend fun deleteReport(id: String): Result<Unit> {
                receivedId = id
                return Result.success(Unit)
            }
        }

        val useCase = DeleteReportUseCase(fakeRepository)
        val result = useCase("42")

        assertTrue(result.isSuccess)
        assertEquals("42", receivedId)
    }
}