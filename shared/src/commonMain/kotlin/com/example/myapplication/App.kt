package com.example.myapplication


import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.savedstate.read
import com.example.myapplication.di.appModule
import com.example.myapplication.navigation.Routes
import org.koin.compose.KoinApplication
import com.example.myapplication.presentation.machinelist.MachineListScreen
import com.example.myapplication.presentation.login.LoginScreen
import com.example.myapplication.presentation.dashboard.DashboardScreen
import com.example.myapplication.ui.theme.AppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.myapplication.presentation.machinedetail.MachineDetailScreen
import com.example.myapplication.presentation.sensorchart.SensorChartScreen
import com.example.myapplication.presentation.notifications.NotificationScreen
import com.example.myapplication.presentation.maintenance.MaintenanceScreen
import com.example.myapplication.presentation.reports.ReportListScreen
import com.example.myapplication.presentation.reports.ReportFormScreen
import com.example.myapplication.presentation.energycarbon.EnergyCarbonScreen
import com.example.myapplication.presentation.settings.SettingsScreen

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(appModule)
    }) {
        var isDarkTheme by remember { mutableStateOf(false) }
        var isTurkish by remember { mutableStateOf(true) }

        AppTheme(isDarkTheme = isDarkTheme, isTurkish = isTurkish) {
            val navController = rememberNavController()

            // startDestination'ı Routes.Login.route yaparak ilk ekranı giriş ekranı olarak ayarladık
            NavHost(navController = navController, startDestination = Routes.Login.route) {

                composable(Routes.Login.route) {
                    LoginScreen(
                        onLoginSuccess = {
                            //  Giriş başarılı olunca kullanıcıyı doğrudan Dashboard (Ana Panel) ekranına yönlendiriyoruz
                            navController.navigate(Routes.Dashboard.route) {
                                // Geri butonuna basıldığında tekrar giriş ekranına dönmesin diye backstack'ten uçuruyoruz
                                popUpTo(Routes.Login.route) { inclusive = true }
                            }
                        }
                    )
                }

                composable(Routes.Dashboard.route) {
                    DashboardScreen(
                        onNavigateToMachines = {
                            navController.navigate(Routes.MachineList.route)
                        },
                        onNavigateToWelds = {
                            navController.navigate(Routes.Notifications.route)
                        },
                        onNavigateToMaintenance = {
                            navController.navigate(Routes.Maintenance.route)
                        },
                        onNavigateToReports = {
                            navController.navigate(Routes.ReportList.route)
                        },
                        onNavigateToEnergyCarbon = {
                            navController.navigate(Routes.EnergyCarbon.route)     
                        },
                        onNavigateToSettings = {
                            navController.navigate(Routes.Settings.route)
                        }

                    )
                }

                composable(Routes.MachineList.route) {
                    MachineListScreen(
                        onMachineClick = { machine ->
                            navController.navigate(Routes.MachineDetail.createRoute(machine.serialNumber))
                        }
                    )
                }

                composable(Routes.MachineDetail.route) { backStackEntry ->
                    val machineId = backStackEntry.arguments?.read { getString("machineId") } ?: ""
                    MachineDetailScreen(machineId = machineId)
                }

                composable(Routes.Notifications.route) {
                    NotificationScreen()
                }

                composable(Routes.Maintenance.route) {
                    MaintenanceScreen()
                }

                composable(Routes.ReportList.route) {
                    ReportListScreen(
                        onCreateReport = {
                            navController.navigate(Routes.ReportForm.route)
                        },
                        onOpenReport = {
                            // Rapor detay ekranı eklenince buraya yönlendirme eklenecek
                        }
                    )
                }

                composable(Routes.ReportForm.route) {
                    ReportFormScreen(
                        onBack = { navController.popBackStack() },
                        onReportCreated = { navController.popBackStack() }
                    )
                }

                composable(Routes.EnergyCarbon.route) {
                    EnergyCarbonScreen()
                }

                composable(Routes.Settings.route) {
                    SettingsScreen(
                        isDarkTheme = isDarkTheme,
                        isTurkish = isTurkish,
                        onToggleDarkTheme = { isDarkTheme = it },
                        onToggleLanguage = { isTurkish = it }
                    )
                }

            }
        }
    }
}