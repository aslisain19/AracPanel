package com.example.myapplication.data.remote

import io.ktor.client.call.*
import com.example.myapplication.data.dto.CreateReportRequestDto
import com.example.myapplication.data.dto.ReportDto
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class ReportApi {

    // Basit in-memory mock veri; gerçek backend 2. fazda buranın yerini alacak.
    private val mockReports = mutableListOf(
        ReportDto(
            id = "1",
            name = "awedf",
            groupByX = "Location",
            groupByY = "OneDay",
            displayValue = "GazTuketimi",
            sentEmail = false,
            sentSms = false,
            sentNotification = false,
            dateRangeKind = "LastMonth"
        )
    )

    private val client = HttpClient(MockEngine) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        engine {
            addHandler { request ->
                when {
                    request.method == HttpMethod.Get && request.url.encodedPath == "/reports" -> {
                        val json = Json.encodeToString(
                            kotlinx.serialization.builtins.ListSerializer(ReportDto.serializer()),
                            mockReports.toList()
                        )
                        respond(
                            content = json,
                            status = HttpStatusCode.OK,
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                    }

                    request.method == HttpMethod.Post && request.url.encodedPath == "/reports" -> {
                        val bodyText = (request.body as? io.ktor.http.content.TextContent)?.text
                        val dto = bodyText?.let {
                            Json { ignoreUnknownKeys = true }
                                .decodeFromString(CreateReportRequestDto.serializer(), it)
                        }

                        if (dto != null) {
                            mockReports.add(
                                ReportDto(
                                    id = (mockReports.size + 1).toString(),
                                    name = dto.name,
                                    groupByX = dto.groupByX,
                                    groupByY = dto.groupByY,
                                    displayValue = dto.displayValue,
                                    sentEmail = dto.sentEmail,
                                    sentSms = dto.sentSms,
                                    sentNotification = dto.sentNotification,
                                    dateRangeKind = dto.dateRangeKind
                                )
                            )
                        }

                        respond(
                            content = "",
                            status = HttpStatusCode.Created
                        )
                    }

                    request.method == HttpMethod.Delete && request.url.encodedPath.startsWith("/reports/") -> {
                        val id = request.url.encodedPath.removePrefix("/reports/")
                        mockReports.removeAll { it.id == id }
                        respond(
                            content = "",
                            status = HttpStatusCode.NoContent
                        )
                    }

                    else -> respond(
                        content = "Not found",
                        status = HttpStatusCode.NotFound
                    )
                }
            }
        }
    }

    suspend fun getReports(): List<ReportDto> {
        return client.get("https://fake-api.com/reports").body()
    }

    suspend fun createReport(request: CreateReportRequestDto) {
        client.post("https://fake-api.com/reports") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }

    suspend fun deleteReport(id: String) {
        client.delete("https://fake-api.com/reports/$id")
    }
}