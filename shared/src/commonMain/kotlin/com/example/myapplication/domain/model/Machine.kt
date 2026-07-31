package com.example.myapplication.domain.model

data class Machine(
    val location: String,
    val model: String,
    val name: String,
    val serialNumber: String,
    val connectionStatus: ConnectionStatus,
    val lastConnection: String,
    val oee: Int,
    val workingHours: Int,
    val lastMaintenance: String
)

    enum class ConnectionStatus{
        ONLINE,
        OFFLINE
    }
