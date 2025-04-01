# 🎬 MoviesCatalog Android App

## 📖 Introduction
MoviesCatalog is a modularized Android app showcasing modern Android development with a clean architecture approach. The app fetches movie data from a remote API, organizes it with a domain layer, and presents it via a Jetpack Compose-based UI. This repository is intended as a learning tool and a real-world example of best practices in Android development.

---

## 📁 Project Structure
```
com.moviescatalog
├── app                 # Application setup and entry point
├── core                # Reusable code: DI modules, utils
├── data                # Repository, remote sources, data mappers
├── domain              # Business logic: models, use cases, interfaces
├── features            # Presentation layer: UI, ViewModels, Navigation
```

---

## 📄 Architecture
**Model-View-ViewModel (MVVM)** combined with **Clean Architecture** principles.

### ✏️ Layers:
- **Presentation (features)**: Contains screens and UI logic built with Jetpack Compose.
- **Domain**: Use cases and business rules.
- **Data**: Implements repositories and remote data source access.
- **Core**: Dependency injection (Hilt), constants, and reusable utils.

---

## 🧰 Tech Stack
| Layer | Libraries & Tools |
|-------|-------------------|
| UI | Jetpack Compose, Material3 |
| DI | Hilt |
| Networking | Retrofit, OkHttp Logging |
| Language | Kotlin (2.0.0) |
| Media | ExoPlayer (Media3) |
| Image loading | Coil |
| Tooling | KSP, Gradle Version Catalog |

---

## ⚙️ Modules Overview
### `app`
- Entry point: `MoviesCatalogApp`
- Sets up Hilt and UI theme

### `core`
- `di/`: Hilt modules: `UseCaseModule`, `RepositoryModule`
- `util/`: Constants, Theme manager, Date utilities

### `domain`
- Use cases: `GetPopularMoviesUseCase`, `GetMovieByIdUseCase`, etc.
- Repository interfaces
- Model classes (e.g. `Movie`, `MovieCategory`)

### `data`
- `remote/`: Retrofit API definitions
- `repository/`: Implements domain interfaces
- `usecase/`: Implements domain use cases (e.g. `GetPopularMoviesUseCaseImpl`)

### `features`
- Screens (presentation): movie list, movie detail, player screen
- Navigation graph
- ViewModels

---

## 🔧 Setup Instructions
1. **Clone the repository**
2. Open with **Android Studio Hedgehog or newer**
3. Make sure Java 17 is installed (toolchain is set in Gradle)
4. Add your own TMDB API Key in `core/di/UseCaseModule.kt` if needed
5. Run the app

---

## 🔄 Data Flow
1. UI requests data via ViewModel
2. ViewModel calls a UseCase (e.g. `GetPopularMoviesUseCase`)
3. UseCase invokes a method on the `MovieRepository`
4. Repository fetches data from remote source (Retrofit)
5. Response is mapped and returned to the UI

---

## 🎓 Learning Focus
This project is ideal for Android developers who want to:
- Understand modularization in real-world apps
- Apply MVVM and Clean Architecture together
- Use dependency injection (Hilt) effectively
- Build UI with Jetpack Compose
- Explore Retrofit and KSP setup with Kotlin 2.0

---


## ✅ Completed Improvements

- ✅ Added Room for local data caching
- ✅ Extended offline support (Room + Coil disk cache)
- ✅ Implemented pagination for movie lists
- ✅ Added unit tests for repository and use case layers

## 👥 Contributors
- Built by [Mahmut Yüce](https://github.com/mahmutyuce) as part of a clean architecture showcase

---

## 🚀 License
MIT License
