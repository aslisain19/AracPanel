package com.example.myapplication.data.remote

import com.example.myapplication.data.dto.SensorReadingDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class SensorApi {

    private val client = HttpClient(MockEngine) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        engine {
            addHandler { request ->
                respond(
                    content = """
                        [
                            {"timestamp": "09:00", "temperature": 45.2, "vibration": 1.1, "energy": 12.4},
                            {"timestamp": "10:00", "temperature": 47.8, "vibration": 1.3, "energy": 13.1},
                            {"timestamp": "11:00", "temperature": 52.1, "vibration": 1.8, "energy": 14.6},
                            {"timestamp": "12:00", "temperature": 49.5, "vibration": 1.4, "energy": 13.8},
                            {"timestamp": "13:00", "temperature": 46.0, "vibration": 1.2, "energy": 12.9},
                            {"timestamp": "14:00", "temperature": 48.3, "vibration": 1.5, "energy": 13.5}
                        ]
                    """.trimIndent(),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
        }
    }

    suspend fun getSensorReadings(machineId: String): List<SensorReadingDto> {
        return client.get("https://fake-api.com/sensors/$machineId").body()
    }
}