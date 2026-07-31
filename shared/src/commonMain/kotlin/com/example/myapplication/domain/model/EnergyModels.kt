package com.example.myapplication.domain.model

enum class EnergyPeriod(val apiValue: String, val label: String) {
    DAILY("Daily", "Günlük"),
    WEEKLY("Weekly", "Haftalık"),
    MONTHLY("Monthly", "Aylık")
}

enum class EnergySource(val apiValue: String, val label: String) {
    ALL("All", "Tümü"),
    ELECTRICITY("Electricity", "Elektrik"),
    GAS("Gas", "Doğalgaz"),
    WATER("Water", "Su")
}

/** Zaman içindeki tek bir enerji tüketim noktası (grafik verisi). */
data class EnergyReading(
    val label: String,       // örn: "Pzt", "12:00", "Hafta 1"
    val consumptionKwh: Double,
    val carbonKg: Double
)

/** Üst özet kartları için hesaplanmış toplamlar. */
data class EnergyCarbonSummary(
    val totalConsumptionKwh: Double,
    val totalCarbonKg: Double,
    val changeVsPreviousPercent: Double, // negatif = azalma (iyi), pozitif = artış
    val equivalentTreesPlanted: Int
)
enum class TirePressureStatus(val apiValue: String) {
    OK("Ok"),
    LOW("Low"),
    HIGH("High")
}

data class MaintenanceImpact(
    val lastServiceDate: String,
    val estimatedFuelSavingPercent: Double,
    val nextMaintenanceDate: String,
    val tirePressureStatus: TirePressureStatus
)