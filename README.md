# 🐱 Cat Breeds App

An Android application for cat lovers! This app allows users to explore different cat breeds and view detailed information. 
Built with modern Android technologies, it ensures a seamless and interactive user experience.

## 📌 Features

- 🐾 Browse a list of cat breeds
- ❤️ Mark breeds as favourites
- 🔍 View detailed information on each breed
- ⭐ Toggle favourite status with a single click
- 📶 **(Upcoming)** Offline functionality with Room database

## 🛠️ Technologies Used

- **Kotlin** - Modern programming language for Android development
- **Jetpack Compose** - Declarative UI framework
- **Hilt** - Dependency Injection for cleaner architecture
- **Coroutines & Flow** - Efficient asynchronous programming
- **Retrofit** - API calls made simple
- **Room (Upcoming)** - Offline storage support
- **Coil** - Image loading library for efficient image handling
- **Mockk** -  Mocking framework for unit testing

## 🏗️ Architecture

The app follows the **MVVM (Model-View-ViewModel)** pattern to maintain a clean and scalable architecture:

- **Model**: Defines data classes
- **View**: Uses Composable functions to define UI components
- **ViewModel**: Manages UI-related data and handles UI logic

## 📂 Project Structure

```
app/
├── build.gradle.kts
├── src/
│   ├── main/
│   │   ├── AndroidManifest.xml
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── challenge_sword/
│   │   │               ├── ui/
│   │   │               │   ├── components/
│   │   │               │   ├── presentation/
│   │   │               ├── domain/
│   │   │               │   ├── interactor/
│   │   │               │   ├── mapper/
│   │   │               │   └── model/
│   │   │               ├── data/
│   │   │               │   ├── model/
│   │   │               │   ├── repository/
│   │   │               │   └── service/
│   │   │               ├── di/
│   │   │               ├── navigation/
│   │   │               ├── theme/
│   │   │               ├── MyApplication.kt
│   │   │               └── MainActivity.kt
```
## 🆙 Up Next

- Add E2E tests with Appium
- Improve accessibility features
- Implement pagination
- Add more unit tests

## 🚀 Getting Started

### Prerequisites

- Android Studio (latest version recommended)
- Kotlin 1.5+
- Gradle

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/CaMaschion/challenge-sword.git
    ```
2. Open the project in **Android Studio**.
3. Sync Gradle and build the project.
4. Run the app on an emulator or a physical device.

## 🎮 Usage

1. Launch the app to browse a variety of cat breeds.
2. Click on a breed to see detailed information.
3. Tap the **favorite** icon to mark or unmark a breed as favorite.
4. Navigate to the **Favorites** section to view your saved breeds.

## 🤝 Contributing

We welcome contributions! To contribute:

1. Fork the repository.
2. Create a new branch (`feature-branch-name`).
3. Make your changes and commit them.
4. Open a **pull request**.

## 📜 License

This project is licensed under the **MIT License**. See the `LICENSE` file for more details.

---

Happy coding! 🚀🐾

