package com.example.myapplication.data.mapper

import com.example.myapplication.data.dto.ReportDto
import com.example.myapplication.domain.model.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ReportMapperTest {

    @Test
    fun `ReportDto domain modeline dogru esleniyor`() {
        val dto = ReportDto(
            id = "1",
            name = "awedf",
            groupByX = "Location",
            groupByY = "OneDay",
            displayValue = "GazTuketimi",
            dateRangeKind = "LastMonth"
        )

        val domain = dto.toDomain()

        assertEquals("1", domain.id)
        assertEquals("awedf", domain.name)
        assertEquals(GroupByOption.LOCATION, domain.groupByX)
        assertEquals(GroupByOption.ONE_DAY, domain.groupByY)
        assertEquals(DisplayValue.GAS_CONSUMPTION, domain.displayValue)
        assertEquals(DateRangeKind.LAST_MONTH, domain.dateRangeKind)
    }

    @Test
    fun `CreateReportCommand DTOya dogru esleniyor`() {
        val command = CreateReportCommand(
            name = "Yeni Rapor",
            groupByX = GroupByOption.MACHINE,
            groupByY = GroupByOption.ONE_HOUR,
            displayValue = DisplayValue.ELECTRICITY_CONSUMPTION,
            sentEmail = true,
            sentSms = false,
            sentNotification = true,
            dateRangeKind = DateRangeKind.TODAY,
            isScheduled = true,
            frequency = ReportFrequency.WEEKLY,
            scheduledTime = ScheduledTime.NOON_12
        )

        val dto = command.toDto()

        assertEquals("Yeni Rapor", dto.name)
        assertEquals("Machine", dto.groupByX)
        assertEquals("OneHour", dto.groupByY)
        assertEquals("ElektrikTuketimi", dto.displayValue)
        assertEquals(true, dto.sentEmail)
        assertEquals(false, dto.sentSms)
        assertEquals("Today", dto.dateRangeKind)
        assertEquals("Weekly", dto.frequency)
        assertEquals("12:00", dto.scheduledTime)
    }
}