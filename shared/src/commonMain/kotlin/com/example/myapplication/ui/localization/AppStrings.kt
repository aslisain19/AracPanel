package com.example.myapplication.ui.localization

interface AppStrings {
    val appName: String
    val loginButton: String
    val welcomeMessage: String
    val usernamePlaceholder: String
    val passwordPlaceholder: String
    val dashboardTitle: String
    val activeMachinesLabel: String
    val activeAlarmsLabel: String
    val todayWeldDurationLabel: String
    val totalWeldRecordsLabel: String
    val quickAccessTitle: String
    val machineListButton: String
    val weldHistoryButton: String
    val maintenanceButton: String
    val reportsButton: String
    val energyCarbonButton: String
    val settingsButton: String
    val machineListTitle: String
    val onlineStatusLabel: String
    val offlineStatusLabel: String
    val maintenanceTitle: String
    val dailyAlertCountTitle: String
    val workOrdersTitle: String
    val predictiveMaintenanceTitle: String
    val serialNumberLabel: String
    val durationRatioLabel: String
    val planningDateLabel: String
    val machineLabel: String
    val remainingLifeLabel: String
    val predictedFailureLabel: String
    val workOrderPlanned: String
    val workOrderInProgress: String
    val workOrderCompleted: String
    val riskHigh: String
    val riskMedium: String
    val riskLow: String
    val noWorkOrdersMessage: String
    val energyCarbonTitle: String
    val periodDaily: String
    val periodWeekly: String
    val periodMonthly: String
    val sourceAll: String
    val sourceElectricity: String
    val sourceGas: String
    val sourceWater: String
    val totalConsumptionLabel: String
    val carbonFootprintLabel: String
    val vsPreviousPeriodLabel: String
    val treeEquivalentLabel: String
    val consumptionChartTitle: String
    val machineDetailTitle: String
    val locationLabel: String
    val modelLabel: String
    val oeeLabel: String
    val workingHoursLabel: String
    val lastMaintenanceLabel: String
    val statusLabel: String
    val machineNotFoundMessage: String
    val notificationsTitle: String
    val filterAll: String
    val filterCritical: String
    val filterWarning: String
    val filterInfo: String
    val acknowledgedLabel: String
    val acknowledgeButton: String
    val noNotificationsMessage: String
    fun alarmMessageFor(errorCode: String): String
    val reportsTitle: String
    val searchPlaceholder: String
    val noRecordsMessage: String
    val createReportButton: String
    val deleteMenuItem: String
    val moreOptionsDescription: String
    val showingPaginationLabel: String
    val newReportTitle: String
    val nameLabel: String
    val groupByXLabel: String
    val groupByYLabel: String
    val displayValueLabel: String
    val notificationChannelsLabel: String
    val emailChannelLabel: String
    val smsChannelLabel: String
    val pushChannelLabel: String
    val dateRangeLabel: String
    val scheduledLabel: String
    val frequencyLabel: String
    val scheduledTimeLabel: String
    val selectPlaceholder: String
    val cancelButton: String
    val createButton: String
    val nameRequiredError: String
    val fieldRequiredError: String
    val scheduledTimeRequiredError: String
    val reportCreationFailedError: String
    fun groupByLabel(option: com.example.myapplication.domain.model.GroupByOption): String
    fun displayValueLabel(value: com.example.myapplication.domain.model.DisplayValue): String
    fun dateRangeLabel(kind: com.example.myapplication.domain.model.DateRangeKind): String
    fun frequencyLabel(freq: com.example.myapplication.domain.model.ReportFrequency): String
    val invalidCredentialsError: String
    val temperatureChartTitle: String
    val noSensorDataMessage: String
    val hoursUnit: String
    val treesPerYearUnit: String
    val settingsTitle: String
    val darkThemeLabel: String
    val darkThemeDescription: String
    val turkishLabel: String
    val turkishDescription: String
    fun energyLabelFor(rawLabel: String): String
    val maintenanceImpactLabel: String
    val fuelSavingLabel: String
    val carbonReductionLabel: String
    val ecoRecommendationLabel: String
    val estimatedFuelSavingLabel: String
    val estimatedCarbonReductionLabel: String
    val sinceLastMaintenanceLabel: String
    val temperatureLabel: String
    val maintenanceImpactTitle: String
    val lastServiceLabel: String
    val nextMaintenanceLabel: String
    val tirePressureLabel: String
    val tirePressureOk: String
    val tirePressureLow: String
    val tirePressureHigh: String
}

