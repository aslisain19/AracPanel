# AracPanel

A vehicle maintenance and monitoring mobile application built with Kotlin Multiplatform (KMP) and Compose Multiplatform. Structured around Clean Architecture, MVVM, and TDD principles.

## 📱 Features

- **Dashboard** — Active vehicle/machine count, alarm status, and quick access menu
- **Machine/Vehicle List & Detail** — Live status tracking, OEE, working hours, temperature chart
- **Notifications** — Severity-filterable alarm list with acknowledge flow
- **Predictive Maintenance** — Daily alert chart, work orders, failure prediction
- **Reporting** — Customizable report creation, listing, and scheduling
- **Fuel & Emissions Monitoring** — Period/source-based fuel consumption and CO₂ emission charts, maintenance impact card
- **Settings** — Dark/light theme and Turkish/English language support

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| UI | Jetpack Compose / Compose Multiplatform, Material 3 |
| Architecture | Clean Architecture (domain / data / presentation) + MVVM |
| Dependency Injection | Koin |
| Networking | Ktor Client (mocked via MockEngine) |
| Charts | Vico (Cartesian Charts) |
| Navigation | Jetpack Navigation Compose |
| Testing | Kotlin Test, Coroutines Test, Turbine, MockK |
| Serialization | Kotlinx Serialization |

## 🏗️ Architecture

The project follows Clean Architecture, with dependencies flowing inward:
```
presentation/  → UI, ViewModel, UiState (Compose)
domain/        → Models, Repository interfaces, UseCases (pure Kotlin, framework-agnostic)
data/          → DTOs, Mappers, Repository implementations, mock APIs
di/            → Koin module (wires the layers together)
```

Each feature (`reports`, `energycarbon`, `maintenance`, etc.) is spread across these four layers, keeping the codebase testable and features independent of one another.

## 🧪 Testing

Use cases and mappers in the domain layer are developed test-first (TDD), using fake repository implementations. Tests live under `shared/src/commonTest`.

## 🚀 Running the App

**Android:**

```
./gradlew :androidApp:assembleDebug
```

or run it from Android Studio using the ▶️ Run button.

**iOS:**
Open the `/iosApp` directory in Xcode and run it from there.

## 📂 Project Structure
```
androidApp/    → Android entry point
iosApp/        → iOS entry point (SwiftUI)
shared/        → Cross-platform shared code (UI + business logic)
  └── commonMain/kotlin/com/example/myapplication/
      ├── domain/
      ├── data/
      ├── di/
      ├── presentation/
      └── navigation/
```
## 📸 Screenshots

<table>
  <tr>
    <td><img width="432" alt="screenshot" src="https://github.com/user-attachments/assets/7154d946-f9b4-4f8f-ad68-e7d8be05248e" /></td>
    <td><img width="402" alt="screenshot" src="https://github.com/user-attachments/assets/2340db73-468d-4e7c-ac8b-3f2017175988" /></td>
    <td><img width="407" alt="screenshot" src="https://github.com/user-attachments/assets/e9d8798b-3174-444b-9548-7e3207e55efd" /></td>
  </tr>
  <tr>
    <td><img width="407" alt="screenshot" src="https://github.com/user-attachments/assets/f4640399-6a81-4813-93c1-cdc5e67bdb04" /></td>
    <td><img width="402" alt="screenshot" src="https://github.com/user-attachments/assets/f3cea393-bd4f-4744-8b1b-65d27b19f59e" /></td>
    <td><img width="421" alt="screenshot" src="https://github.com/user-attachments/assets/893599ab-537a-4789-aac9-5a309aab322f" /></td>
  </tr>
</table>

