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
