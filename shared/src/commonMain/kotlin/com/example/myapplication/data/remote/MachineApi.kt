package com.example.myapplication.data.remote

import io.ktor.client.call.*
import com.example.myapplication.data.dto.MachineDto
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class MachineApi {

    private val client = HttpClient(MockEngine) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        engine {
            addHandler { request ->
                respond(
                    content = """
                        [
        {
            "location": "Ankara",
            "model": "MX500",
            "name": "SUAT ARAZ",
            "serialNumber": "131132",
            "connectionStatus": "online",
            "lastConnection": "08/07/2026 08:28:18",
            "oee": 87,
            "workingHours": 1240,
            "lastMaintenance": "01/06/2026"
        },
        {
            "location": "Ankara",
            "model": "MX500",
            "name": "Utest -3",
            "serialNumber": "131131",
            "connectionStatus": "offline",
            "lastConnection": "07/07/2026 15:33:12",
            "oee": 62,
            "workingHours": 890,
            "lastMaintenance": "15/05/2026"
        },
        {
            "location": "hol 1",
            "model": "MX500",
            "name": "Ali Kürkçü",
            "serialNumber": "127452",
            "connectionStatus": "online",
            "lastConnection": "08/07/2026 12:30:46",
            "oee": 94,
            "workingHours": 2010,
            "lastMaintenance": "20/06/2026"
        }
    ]
           """.trimIndent(),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }

        }
    }

    suspend fun getMachines(): List<MachineDto> {
        //kotlinx.coroutines.delay(2000) yükleniyor durumunu test etmek için koydum
        return client.get("https://fake-api.com/machines").body()
    }
}