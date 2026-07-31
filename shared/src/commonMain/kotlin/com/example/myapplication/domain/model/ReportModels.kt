package com.example.myapplication.domain.model

enum class GroupByOption(val apiValue: String, val label: String) {
    LOCATION("Location", "Konum"),
    ONE_DAY("OneDay", "Gün"),
    ONE_HOUR("OneHour", "Saat"),
    MACHINE("Machine", "Makine");

    companion object {
        val xAxisOptions = listOf(LOCATION, MACHINE)
        val yAxisOptions = listOf(ONE_DAY, ONE_HOUR)
    }
}

enum class DisplayValue(val apiValue: String, val label: String) {
    GAS_CONSUMPTION("GazTuketimi", "Gaz Tüketimi"),
    ELECTRICITY_CONSUMPTION("ElektrikTuketimi", "Elektrik Tüketimi"),
    WATER_CONSUMPTION("SuTuketimi", "Su Tüketimi")
}

enum class DateRangeKind(val apiValue: String, val label: String) {
    TODAY("Today", "Bugün"),
    LAST_WEEK("LastWeek", "Geçen Hafta"),
    LAST_MONTH("LastMonth", "Geçen Ay"),
    CUSTOM("Custom", "Özel Aralık")
}

enum class ReportFrequency(val apiValue: String, val label: String) {
    DAILY("Daily", "Günlük"),
    WEEKLY("Weekly", "Haftalık"),
    MONTHLY("Monthly", "Aylık")
}

enum class ScheduledTime(val apiValue: String, val label: String) {
    MORNING_08("08:00", "08:00"),
    NOON_12("12:00", "12:00"),
    EVENING_18("18:00", "18:00")
}

/** Rapor oluşturma isteği — use case'e giren komut nesnesi. */
data class CreateReportCommand(
    val name: String,
    val groupByX: GroupByOption,
    val groupByY: GroupByOption,
    val displayValue: DisplayValue,
    val sentEmail: Boolean,
    val sentSms: Boolean,
    val sentNotification: Boolean,
    val dateRangeKind: DateRangeKind,
    val isScheduled: Boolean,
    val frequency: ReportFrequency,
    val scheduledTime: ScheduledTime?
)

/** Rapor listesindeki tek bir kayıt (domain modeli, DTO değil). */
data class Report(
    val id: String,
    val name: String,
    val groupByX: GroupByOption,
    val groupByY: GroupByOption,
    val displayValue: DisplayValue,
    val dateRangeKind: DateRangeKind
)