object TrStrings : AppStrings {
    override val appName = "Dijital İkiz"
    override val loginButton = "Giriş Yap"
    override val welcomeMessage = "Endüstriyel İzleme Sistemine Hoş Geldiniz"
    override val usernamePlaceholder = "Kullanıcı Adı"
    override val passwordPlaceholder = "Şifre"
    override val dashboardTitle = "Kontrol Paneli"
    override val activeMachinesLabel = "Aktif Makineler"
    override val activeAlarmsLabel = "Aktif Alarmlar"
    override val todayWeldDurationLabel = "Bugünkü Kaynak Süresi"
    override val totalWeldRecordsLabel = "Toplam Kaynak Kaydı"
    override val quickAccessTitle = "Hızlı Erişim"
    override val machineListButton = "Makineleri Listele (Live View / Overview)"
    override val weldHistoryButton = "Kaynak Geçmişini Görüntüle"
    override val maintenanceButton = "Kestirimci Bakım"
    override val reportsButton = "Raporlar"
    override val energyCarbonButton = "Enerji & Karbon İzleme"
    override val settingsButton = "Ayarlar"
    override val machineListTitle = "Makineler"
    override val onlineStatusLabel = "Çevrimiçi"
    override val offlineStatusLabel = "Çevrimdışı"
    override val maintenanceTitle = "Bakım"
    override val dailyAlertCountTitle = "Günlük Alarm Sayısı"
    override val workOrdersTitle = "Bakım İş Emirleri"
    override val predictiveMaintenanceTitle = "Kestirimci Bakım"
    override val serialNumberLabel = "Seri No"
    override val durationRatioLabel = "Süre Oranı"
    override val planningDateLabel = "Planlama Tarihi"
    override val machineLabel = "Makine"
    override val remainingLifeLabel = "Kalan Ömür"
    override val predictedFailureLabel = "Tahmini Arıza"
    override val workOrderPlanned = "Planlandı"
    override val workOrderInProgress = "Devam Ediyor"
    override val workOrderCompleted = "Tamamlandı"
    override val riskHigh = "Yüksek Risk"
    override val riskMedium = "Orta Risk"
    override val riskLow = "Düşük Risk"
    override val noWorkOrdersMessage = "İş emri bulunamadı"
    override val energyCarbonTitle = "Enerji & Karbon İzleme"
    override val periodDaily = "Günlük"
    override val periodWeekly = "Haftalık"
    override val periodMonthly = "Aylık"
    override val sourceAll = "Tümü"
    override val sourceElectricity = "Yakıt"
    override val sourceGas = "LPG/Dizel"
    override val sourceWater = "Bakım"
    override val totalConsumptionLabel = "Yakıt Tüketimi"
    override val carbonFootprintLabel = "CO₂ Emisyonu"
    override val vsPreviousPeriodLabel = "Yakıt Verimliliği"
    override val treeEquivalentLabel = "Ağaç Eşdeğeri"
    override val consumptionChartTitle = "Yakıt Tüketimi (L)"
    override val machineDetailTitle = "Makine Detayı"
    override val locationLabel = "Konum"
    override val modelLabel = "Model"
    override val oeeLabel = "OEE"
    override val workingHoursLabel = "Çalışma Saati"
    override val lastMaintenanceLabel = "Son Bakım"
    override val statusLabel = "Durum"
    override val machineNotFoundMessage = "Makine bulunamadı"
    override val notificationsTitle = "Bildirimler"
    override val filterAll = "Tümü"
    override val filterCritical = "Kritik"
    override val filterWarning = "Uyarı"
    override val filterInfo = "Bilgi"
    override val acknowledgedLabel = "Onaylandı"
    override val acknowledgeButton = "Onayla"
    override val noNotificationsMessage = "Bildirim bulunamadı"
    override fun alarmMessageFor(errorCode: String): String = when (errorCode) {
        "256" -> "Aşırı akım tespit edildi"
        "E4" -> "Gaz akışı düşük"
        "E5" -> "Kalibrasyon önerilir"
        "268" -> "Sıcaklık limiti aşıldı"
        "264" -> "Tel besleme sorunu"
        else -> errorCode
    }

