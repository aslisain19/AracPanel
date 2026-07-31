package com.example.myapplication.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Dashboard : Routes("dashboard")
    object MachineList : Routes("machine_list")
    object MachineDetail : Routes("machine_detail/{machineId}") {
        fun createRoute(machineId: String) = "machine_detail/$machineId"
    }

    object SensorChart : Routes("sensor_chart")
    object Notifications : Routes("notifications")

    object Maintenance : Routes("maintenance")

    object ReportList : Routes("report_list")
    object ReportForm : Routes("report_form")

    object EnergyCarbon : Routes("energy_carbon")
    object Settings : Routes("settings")


}