package com.example.myapplication.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MachineDto (
    val location: String,
    val model: String,
    val name: String,
    val serialNumber: String,
    val connectionStatus: String,
    val lastConnection: String,
    val oee: Int,
    val workingHours: Int,
    val lastMaintenance: String

)