    override val reportsTitle = "Raporlar"
    override val searchPlaceholder = "Ara..."
    override val noRecordsMessage = "Kayıt bulunamadı"
    override val createReportButton = "Rapor Oluştur"
    override val deleteMenuItem = "Sil"
    override val moreOptionsDescription = "Diğer"
    override val showingPaginationLabel = "Gösteriliyor"
    override val newReportTitle = "Yeni Rapor Oluştur"
    override val nameLabel = "Ad"
    override val groupByXLabel = "Grupla (X ekseni)"
    override val groupByYLabel = "Grupla (Y ekseni)"
    override val displayValueLabel = "Gösterilecek Değer"
    override val notificationChannelsLabel = "Bildirim Kanalları"
    override val emailChannelLabel = "E-posta"
    override val smsChannelLabel = "SMS"
    override val pushChannelLabel = "Bildirim"
    override val dateRangeLabel = "Tarih Aralığı"
    override val scheduledLabel = "Programlanmış"
    override val frequencyLabel = "Sıklık"
    override val scheduledTimeLabel = "Programlanmış Zaman"
    override val selectPlaceholder = "Seçiniz..."
    override val cancelButton = "İptal"
    override val createButton = "Oluştur"
    override val nameRequiredError = "Ad zorunludur"
    override val fieldRequiredError = "Bu alan zorunludur"
    override val scheduledTimeRequiredError = "Programlanmış zaman seçin"
    override val reportCreationFailedError = "Rapor oluşturulamadı"
    override fun groupByLabel(option: com.example.myapplication.domain.model.GroupByOption): String = when (option) {
        com.example.myapplication.domain.model.GroupByOption.LOCATION -> "Konum"
        com.example.myapplication.domain.model.GroupByOption.ONE_DAY -> "Gün"
        com.example.myapplication.domain.model.GroupByOption.ONE_HOUR -> "Saat"
        com.example.myapplication.domain.model.GroupByOption.MACHINE -> "Makine"
    }
    override fun displayValueLabel(value: com.example.myapplication.domain.model.DisplayValue): String = when (value) {
        com.example.myapplication.domain.model.DisplayValue.GAS_CONSUMPTION -> "Gaz Tüketimi"
        com.example.myapplication.domain.model.DisplayValue.ELECTRICITY_CONSUMPTION -> "Elektrik Tüketimi"
        com.example.myapplication.domain.model.DisplayValue.WATER_CONSUMPTION -> "Su Tüketimi"
    }
    override fun dateRangeLabel(kind: com.example.myapplication.domain.model.DateRangeKind): String = when (kind) {
        com.example.myapplication.domain.model.DateRangeKind.TODAY -> "Bugün"
        com.example.myapplication.domain.model.DateRangeKind.LAST_WEEK -> "Geçen Hafta"
        com.example.myapplication.domain.model.DateRangeKind.LAST_MONTH -> "Geçen Ay"
        com.example.myapplication.domain.model.DateRangeKind.CUSTOM -> "Özel Aralık"
    }
    override fun frequencyLabel(freq: com.example.myapplication.domain.model.ReportFrequency): String = when (freq) {
        com.example.myapplication.domain.model.ReportFrequency.DAILY -> "Günlük"
        com.example.myapplication.domain.model.ReportFrequency.WEEKLY -> "Haftalık"
        com.example.myapplication.domain.model.ReportFrequency.MONTHLY -> "Aylık"
    }

    override val invalidCredentialsError = "Hatalı kullanıcı adı veya şifre!"

    override val temperatureChartTitle = "Sıcaklık (°C)"
    override val noSensorDataMessage = "Sensör verisi bulunamadı"
    override val hoursUnit = "saat"
    override val treesPerYearUnit = "ağaç/yıl"
    override val settingsTitle = "Ayarlar"
    override val darkThemeLabel = "Koyu Tema"
    override val darkThemeDescription = "Uygulamayı koyu renk şemasıyla kullan"
    override val turkishLabel = "Türkçe"
    override val turkishDescription = "Kapalıyken uygulama dili İngilizce olur"
    override fun energyLabelFor(rawLabel: String): String = rawLabel
    override val maintenanceImpactLabel = "Bakım Etkisi"
    override val fuelSavingLabel = "Yakıt Tasarrufu"
    override val carbonReductionLabel = "Karbon Azalımı"
    override val ecoRecommendationLabel = "Çevre Dostu Öneri"
    override val estimatedFuelSavingLabel = "Tahmini Yakıt Tasarrufu"
    override val estimatedCarbonReductionLabel = "Tahmini CO₂ Azalımı"
    override val sinceLastMaintenanceLabel = "Son bakımdan bu yana"
    override val temperatureLabel = "Sıcaklık İzleme"
    override val maintenanceImpactTitle = "Bakım Etkisi"
    override val lastServiceLabel = "Son Bakım"
    override val nextMaintenanceLabel = "Sıradaki Bakım"
    override val tirePressureLabel = "Lastik Basıncı"
    override val tirePressureOk = "Normal"
    override val tirePressureLow = "Düşük"
    override val tirePressureHigh = "Yüksek"

}

