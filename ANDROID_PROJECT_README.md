# Florida Learning Stars - Android App

## Overview
This is a native Android version of the Florida Learning Stars educational app, built with **Kotlin** and **Jetpack Compose**. The app provides interactive learning experiences for students in grades K-5, covering Math, Reading, and Science topics aligned with Florida standards.

## Project Structure

```
FloridaLearningApp/
├── app/
│   ├── build.gradle.kts
│   ├── src/
│   │   ├── main/
│   │   │   ├── AndroidManifest.xml
│   │   │   ├── java/com/floridalearning/stars/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── data/
│   │   │   │   │   ├── Models.kt
│   │   │   │   │   └── QuestionsRepository.kt
│   │   │   │   ├── ui/
│   │   │   │   │   ├── LearningViewModel.kt
│   │   │   │   │   ├── theme/
│   │   │   │   │   │   └── Theme.kt
│   │   │   │   │   └── screens/
│   │   │   │   │       ├── HomeScreen.kt
│   │   │   │   │       └── QuizScreen.kt
│   │   │   │   └── res/
│   │   │   │       ├── values/
│   │   │   │       │   └── strings.xml
│   │   │   │       └── mipmap/
│   │   │   │           ├── ic_launcher.xml
│   │   │   │           └── ic_launcher_round.xml
├── build.gradle.kts
└── settings.gradle.kts
```

## Key Features

### 1. **Grade Selection** (K-5)
- Choose appropriate grade level
- Tailored content based on Florida standards

### 2. **Avatar Selection**
- 16 unique avatar options
- Personalized learning experience
- Tracks user progress

### 3. **Subject-Based Learning**
- **Math**: Counting, Addition, Shapes, Multiplication, Fractions
- **Reading**: Letter Recognition, Phonics, Story Elements, Theme
- **Science**: Living Things, Properties of Matter, Energy

### 4. **Interactive Quiz System**
- Multiple-choice questions
- Real-time feedback with explanations
- Star-based reward system
- Performance tracking

### 5. **Progress Tracking**
- Accumulate stars for correct answers
- Track completed topics
- Session-based scoring
- Total stars display

## Tech Stack

### Core Technologies
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Navigation**: Navigation Compose
- **Architecture**: MVVM Pattern with ViewModel
- **Data Persistence**: DataStore Preferences (optional expansion)
- **Serialization**: kotlinx-serialization

### Dependencies
```gradle
androidx.core:core-ktx:1.12.0
androidx.lifecycle:lifecycle-runtime-ktx:2.6.2
androidx.activity:activity-compose:1.8.0
androidx.compose.ui:ui:2023.10.00
androidx.compose.material3:material3
androidx.navigation:navigation-compose:2.7.5
kotlinx-serialization-json:1.6.0
androidx.datastore:datastore-preferences:1.0.0
```

## Data Models

### Question
- `id`: Unique identifier
- `subject`: Math, Reading, or Science
- `grade`: Grade level (0-5)
- `topicId`: Topic identifier
- `question`: Question text
- `options`: List of answer choices
- `correct`: Index of correct answer
- `explanation`: Explanation of correct answer
- `stars`: Points awarded for correct answer

### UserProgress
- `gradeLevel`: Selected grade
- `selectedAvatar`: Chosen avatar
- `totalStars`: Accumulated stars
- `completedTopics`: Set of completed topics
- `answeredQuestions`: Map of question responses

### Topic
- `id`: Unique identifier
- `name`: Topic name
- `standard`: Florida standard reference

## Installation & Setup

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK 34+
- JDK 11+

### Steps
1. Clone the repository
2. Open project in Android Studio
3. Sync Gradle files
4. Build the project
5. Run on an emulator or device (API 24+)

## File Guide

### Models.kt
Contains all data classes:
- `Question`: Quiz question structure
- `Topic`: Learning topic structure
- `Subject`: Collection of topics
- `UserProgress`: Student progress data
- `Badge`: Achievement badges

### QuestionsRepository.kt
Data repository containing:
- `TOPICS`: Grade-organized topic structure
- `QUESTIONS`: Question database (~50+ sample questions)
- `BADGES`: Achievement system
- `AVATARS`: Avatar emoji collection
- Helper functions for data retrieval

### LearningViewModel.kt
Business logic and state management:
- Grade initialization
- Avatar selection
- Subject selection
- Question loading
- Answer submission
- Progress tracking
- Session management

### Screens

