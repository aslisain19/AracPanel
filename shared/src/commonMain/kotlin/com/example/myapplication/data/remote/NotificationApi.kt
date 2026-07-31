package com.example.myapplication.data.remote

import com.example.myapplication.data.dto.NotificationDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class NotificationApi {

    private val client = HttpClient(MockEngine) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        engine {
            addHandler { request ->
                respond(
                    content = """
                        [
                            {"id": "1", "machineId": "131132", "errorCode": "256", "message": "Aşırı akım tespit edildi", "severity": "CRITICAL", "count": 856, "timestamp": "24/07/2026 08:12"},
                            {"id": "2", "machineId": "131131", "errorCode": "E4", "message": "Gaz akışı düşük", "severity": "WARNING", "count": 724, "timestamp": "24/07/2026 07:45"},
                            {"id": "3", "machineId": "127452", "errorCode": "E5", "message": "Kalibrasyon önerilir", "severity": "INFO", "count": 1, "timestamp": "23/07/2026 16:30"},
                            {"id": "4", "machineId": "131132", "errorCode": "268", "message": "Sıcaklık limiti aşıldı", "severity": "CRITICAL", "count": 5202, "timestamp": "24/07/2026 09:03"},
                            {"id": "5", "machineId": "131131", "errorCode": "264", "message": "Tel besleme sorunu", "severity": "WARNING", "count": 2168, "timestamp": "23/07/2026 14:20"}
                        ]
                    """.trimIndent(),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
        }
    }

    suspend fun getNotifications(): List<NotificationDto> {
        return client.get("https://fake-api.com/notifications").body()
    }
}