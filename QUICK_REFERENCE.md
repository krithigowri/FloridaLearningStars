# Android Project Quick Reference

## 📁 Files Overview

| File | Purpose | Key Classes/Functions |
|------|---------|----------------------|
| **Models.kt** | Data structures | Question, Topic, UserProgress, Badge |
| **QuestionsRepository.kt** | Question database | TOPICS, QUESTIONS, AVATARS, helper functions |
| **LearningViewModel.kt** | State management | initializeGrade(), selectAnswer(), submitAnswer() |
| **MainActivity.kt** | App entry point | Main activity, navigation setup |
| **HomeScreen.kt** | Home UI | HomeScreen(), GradeCard(), AvatarSelectionScreen() |
| **QuizScreen.kt** | Quiz UI | QuizScreen(), AnswerOptionButton(), QuizHeader() |
| **Theme.kt** | App styling | ColorScheme, Light/DarkTheme colors |
| **Typography.kt** | Text styles | Typography definitions for Material Design 3 |

## 🎯 Quick Start

```bash
# 1. Create new Android project in Android Studio
# 2. Copy all .kt files to: app/src/main/java/com/floridalearning/stars/
# 3. Copy AndroidManifest.xml to: app/src/main/AndroidManifest.xml
# 4. Replace build.gradle.kts with provided version
# 5. Sync Gradle
# 6. Run on emulator/device
```

## 📱 Screen Flow

```
Home → Grade Selection
    ↓
Avatar Selection
    ↓
Subject Selection (Math/Reading/Science)
    ↓
Quiz Mode (Answer Questions)
    ↓
Results/Completion
    ↓
Back to Home
```

## 🔧 Common Tasks

### Add a New Question
```kotlin
// In QuestionsRepository.kt, add to QUESTIONS list:
Question(
    id = "unique_id",
    subject = "math",
    grade = 0,
    topicId = "k_counting",
    question = "Your question here?",
    options = listOf("A", "B", "C", "D"),
    correct = 0,
    explanation = "Why this answer...",
    stars = 1
)
```

### Change App Colors
```kotlin
// In Theme.kt:
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF0061CA),        // Main color
    secondary = Color(0xFF0078D7),      // Secondary
    tertiary = Color(0xFF2E7D32),       // Accent
)
```

### Add a New Screen
```kotlin
// 1. Create: screens/NewScreen.kt
@Composable
fun NewScreen() { ... }

// 2. Add to MainActivity.kt NavHost:
composable("new_screen") {
    NewScreen()
}

// 3. Navigate from existing screen:
navController.navigate("new_screen")
```

### Get Questions for a Topic
```kotlin
// In ViewModel or any class:
val topicQuestions = QuestionsRepository.getQuestionsForTopic("topic_id")
val gradeQuestions = QuestionsRepository.getQuestionsForGrade(0)
```

## 🎨 UI Components

### Button
```kotlin
Button(
    onClick = { /* action */ },
    modifier = Modifier.fillMaxWidth()
) {
    Text("Click Me")
}
```

### Card
```kotlin
Card(
    modifier = Modifier.fillMaxWidth(),
    shape = RoundedCornerShape(12.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
) {
    Text("Content")
}
```

### LazyGrid (for avatars)
```kotlin
LazyVerticalGrid(
    columns = GridCells.Fixed(4),
    horizontalArrangement = Arrangement.spacedBy(12.dp)
) {
    items(itemsList) { item ->
        ItemCard(item)
    }
}
```

### Column (vertical layout)
```kotlin
Column(
    modifier = Modifier.fillMaxSize().padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text("Item 1")
    Text("Item 2")
}
```

## 📊 State Management Pattern

