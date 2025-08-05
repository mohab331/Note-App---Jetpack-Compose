# 📝 Note App — Jetpack Compose Note App

A modern, clean, and fully offline-capable note-taking app built with Jetpack Compose and structured around best practices like MVVM, Hilt, Room, and StateFlow.

---

## 🚀 Tech Highlights

- 💾 **Room Database**  
  Local persistence with `@Entity`, `@Dao`, and `@Database`. Notes are stored in a SQLite-backed Room database with support for custom types via `@TypeConverters`.

- 📦 **Hilt Dependency Injection**  
  Modular and testable setup using `@HiltViewModel`, `@Inject`, `@Provides`, and `@InstallIn` for constructor-based DI in ViewModels and Repositories.

- 🔄 **Reactive State with StateFlow**  
  UI is bound to state using `StateFlow` in `ViewModel`, and `collectAsState()` in Compose for automatic recomposition on state change.

- 🎨 **Jetpack Compose UI**  
  Built entirely with Jetpack Compose — declarative, flexible, and modern UI toolkit using `LazyColumn`, `Card`, and `Material3` components.

- 🔐 **ViewModel (Lifecycle-Aware)**  
  `@HiltViewModel` handles screen logic and exposes reactive state safely across recompositions and configuration changes.
---

## 🧱 Project Layers

| Layer           | Technology        |
|----------------|-------------------|
| UI              | Jetpack Compose   |
| State Management| ViewModel + StateFlow |
| Persistence     | Room Database     |
| Dependency DI   | Hilt              |

---

## 📁 Folder Structure

