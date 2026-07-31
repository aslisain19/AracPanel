package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.EnergyPeriod
import com.example.myapplication.domain.model.EnergyReading
import com.example.myapplication.domain.model.EnergySource
import com.example.myapplication.domain.repository.EnergyCarbonRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetEnergyReadingsUseCaseTest {

    private val sampleReadings = listOf(
        EnergyReading(label = "Pzt", consumptionKwh = 100.0, carbonKg = 45.0),
        EnergyReading(label = "Sal", consumptionKwh = 80.0, carbonKg = 36.0)
    )

    @Test
    fun `secilen donem ve kaynak repositorye dogru iletiliyor`() = runTest {
        var receivedPeriod: EnergyPeriod? = null
        var receivedSource: EnergySource? = null

        val fakeRepository = object : EnergyCarbonRepository {
            override suspend fun getReadings(
                period: EnergyPeriod,
                source: EnergySource
            ): Result<List<EnergyReading>> {
                receivedPeriod = period
                receivedSource = source
                return Result.success(sampleReadings)
            }
        }

        val useCase = GetEnergyReadingsUseCase(fakeRepository)
        val result = useCase(EnergyPeriod.MONTHLY, EnergySource.ELECTRICITY)

        assertTrue(result.isSuccess)
        assertEquals(EnergyPeriod.MONTHLY, receivedPeriod)
        assertEquals(EnergySource.ELECTRICITY, receivedSource)
        assertEquals(2, result.getOrNull()?.size)
    }
}