object EnStrings : AppStrings {
    override val appName = "Digital Twin"
    override val loginButton = "Login"
    override val welcomeMessage = "Welcome to Industrial Monitoring System"
    override val usernamePlaceholder = "Username"
    override val passwordPlaceholder = "Password"
    override val dashboardTitle = "Dashboard"
    override val activeMachinesLabel = "Active Machines"
    override val activeAlarmsLabel = "Active Alarms"
    override val todayWeldDurationLabel = "Today's Weld Duration"
    override val totalWeldRecordsLabel = "Total Weld Records"
    override val quickAccessTitle = "Quick Access"
    override val machineListButton = "List Machines (Live View / Overview)"
    override val weldHistoryButton = "View Weld History"
    override val maintenanceButton = "Predictive Maintenance"
    override val reportsButton = "Reports"
    override val energyCarbonButton = "Energy & Carbon Monitoring"
    override val settingsButton = "Settings"
    override val machineListTitle = "Machines"
    override val onlineStatusLabel = "Online"
    override val offlineStatusLabel = "Offline"
    override val maintenanceTitle = "Maintenance"
    override val dailyAlertCountTitle = "Daily Alert Count"
    override val workOrdersTitle = "Maintenance Work Orders"
    override val predictiveMaintenanceTitle = "Predictive Maintenance"
    override val serialNumberLabel = "Serial No"
    override val durationRatioLabel = "Duration Ratio"
    override val planningDateLabel = "Planning Date"
    override val machineLabel = "Machine"
    override val remainingLifeLabel = "Remaining Life"
    override val predictedFailureLabel = "Predicted Failure"
    override val workOrderPlanned = "Planned"
    override val workOrderInProgress = "In Progress"
    override val workOrderCompleted = "Completed"
    override val riskHigh = "High Risk"
    override val riskMedium = "Medium Risk"
    override val riskLow = "Low Risk"
    override val noWorkOrdersMessage = "No work orders found"
    override val energyCarbonTitle = "Energy & Carbon Monitoring"
    override val periodDaily = "Daily"
    override val periodWeekly = "Weekly"
    override val periodMonthly = "Monthly"
    override val sourceAll = "All"
    override val sourceElectricity = "Fuel"
    override val sourceGas = "LPG/Diesel"
    override val sourceWater = "Maintenance"
    override val totalConsumptionLabel = "Fuel Consumption"
    override val carbonFootprintLabel = "CO₂ Emission"
    override val vsPreviousPeriodLabel = "Fuel Efficiency"
    override val treeEquivalentLabel = "Tree Equivalent"
    override val consumptionChartTitle = "Fuel Consumption (L)"
    override val machineDetailTitle = "Machine Detail"
    override val locationLabel = "Location"
    override val modelLabel = "Model"
    override val oeeLabel = "OEE"
    override val workingHoursLabel = "Working Hours"
    override val lastMaintenanceLabel = "Last Maintenance"
    override val statusLabel = "Status"
    override val machineNotFoundMessage = "Machine not found"
    override val notificationsTitle = "Notifications"
    override val filterAll = "All"
    override val filterCritical = "Critical"
    override val filterWarning = "Warning"
    override val filterInfo = "Info"
    override val acknowledgedLabel = "Acknowledged"
    override val acknowledgeButton = "Acknowledge"
    override val noNotificationsMessage = "No notifications found"
    override fun alarmMessageFor(errorCode: String): String = when (errorCode) {
        "256" -> "Overcurrent detected"
        "E4" -> "Low gas flow"
        "E5" -> "Calibration recommended"
        "268" -> "Temperature limit exceeded"
        "264" -> "Wire feed issue"
        else -> errorCode
    }

