package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.Machine

interface MachineRepository {
    suspend fun getMachines(): List<Machine>
}