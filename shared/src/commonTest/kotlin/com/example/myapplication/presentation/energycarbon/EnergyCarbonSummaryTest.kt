package com.example.myapplication.presentation.energycarbon

import com.example.myapplication.domain.model.EnergyReading
import kotlin.test.Test
import kotlin.test.assertEquals

class EnergyCarbonSummaryTest {

    @Test
    fun `toplam tuketim ve karbon dogru hesaplaniyor`() {
        val readings = listOf(
            EnergyReading(label = "Pzt", consumptionKwh = 100.0, carbonKg = 45.0),
            EnergyReading(label = "Sal", consumptionKwh = 50.0, carbonKg = 22.5)
        )

        val summary = readings.toSummary()

        assertEquals(150.0, summary.totalConsumptionKwh)
        assertEquals(67.5, summary.totalCarbonKg)
    }

    @Test
    fun `agac esdegeri karbon toplamindan dogru hesaplaniyor`() {
        val readings = listOf(
            EnergyReading(label = "Pzt", consumptionKwh = 100.0, carbonKg = 210.0)
        )

        val summary = readings.toSummary()

        assertEquals(10, summary.equivalentTreesPlanted)
    }
}