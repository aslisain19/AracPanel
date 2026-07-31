package com.example.myapplication.data.mapper

import com.example.myapplication.data.dto.MachineDto
import com.example.myapplication.domain.model.ConnectionStatus
import com.example.myapplication.domain.model.Machine

fun MachineDto.toDomain(): Machine {
    return Machine(
        location = this.location,
        model = this.model,
        name = this.name,
        serialNumber = this.serialNumber,
        connectionStatus = when (this.connectionStatus) {
            "online" -> ConnectionStatus.ONLINE
            else -> ConnectionStatus.OFFLINE
        },
        lastConnection = this.lastConnection,
        oee = this.oee,
        workingHours = this.workingHours,
        lastMaintenance = this.lastMaintenance
    )
}