    override val reportsTitle = "Reports"
    override val searchPlaceholder = "Search..."
    override val noRecordsMessage = "No records found"
    override val createReportButton = "Create Report"
    override val deleteMenuItem = "Delete"
    override val moreOptionsDescription = "More"
    override val showingPaginationLabel = "Showing"
    override val newReportTitle = "Create New Report"
    override val nameLabel = "Name"
    override val groupByXLabel = "Group By (X Axis)"
    override val groupByYLabel = "Group By (Y Axis)"
    override val displayValueLabel = "Display Value"
    override val notificationChannelsLabel = "Notification Channels"
    override val emailChannelLabel = "Email"
    override val smsChannelLabel = "SMS"
    override val pushChannelLabel = "Push"
    override val dateRangeLabel = "Date Range"
    override val scheduledLabel = "Scheduled"
    override val frequencyLabel = "Frequency"
    override val scheduledTimeLabel = "Scheduled Time"
    override val selectPlaceholder = "Select..."
    override val cancelButton = "Cancel"
    override val createButton = "Create"
    override val nameRequiredError = "Name is required"
    override val fieldRequiredError = "This field is required"
    override val scheduledTimeRequiredError = "Please select a scheduled time"
    override val reportCreationFailedError = "Failed to create report"
    override fun groupByLabel(option: com.example.myapplication.domain.model.GroupByOption): String = when (option) {
        com.example.myapplication.domain.model.GroupByOption.LOCATION -> "Location"
        com.example.myapplication.domain.model.GroupByOption.ONE_DAY -> "Day"
        com.example.myapplication.domain.model.GroupByOption.ONE_HOUR -> "Hour"
        com.example.myapplication.domain.model.GroupByOption.MACHINE -> "Machine"
    }
    override fun displayValueLabel(value: com.example.myapplication.domain.model.DisplayValue): String = when (value) {
        com.example.myapplication.domain.model.DisplayValue.GAS_CONSUMPTION -> "Gas Consumption"
        com.example.myapplication.domain.model.DisplayValue.ELECTRICITY_CONSUMPTION -> "Electricity Consumption"
        com.example.myapplication.domain.model.DisplayValue.WATER_CONSUMPTION -> "Water Consumption"
    }
    override fun dateRangeLabel(kind: com.example.myapplication.domain.model.DateRangeKind): String = when (kind) {
        com.example.myapplication.domain.model.DateRangeKind.TODAY -> "Today"
        com.example.myapplication.domain.model.DateRangeKind.LAST_WEEK -> "Last Week"
        com.example.myapplication.domain.model.DateRangeKind.LAST_MONTH -> "Last Month"
        com.example.myapplication.domain.model.DateRangeKind.CUSTOM -> "Custom Range"
    }
    override fun frequencyLabel(freq: com.example.myapplication.domain.model.ReportFrequency): String = when (freq) {
        com.example.myapplication.domain.model.ReportFrequency.DAILY -> "Daily"
        com.example.myapplication.domain.model.ReportFrequency.WEEKLY -> "Weekly"
        com.example.myapplication.domain.model.ReportFrequency.MONTHLY -> "Monthly"
    }

    override val invalidCredentialsError = "Invalid username or password!"

    override val temperatureChartTitle = "Temperature (°C)"
    override val noSensorDataMessage = "No sensor data found"
    override val hoursUnit = "hours"
    override val treesPerYearUnit = "trees/year"
    override val settingsTitle = "Settings"
    override val darkThemeLabel = "Dark Theme"
    override val darkThemeDescription = "Use the app with a dark color scheme"
    override val turkishLabel = "Turkish"
    override val turkishDescription = "When off, the app language becomes English"
    override fun energyLabelFor(rawLabel: String): String = when (rawLabel) {
        "Pzt" -> "Mon"
        "Sal" -> "Tue"
        "Çar" -> "Wed"
        "Per" -> "Thu"
        "Cum" -> "Fri"
        "Cmt" -> "Sat"
        "Paz" -> "Sun"
        "Hafta 1" -> "Week 1"
        "Hafta 2" -> "Week 2"
        "Hafta 3" -> "Week 3"
        "Hafta 4" -> "Week 4"
        else -> rawLabel
    }
    override val maintenanceImpactLabel = "Maintenance Impact"
    override val fuelSavingLabel = "Fuel Saving"
    override val carbonReductionLabel = "Carbon Reduction"
    override val ecoRecommendationLabel = "Eco Recommendation"
    override val estimatedFuelSavingLabel = "Estimated Fuel Saving"
    override val estimatedCarbonReductionLabel = "Estimated CO₂ Reduction"
    override val sinceLastMaintenanceLabel = "Since last maintenance"
    override val temperatureLabel = "Temperature Monitoring"
    override val maintenanceImpactTitle = "Maintenance Impact"
    override val lastServiceLabel = "Last Service"
    override val nextMaintenanceLabel = "Next Maintenance"
    override val tirePressureLabel = "Tire Pressure"
    override val tirePressureOk = "OK"
    override val tirePressureLow = "Low"
    override val tirePressureHigh = "High"
}