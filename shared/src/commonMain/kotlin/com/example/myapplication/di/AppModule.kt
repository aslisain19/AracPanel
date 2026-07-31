package com.example.myapplication.di

import com.example.myapplication.data.remote.MachineApi
import com.example.myapplication.data.repository.MachineRepositoryImpl
import com.example.myapplication.domain.repository.MachineRepository
import com.example.myapplication.domain.usecase.GetMachineListUseCase
import com.example.myapplication.presentation.machinelist.MachineListViewModel
import com.example.myapplication.presentation.machinedetail.MachineDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import com.example.myapplication.data.remote.SensorApi
import com.example.myapplication.data.repository.SensorRepositoryImpl
import com.example.myapplication.domain.repository.SensorRepository
import com.example.myapplication.presentation.sensorchart.SensorChartViewModel
import com.example.myapplication.data.remote.NotificationApi
import com.example.myapplication.data.repository.NotificationRepositoryImpl
import com.example.myapplication.domain.repository.NotificationRepository
import com.example.myapplication.domain.usecase.FilterNotificationsUseCase
import com.example.myapplication.presentation.notifications.NotificationViewModel
import com.example.myapplication.domain.usecase.RetryUseCase
import com.example.myapplication.data.remote.MaintenanceApi
import com.example.myapplication.data.repository.MaintenanceRepositoryImpl
import com.example.myapplication.domain.repository.MaintenanceRepository
import com.example.myapplication.presentation.maintenance.MaintenanceViewModel
import com.example.myapplication.domain.usecase.PrioritizeNotificationsUseCase
import com.example.myapplication.data.remote.ReportApi
import com.example.myapplication.data.repository.ReportRepositoryImpl
import com.example.myapplication.domain.repository.ReportRepository
import com.example.myapplication.domain.usecase.GetReportsUseCase
import com.example.myapplication.domain.usecase.CreateReportUseCase
import com.example.myapplication.domain.usecase.DeleteReportUseCase
import com.example.myapplication.presentation.reports.ReportFormViewModel
import com.example.myapplication.presentation.reports.ReportListViewModel
import com.example.myapplication.data.remote.EnergyApi
import com.example.myapplication.data.repository.EnergyCarbonRepositoryImpl
import com.example.myapplication.domain.repository.EnergyCarbonRepository
import com.example.myapplication.domain.usecase.GetEnergyReadingsUseCase
import com.example.myapplication.presentation.energycarbon.EnergyCarbonViewModel
import com.example.myapplication.domain.usecase.GetMaintenanceImpactUseCase
val appModule = module {
    single { MachineApi() }  // uygulama boyunca MachineApi den sadece bir tane oluştur herkes aynısını kullansın
    // singleton pattern gibi
    single<MachineRepository> { MachineRepositoryImpl(get()) }
    //"Biri MachineRepository (interface) isterse, ona MachineRepositoryImpl (gerçek implementasyon) ver."

    single { GetMachineListUseCase(get()) }
    // "Biri GetMachineListUseCase isterse, ona ver, ihtiyacı olan MachineRepository'i otomatik bul."

    viewModel { MachineListViewModel(get(), get(), get()) }
    //"biri MachineListViewModel isterse, ona bunu ver, ihtiyacı olan MachineRepository ve
    // GetMachineListUseCase'i otomatik bul."

    viewModel { (machineId: String) -> MachineDetailViewModel(machineId, get()) }
    //"biri MachineDetailViewModel isterse, dışarıdan verdiği machineId'yi kullan,
    // MachineRepository'i otomatik bul."

    single { SensorApi() }
    single<SensorRepository> { SensorRepositoryImpl(get()) }

    viewModel { (machineId: String) -> SensorChartViewModel(machineId, get()) }

    single { NotificationApi() }
    single<NotificationRepository> { NotificationRepositoryImpl(get()) }
    single { FilterNotificationsUseCase() }
    viewModel { NotificationViewModel(get(), get(), get()) }

    single { RetryUseCase() }

    single { MaintenanceApi() }
    single<MaintenanceRepository> { MaintenanceRepositoryImpl(get()) }

    viewModel { MaintenanceViewModel(get()) }

    single { PrioritizeNotificationsUseCase() }


    single { ReportApi() }
    single<ReportRepository> { ReportRepositoryImpl(get()) }
    factory { GetReportsUseCase(get()) }
    factory { CreateReportUseCase(get()) }
    factory { DeleteReportUseCase(get()) }
    viewModel { ReportFormViewModel(get()) }
    viewModel { ReportListViewModel(getReportsUseCase = get(), deleteReportUseCase = get()) }


    single { EnergyApi() }
    single<EnergyCarbonRepository> { EnergyCarbonRepositoryImpl(get()) }
    single { GetEnergyReadingsUseCase(get()) }
    single { GetMaintenanceImpactUseCase(get()) }
    viewModel { EnergyCarbonViewModel(get(), get()) }




}