```kotlin
// 1. Define state data class
data class LearningUiState(
    val currentUser: UserProgress = UserProgress(),
    val currentQuestion: Question? = null
)

// 2. Create MutableStateFlow in ViewModel
private val _uiState = MutableStateFlow(LearningUiState())
val uiState: StateFlow<LearningUiState> = _uiState

// 3. Update state in ViewModel functions
fun selectAnswer(index: Int) {
    _uiState.value = _uiState.value.copy(userAnswer = index)
}

// 4. Observe in Compose
@Composable
fun MyScreen(viewModel: LearningViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    // Use uiState to render UI
}
```

## 🧠 Key Concepts

### StateFlow
- Reactive state holder
- Composables automatically recompose when state changes
- Thread-safe

### ViewModel
- Survives configuration changes (rotation, etc.)
- Lives as long as Activity/Fragment
- Best place for business logic

### Compose
- Declarative UI
- Recomposes only when state changes
- Efficient rendering

### Material Design 3
- Modern Android design system
- Built-in themes
- Color scheme management

## 🐛 Debugging Tips

### Check Logs
```
Android Studio → Logcat
Filter: com.floridalearning.stars
```

### Add Debug Logs
```kotlin
Log.d("DEBUG", "Grade: ${grade}, Avatar: ${avatar}")
```

### Debug Mode
```
Run → Debug 'app'
Set breakpoints and step through code
```

### Preview in Android Studio
```kotlin
@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(viewModel = LearningViewModel()) { }
}
```

## 📦 Gradle Commands

```bash
# Clean and build
./gradlew clean build

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Install on device
./gradlew installDebug

# Run emulator tests
./gradlew connectedAndroidTest
```

## 🎓 Learning Resources

### Official Documentation
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [Navigation Compose](https://developer.android.com/jetpack/compose/navigation)

### Topics to Learn
- Kotlin basics
- Coroutines (async programming)
- StateFlow & Flow
- Compose layout system

## 📋 Checklist for Customization

- [ ] Update app colors in Theme.kt
- [ ] Add new questions to QuestionsRepository.kt
- [ ] Create new screens in ui/screens/
- [ ] Add navigation routes to MainActivity.kt
- [ ] Test on emulator
- [ ] Test on physical device
- [ ] Build release APK
- [ ] Test release version

## 🚀 Deployment

### For Google Play Store
1. Generate signed APK in Android Studio
2. Create Google Play Developer account
3. Upload APK to Play Console
4. Fill in app details and screenshots
5. Submit for review

### For Direct Distribution
1. Build signed APK: `./gradlew assembleRelease`
2. Sign APK: Use Android Studio or jarsigner
3. Share APK file directly
4. Users can install via: adb install app.apk

## ⚡ Performance Tips

1. Use `LazyColumn`/`LazyVerticalGrid` for lists
2. Avoid unnecessary state hoisting
3. Use `.copy()` for state updates (immutable)
4. Minimize recompositions with keys
5. Use `remember` for expensive operations

## 🔐 Security Notes

- Don't hardcode API keys
- Use HTTPS for network requests
- Validate user input
- Sanitize displayed data
- Use proper permissions in manifest

## 💾 Data Storage

### Current (In-memory)
- Questions stored in QuestionsRepository
- User progress in ViewModel state

### Recommended (Future)
- Local SQLite database
- DataStore for preferences
- Firebase for cloud sync

## 📝 Code Style

```kotlin
// Use descriptive names
val gradeLevel = 0          // Good
val g = 0                   // Bad

// Use Kotlin conventions
data class Question(...)    // Use data classes
fun loadQuestion()          // Lowercase function names
val isCorrect = true        // Boolean prefixes (is, has)

// Use extension functions
fun List<Question>.filterByGrade(grade: Int) = 
    filter { it.grade == grade }

// Null safety
val name: String? = null
val length = name?.length ?: 0
```

## 🎯 Next Steps After Setup

1. ✅ Get project running
2. Test on multiple devices
3. Add more questions
4. Customize branding (colors, fonts)
5. Implement data persistence
6. Add analytics
7. User testing
8. Deploy to Play Store

---

**Quick Ref Version**: 1.0  
**Last Updated**: January 2026  
**For Help**: See ANDROID_PROJECT_README.md
