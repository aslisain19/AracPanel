package com.example.myapplication.data.repository

import com.example.myapplication.data.mapper.toDomain
import com.example.myapplication.data.remote.MachineApi
import com.example.myapplication.domain.model.Machine
import com.example.myapplication.domain.repository.MachineRepository

class MachineRepositoryImpl(
    private val api: MachineApi
) : MachineRepository {

    override suspend fun getMachines(): List<Machine> {
        return api.getMachines().map { it.toDomain() }
    }
}
