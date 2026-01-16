# Android Project Setup Guide

## Quick Start

This directory contains a complete Android project structure for Florida Learning Stars app, converted from React/TypeScript to native Android (Kotlin + Jetpack Compose).

## Directory Structure

```
FloridaLearningStars/
├── ANDROID_PROJECT_README.md      # Complete project documentation
├── Models.kt                       # Data models and serialization
├── QuestionsRepository.kt          # Question database and business logic
├── LearningViewModel.kt            # MVVM ViewModel for state management
├── MainActivity.kt                 # Main activity and navigation setup
├── HomeScreen.kt                   # Home and avatar selection screens
├── QuizScreen.kt                   # Quiz interface and components
├── Theme.kt                        # Material Design 3 theming
├── Typography.kt                   # Typography definitions
├── AndroidManifest.xml             # App manifest configuration
└── build.gradle.kts               # Gradle build configuration
```

## Setup Instructions

### 1. Create Android Studio Project Structure

In Android Studio:
1. Create new Android project
2. Choose **"Empty Activity"** template
3. Set project name: `FloridaLearningApp`
4. Set package: `com.floridalearning.stars`
5. Minimum SDK: API 24
6. Select Kotlin as language

### 2. Configure build.gradle.kts

Replace the main `build.gradle.kts` with the provided file.

### 3. Copy Source Files

Create the following directory structure and copy files:

```
app/src/main/java/com/floridalearning/stars/
├── MainActivity.kt
├── data/
│   ├── Models.kt
│   └── QuestionsRepository.kt
├── ui/
│   ├── LearningViewModel.kt
│   ├── theme/
│   │   ├── Theme.kt
│   │   └── Typography.kt
│   └── screens/
│       ├── HomeScreen.kt
│       └── QuizScreen.kt
```

### 4. Update AndroidManifest.xml

Copy the provided `AndroidManifest.xml` to `app/src/main/AndroidManifest.xml`

### 5. Sync and Build

1. File → Sync Now
2. Build → Clean Project
3. Build → Build Project

## Running the App

### On Emulator
1. Tools → Device Manager
2. Create or select a virtual device (Android 8.0+)
3. Click Run button
4. Select the emulator

### On Physical Device
1. Enable Developer Mode (tap Build Number 7 times)
2. Enable USB Debugging in Developer Options
3. Connect device via USB
4. Accept USB debugging prompt on device
5. Click Run button
6. Select the physical device

## Architecture Overview

### MVVM Pattern
- **Model**: Data classes in `Models.kt`
- **View**: Compose screens (`HomeScreen.kt`, `QuizScreen.kt`)
- **ViewModel**: `LearningViewModel.kt` - manages UI state

### State Management
- Uses `StateFlow` for reactive state
- ViewModel survives configuration changes
- Compose automatically recomposes on state changes

### Navigation
- Jetpack Navigation Compose
- Routes: home → avatar_selection → subject_selection → quiz → completion

## Key Components

### 1. Models (Models.kt)
```kotlin
data class Question(...)      // Quiz questions
data class Topic(...)         // Learning topics
data class UserProgress(...)  // Student progress
```

### 2. Repository (QuestionsRepository.kt)
- Central data source
- Contains all questions and topics
- Provides query functions
- Can be replaced with database/API

### 3. ViewModel (LearningViewModel.kt)
- Manages application state
- Handles user interactions
- Preserves state across configuration changes

### 4. Screens
- **HomeScreen**: Grade and avatar selection
- **QuizScreen**: Question display and answering

## Customization

### Add New Questions
In `QuestionsRepository.kt`, add to `QUESTIONS` list:
```kotlin
Question(
    id = "unique_id",
    subject = "math",
    grade = 0,
    topicId = "topic_id",
    question = "Your question?",
    options = listOf("A", "B", "C", "D"),
    correct = 0,
    explanation = "Explanation",
    stars = 2
)
```

### Change Theme Colors
In `Theme.kt`:
```kotlin
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF0061CA),        // Change here
    secondary = Color(0xFF0078D7),      // Change here
    // ...
)
```

### Add New Screens
1. Create new file in `ui/screens/`
2. Add navigation route in `MainActivity.kt`
3. Update NavHost composable

## Debugging

### View Logs
1. Android Studio → Logcat
2. Filter by app package: `com.floridalearning.stars`

### Debug Mode
1. Run → Debug 'app'
2. Set breakpoints in code
3. Use debug controls to step through

### Compose Preview
For compose files, use @Preview:
```kotlin
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(viewModel = LearningViewModel()) { }
}
```

## Dependencies

Already configured in `build.gradle.kts`:
- Jetpack Compose (UI)
- Navigation Compose (Navigation)
- Lifecycle components (ViewModel)
- Material Design 3
- Kotlin Serialization

## Build Variants

### Debug (Development)
```bash
./gradlew assembleDebug
```
- Includes debugging symbols
- Slower startup
- Full logging

### Release (Production)
```bash
./gradlew assembleRelease
```
- Optimized and minified
- Faster execution
- Smaller APK size

## Gradle Commands

```bash
# Clean build
./gradlew clean

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Install on connected device
./gradlew installDebug

# Run tests
./gradlew test

# Build and run on emulator
./gradlew connectedAndroidTest
```

## Common Issues

### Gradle Sync Fails
- Update Android Studio to latest version
- Update Gradle to latest version
- Delete `.gradle` folder
- Sync again

### Compose Runtime Error
- Ensure Kotlin version matches Compose version
- Update all dependencies to compatible versions
- Clear build cache

### App Crashes on Startup
- Check Logcat for exception
- Verify all imports are correct
- Check that all classes are in correct packages

## Performance Tips

1. **Lazy Loading**
   - Use `LazyColumn` and `LazyVerticalGrid`
   - Only recompose changed UI

2. **State Management**
   - Keep state in ViewModel
   - Minimize state hoisting

3. **Memory**
   - Use Kotlin's nullable types
   - Clear large data structures

## Next Steps

1. Install Android Studio if not already done
2. Follow setup instructions above
3. Run app on emulator/device
4. Customize content as needed
5. Build and deploy

## Resources

- [Android Developer Guide](https://developer.android.com/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)

## Support

For issues:
1. Check Android Studio Logcat
2. Review error messages carefully
3. Search Android documentation
4. Check Stack Overflow

---

**Project**: Florida Learning Stars  
**Technology**: Kotlin + Jetpack Compose  
**Min API**: 24  
**Target API**: 34
