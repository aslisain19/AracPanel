package com.example.myapplication.data.remote

import io.ktor.client.call.*
import com.example.myapplication.data.dto.EnergyReadingDto
import com.example.myapplication.data.dto.MaintenanceImpactDto
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlin.random.Random

class EnergyApi {

    private val client = HttpClient(MockEngine) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        engine {
            addHandler { request ->
                when {
                    request.url.encodedPath.contains("maintenance-impact") -> {
                        val dto = MaintenanceImpactDto(
                            lastServiceDate = "15/06/2026",
                            estimatedFuelSavingPercent = 8.5,
                            nextMaintenanceDate = "15/09/2026",
                            tirePressureStatus = "Ok"
                        )
                        respond(
                            content = Json.encodeToString(MaintenanceImpactDto.serializer(), dto),
                            status = HttpStatusCode.OK,
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                    }
                    else -> {
                        val period = request.url.parameters["period"] ?: "Weekly"
                        val labels = when (period) {
                            "Daily" -> listOf("00:00", "04:00", "08:00", "12:00", "16:00", "20:00")
                            "Monthly" -> listOf("Hafta 1", "Hafta 2", "Hafta 3", "Hafta 4")
                            else -> listOf("Pzt", "Sal", "Çar", "Per", "Cum", "Cmt", "Paz")
                        }

                        val readings = labels.map { label ->
                            val consumption = Random.nextDouble(40.0, 120.0)
                            EnergyReadingDto(
                                label = label,
                                consumptionKwh = consumption,
                                carbonKg = consumption * 0.45
                            )
                        }

                        respond(
                            content = Json.encodeToString(
                                ListSerializer(EnergyReadingDto.serializer()),
                                readings
                            ),
                            status = HttpStatusCode.OK,
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                    }
                }
            }
        }
    }

    suspend fun getReadings(period: String, source: String): List<EnergyReadingDto> {
        return client.get("https://fake-api.com/energy") {
            parameter("period", period)
            parameter("source", source)
        }.body()
    }

    suspend fun getMaintenanceImpact(): MaintenanceImpactDto {
        return client.get("https://fake-api.com/energy/maintenance-impact").body()
    }
}