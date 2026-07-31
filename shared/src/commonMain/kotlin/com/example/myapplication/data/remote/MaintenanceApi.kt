package com.example.myapplication.data.remote

import com.example.myapplication.data.dto.DailyAlertCountDto
import com.example.myapplication.data.dto.MaintenancePredictionDto
import com.example.myapplication.data.dto.MaintenanceWorkOrderDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class MaintenanceApi {

    private val client = HttpClient(MockEngine) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        engine {
            addHandler { request ->
                val path = request.url.encodedPath
                when {
                    path.contains("work-orders") -> respond(
                        content = """
                            [
                                {"serialNumber": "131132", "durationRatio": 0.8, "planningDate": "28/07/2026", "state": "PLANNED"},
                                {"serialNumber": "131131", "durationRatio": 0.3, "planningDate": "26/07/2026", "state": "IN_PROGRESS"},
                                {"serialNumber": "127452", "durationRatio": 1.0, "planningDate": "20/07/2026", "state": "COMPLETED"}
                            ]
                        """.trimIndent(),
                        status = HttpStatusCode.OK,
                        headers = headersOf(HttpHeaders.ContentType, "application/json")
                    )
                    path.contains("daily-alerts") -> respond(
                        content = """
                            [
                                {"date": "18/07/2026", "count": 0},
                                {"date": "19/07/2026", "count": 0},
                                {"date": "20/07/2026", "count": 45},
                                {"date": "21/07/2026", "count": 28},
                                {"date": "22/07/2026", "count": 32},
                                {"date": "23/07/2026", "count": 30},
                                {"date": "24/07/2026", "count": 51}
                            ]
                        """.trimIndent(),
                        status = HttpStatusCode.OK,
                        headers = headersOf(HttpHeaders.ContentType, "application/json")
                    )
                    else -> respond(
                        content = """
                            [
                                {"machineId": "131132", "remainingUsefulLifeDays": 45, "riskLevel": "LOW", "predictedFailureDate": "10/09/2026", "recommendation": "Rutin bakım yeterli"},
                                {"machineId": "131131", "remainingUsefulLifeDays": 7, "riskLevel": "HIGH", "predictedFailureDate": "31/07/2026", "recommendation": "Acil bakım planlayın"},
                                {"machineId": "127452", "remainingUsefulLifeDays": 20, "riskLevel": "MEDIUM", "predictedFailureDate": "14/08/2026", "recommendation": "2 hafta içinde kontrol edin"}
                            ]
                        """.trimIndent(),
                        status = HttpStatusCode.OK,
                        headers = headersOf(HttpHeaders.ContentType, "application/json")
                    )
                }
            }
        }
    }

    suspend fun getMaintenancePredictions(): List<MaintenancePredictionDto> {
        return client.get("https://fake-api.com/maintenance-predictions").body()
    }

    suspend fun getWorkOrders(): List<MaintenanceWorkOrderDto> {
        return client.get("https://fake-api.com/work-orders").body()
    }

    suspend fun getDailyAlerts(): List<DailyAlertCountDto> {
        return client.get("https://fake-api.com/daily-alerts").body()
    }
}