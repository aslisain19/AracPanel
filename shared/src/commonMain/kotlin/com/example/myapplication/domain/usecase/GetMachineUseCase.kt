package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.ConnectionStatus
import com.example.myapplication.domain.model.Machine
import com.example.myapplication.domain.repository.MachineRepository

class GetMachineListUseCase(
    private val repository: MachineRepository
) {
    operator fun invoke(machines: List<Machine>): List<Machine> {
        return machines.sortedBy { machine ->
            if (machine.connectionStatus == ConnectionStatus.OFFLINE) 0 else 1
        }
    }
}