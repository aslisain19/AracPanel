package com.example.myapplication.presentation.reports

import com.example.myapplication.domain.model.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ReportFormValidationTest {

    @Test
    fun `bos form tum zorunlu alanlarda hata veriyor`() {
        val state = ReportFormUiState()

        val errors = state.validate()

        assertTrue(errors.contains(ReportFormField.NAME))
        assertTrue(errors.contains(ReportFormField.GROUP_BY_X))
        assertTrue(errors.contains(ReportFormField.GROUP_BY_Y))
        assertTrue(errors.contains(ReportFormField.DISPLAY_VALUE))
        assertTrue(errors.contains(ReportFormField.DATE_RANGE))
    }

    @Test
    fun `tum zorunlu alanlar doluysa hata olusmuyor`() {
        val state = ReportFormUiState(
            name = "Test Raporu",
            groupByX = GroupByOption.LOCATION,
            groupByY = GroupByOption.ONE_DAY,
            displayValue = DisplayValue.GAS_CONSUMPTION,
            dateRangeKind = DateRangeKind.LAST_MONTH
        )

        val errors = state.validate()

        assertEquals(0, errors.size)
    }

    @Test
    fun `programlanmis acikken zamanli alan secilmezse hata veriyor`() {
        val state = ReportFormUiState(
            name = "Test",
            groupByX = GroupByOption.LOCATION,
            groupByY = GroupByOption.ONE_DAY,
            displayValue = DisplayValue.GAS_CONSUMPTION,
            dateRangeKind = DateRangeKind.LAST_MONTH,
            isScheduled = true,
            scheduledTime = null
        )

        val errors = state.validate()

        assertTrue(errors.contains(ReportFormField.SCHEDULED_TIME))
    }

    @Test
    fun `gecerli form komut nesnesine donusturulebiliyor`() {
        val state = ReportFormUiState(
            name = "Test Raporu",
            groupByX = GroupByOption.LOCATION,
            groupByY = GroupByOption.ONE_DAY,
            displayValue = DisplayValue.GAS_CONSUMPTION,
            dateRangeKind = DateRangeKind.LAST_MONTH
        )

        val command = state.toCommandOrNull()

        assertEquals("Test Raporu", command?.name)
        assertEquals(GroupByOption.LOCATION, command?.groupByX)
    }

    @Test
    fun `eksik alan varsa komut null donuyor`() {
        val state = ReportFormUiState(name = "Test")

        val command = state.toCommandOrNull()

        assertEquals(null, command)
    }
}