package com.example.myapplication.data.mapper

import com.example.myapplication.data.dto.MachineDto
import com.example.myapplication.domain.model.ConnectionStatus
import kotlin.test.Test
import kotlin.test.assertEquals

class MachineMapperTest {

    @Test
    fun `dto online durumunu domain modeline dogru ceviriyor`() {
        val dto = MachineDto(
            location = "Ankara",
            model = "MX500",
            name = "SUAT ARAZ",
            serialNumber = "131132",
            connectionStatus = "online",
            lastConnection = "08/07/2026 08:28:18",
            oee = 87,
            workingHours = 1240,
            lastMaintenance = "01/06/2026"
        )


        val result = dto.toDomain()

        assertEquals(ConnectionStatus.ONLINE, result.connectionStatus)
        assertEquals("Ankara", result.location)
    }
}