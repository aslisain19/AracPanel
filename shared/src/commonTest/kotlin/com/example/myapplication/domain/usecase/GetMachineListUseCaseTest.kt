package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.ConnectionStatus
import com.example.myapplication.domain.model.Machine
import com.example.myapplication.domain.repository.MachineRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetMachineListUseCaseTest {

    @Test
    fun `offline makineler listenin basina siralaniyor`() = runTest {
        val onlineMachine = Machine(
            location = "Ankara", model = "MX500", name = "Online Makine",
            serialNumber = "1", connectionStatus = ConnectionStatus.ONLINE,
            lastConnection = "2026-07-08",
            oee = 90, workingHours = 1000, lastMaintenance = "2026-06-01"
        )
        val offlineMachine = Machine(
            location = "Ankara", model = "MX500", name = "Offline Makine",
            serialNumber = "2", connectionStatus = ConnectionStatus.OFFLINE,
            lastConnection = "2026-07-08",
            oee = 50, workingHours = 500, lastMaintenance = "2026-05-01"
        )

        val fakeRepository = object : MachineRepository {
            override suspend fun getMachines(): List<Machine> = listOf(onlineMachine, offlineMachine)
        }

        val useCase = GetMachineListUseCase(fakeRepository)
        val result = useCase(listOf(onlineMachine, offlineMachine))

        assertEquals(ConnectionStatus.OFFLINE, result.first().connectionStatus)
    }
}