#### HomeScreen.kt
- Grade selection interface
- Avatar picker component
- Grade cards with visual layout

#### QuizScreen.kt
- Question display
- Answer options
- Submission handling
- Feedback display
- Explanation presentation
- Star earning visualization
- Progress indicator

## How It Works

### User Flow
1. **Home Screen** → Select Grade (K-5)
2. **Avatar Selection** → Choose personal avatar
3. **Subject Selection** → Choose Math, Reading, or Science
4. **Quiz Mode** → Answer questions from selected subject
5. **Completion** → View results and stars earned
6. **Home** → Return to start or continue learning

### Quiz Logic
1. Load questions for selected subject/grade
2. Display question with 4 multiple-choice options
3. Wait for user selection
4. Display explanation after submission
5. Award stars if correct
6. Move to next question
7. Show results summary

## Customization

### Adding New Questions
Edit `QuestionsRepository.kt` and add to `QUESTIONS` list:
```kotlin
Question(
    id = "unique_id",
    subject = "math",
    grade = 0,
    topicId = "k_counting",
    question = "How many? ⭐⭐",
    options = listOf("1", "2", "3", "4"),
    correct = 1,
    explanation = "Count: 1, 2. There are 2 stars!",
    stars = 1
)
```

### Styling
Modify theme in `Theme.kt`:
- Primary color: `Color(0xFF0061CA)` (Florida Blue)
- Secondary color: `Color(0xFF0078D7)`
- Tertiary color: `Color(0xFF2E7D32)` (Green)

### Adding Topics
Update `TOPICS` map in `QuestionsRepository.kt`:
```kotlin
0 to mapOf(
    "math" to listOf(
        Topic("new_topic", "New Topic Name", "MAFS.STANDARD")
    )
)
```

## Standards Alignment

Topics are mapped to Florida Standards:
- **MAFS**: Mathematics standards
- **LAFS**: Language Arts standards
- **SC**: Science standards

Example: `MAFS.K.CC.1.1` = Kindergarten Math - Counting standard

## Performance Optimization

- Lazy loading of questions
- Efficient state management with ViewModel
- Compose recomposition optimization
- DataStore for persistent storage (when implemented)

## Future Enhancements

1. **Firebase Integration**
   - Cloud sync for progress
   - Analytics tracking
   - Remote updates

2. **Advanced Features**
   - Leaderboards
   - Multiplayer mode
   - Customizable difficulty
   - Timed challenges
   - Achievement badges

3. **Content Expansion**
   - More questions per topic
   - Video tutorials
   - Interactive activities
   - Downloadable content

4. **Data Persistence**
   - Local SQLite database
   - Cloud backup
   - Offline mode

5. **Parental Controls**
   - Usage monitoring
   - Progress reports
   - Time limits
   - Content filtering

## Testing

### Unit Tests
Place in `app/src/test/`:
```kotlin
class QuestionRepositoryTest {
    @Test
    fun testGetTopicsForGrade() {
        // Test implementation
    }
}
```

### UI Tests
Place in `app/src/androidTest/`:
```kotlin
@RunWith(AndroidComposeTestRunner::class)
class HomeScreenTest {
    @get:Rule
    val composeRule = createComposeRule()
    
    @Test
    fun testGradeCardDisplays() {
        // Test implementation
    }
}
```

## Build Instructions

### Debug Build
```bash
./gradlew assembleDebug
```

### Release Build
```bash
./gradlew assembleRelease
```

### Install APK
```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

## Troubleshooting

### Gradle Sync Issues
- Update Android Studio to latest version
- Clear Gradle cache: `./gradlew clean`
- Delete `.gradle` folder and resync

### Compose Runtime Errors
- Ensure Compose version matches in `build.gradle.kts`
- Update Kotlin plugin version
- Clear build cache: `./gradlew clean`

### App Crashes
- Check Logcat for error messages
- Verify all dependencies are installed
- Run in debug mode for stack traces

## Resources

- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Android Architecture Components](https://developer.android.com/topic/architecture)
- [Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
- [Florida Standards](https://www.fldoe.org/)

## License

Proprietary - Florida Learning Stars Inc.

## Support

For issues or questions:
- Check the GitHub Issues section
- Review Android Studio Logcat for errors
- Consult Android documentation

---

**Version**: 1.0  
**Last Updated**: January 2026  
**Minimum API Level**: 24 (Android 7.0)  
**Target API Level**: 34 (Android 14)
