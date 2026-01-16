# React to Android Migration Guide

## Overview
This document outlines how the React/TypeScript Florida Learning Stars app has been converted to a native Android application using Kotlin and Jetpack Compose.

## Comparison Table

| Aspect | React (Original) | Android (New) |
|--------|------------------|---------------|
| **Language** | TypeScript | Kotlin |
| **UI Framework** | React with Tailwind CSS | Jetpack Compose |
| **State Management** | useState hooks | ViewModel + StateFlow |
| **Styling** | CSS classes | Material Design 3 |
| **Navigation** | React Router | Navigation Compose |
| **Platform** | Web browser | Mobile (Android) |
| **Deployment** | Web hosting | Google Play Store |

## Architecture Mapping

### React Component → Android Compose

```
React Component Structure:
├── Home Component
├── AvatarSelection Component
├── SubjectSelection Component
├── Quiz Component
└── Completion Component

↓

Android Compose Structure:
├── HomeScreen (home route)
├── AvatarSelectionScreen (avatar_selection route)
├── SubjectSelectionScreen (subject_selection route)
├── QuizScreen (quiz route)
└── CompletionScreen (completion route)
```

### State Management

**React (Hooks)**
```typescript
const [gradeLevel, setGradeLevel] = useState(0);
const [selectedAvatar, setSelectedAvatar] = useState('🐶');
const [currentQuestion, setCurrentQuestion] = useState(null);
const [userAnswer, setUserAnswer] = useState(null);
```

**Android (ViewModel)**
```kotlin
private val _uiState = MutableStateFlow(LearningUiState())
val uiState: StateFlow<LearningUiState> = _uiState

// Update state
_uiState.value = _uiState.value.copy(
    gradeLevel = 0,
    selectedAvatar = "🐶"
)
```

### Data Models

**React (TypeScript Interface)**
```typescript
interface Question {
  id: string;
  subject: string;
  grade: number;
  topicId: string;
  question: string;
  options: string[];
  correct: number;
  explanation: string;
  stars: number;
}
```

**Android (Kotlin Data Class)**
```kotlin
@Serializable
data class Question(
    val id: String,
    val subject: String,
    val grade: Int,
    val topicId: String,
    val question: String,
    val options: List<String>,
    val correct: Int,
    val explanation: String,
    val stars: Int
)
```

## Detailed Conversions

### 1. Component Lifecycle

**React**
```typescript
useEffect(() => {
  loadQuestionsForTopic(topicId);
}, [topicId]);
```

**Android**
```kotlin
fun selectSubject(subject: String) {
    _uiState.value = _uiState.value.copy(selectedSubject = subject)
    loadQuestionsForSubject(subject)
}
```

### 2. Event Handling

**React**
```typescript
const handleAnswerSelect = (index) => {
  setUserAnswer(index);
};

const handleSubmit = () => {
  const isCorrect = userAnswer === currentQuestion.correct;
  // ... validation logic
};
```

**Android**
```kotlin
fun selectAnswer(answerIndex: Int) {
    _uiState.value = _uiState.value.copy(userAnswer = answerIndex)
}

fun submitAnswer() {
    val currentQuestion = _uiState.value.currentQuestion ?: return
    val userAnswer = _uiState.value.userAnswer ?: return
    // ... validation logic
}
```

### 3. Conditional Rendering

**React**
```typescript
{isAnswerSubmitted ? (
  <ExplanationCard explanation={explanation} />
) : (
  <AnswerOptions options={options} />
)}
```

**Android (Compose)**
```kotlin
if (!uiState.isAnswerSubmitted) {
    AnswerOptionButton(...)
} else {
    ExplanationCard(explanation = question.explanation)
}
```

### 4. Lists/Loops

**React**
```typescript
{topics.map((topic) => (
  <TopicCard key={topic.id} topic={topic} />
))}
```

**Android (Compose)**
```kotlin
items(topics) { topic ->
    TopicCard(topic = topic)
}
```

### 5. Styling

**React (Tailwind)**
```typescript
<div className="bg-blue-500 text-white p-4 rounded-lg">
  Question
</div>
```

**Android (Compose)**
```kotlin
Card(
    modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primary
    )
) {
    Text("Question")
}
```

## Navigation

### React Router
```typescript
<Routes>
  <Route path="/" element={<Home />} />
  <Route path="/quiz/:grade" element={<Quiz />} />
  <Route path="/completion" element={<Completion />} />
</Routes>
```

### Android Navigation Compose
```kotlin
NavHost(navController = navController, startDestination = "home") {
    composable("home") { HomeScreen(...) }
    composable("quiz") { QuizScreen(...) }
    composable("completion") { CompletionScreen(...) }
}
```

## Data Persistence

### React (Local Storage)
```typescript
localStorage.setItem('userProgress', JSON.stringify(progress));
const progress = JSON.parse(localStorage.getItem('userProgress'));
```

### Android (DataStore - Future Enhancement)
```kotlin
// Write
dataStore.edit { preferences ->
    preferences[GRADE_KEY] = gradeLevel
}

// Read
dataStore.data.map { preferences ->
    preferences[GRADE_KEY] ?: 0
}.collect { grade -> ... }
```

## Common Conversions

### Loading States

**React**
```typescript
const [isLoading, setIsLoading] = useState(false);

return isLoading ? <LoadingSpinner /> : <Content />;
```

**Android**
```kotlin
if (uiState.isLoading) {
    CircularProgressIndicator()
} else {
    Content()
}
```

### Error Handling

**React**
```typescript
try {
  const data = await fetchQuestions();
} catch (error) {
  setError(error.message);
}
```

**Android (In ViewModel)**
```kotlin
try {
    val questions = repository.getQuestions()
    _uiState.value = _uiState.value.copy(questions = questions)
} catch (e: Exception) {
    _uiState.value = _uiState.value.copy(error = e.message)
}
```

### Async Operations

**React**
```typescript
const [data, setData] = useState(null);

useEffect(() => {
  const loadData = async () => {
    const result = await fetchData();
    setData(result);
  };
  loadData();
}, []);
```

**Android (Coroutines)**
```kotlin
private var currentQuestionList: List<Question> = emptyList()

fun loadQuestionsForSubject(subject: String) {
    currentQuestionList = QuestionsRepository.getQuestionsForGrade(grade)
        .filter { it.subject == subject }
    _uiState.value = _uiState.value.copy(questions = currentQuestionList)
}
```

## File Organization

### React
```
src/
├── components/
│   ├── Home.tsx
│   ├── Quiz.tsx
│   └── ...
├── types/
│   └── index.ts
├── utils/
│   └── constants.ts
└── App.tsx
```

### Android
```
app/src/main/java/com/floridalearning/stars/
├── MainActivity.kt          # Entry point
├── data/
│   ├── Models.kt
│   └── QuestionsRepository.kt
└── ui/
    ├── LearningViewModel.kt
    ├── theme/
    │   └── Theme.kt
    └── screens/
        ├── HomeScreen.kt
        └── QuizScreen.kt
```

## Resources and Constants

### React
```typescript
const AVATARS = ['🐶', '🐱', ...];
const TOPICS = { 0: { math: [...], reading: [...] }, ... };
const QUESTIONS = [
  { id: 'k_c1', subject: 'math', ... },
  ...
];
```

### Android
```kotlin
object QuestionsRepository {
    val AVATARS = listOf("🐶", "🐱", ...)
    val TOPICS = mapOf(0 to mapOf("math" to listOf(...)), ...)
    val QUESTIONS = listOf(
        Question(id = "k_c1", subject = "math", ...),
        ...
    )
}
```

## UI/UX Changes

### Responsive Design
- **React**: Flexbox with CSS media queries
- **Android**: Native responsive layouts with Compose modifiers

### Theme System
- **React**: CSS variables or Tailwind config
- **Android**: Material Design 3 ColorScheme and Typography

### Animations
- **React**: CSS transitions, React Spring
- **Android**: Compose animations, transitions

## Performance Considerations

### React
- Virtual DOM reconciliation
- Re-render optimization with useMemo/useCallback
- Code splitting for faster load

### Android
- Recomposition skipping with @Composable keys
- Efficient state updates with StateFlow
- Memory management with ViewModel lifecycle

## Testing

### React
```typescript
test('HomeScreen renders grade buttons', () => {
  render(<HomeScreen />);
  expect(screen.getByText('Grade 1')).toBeInTheDocument();
});
```

### Android
```kotlin
@Test
fun homeScreenRendersGradeButtons() {
    composeRule.setContent {
        HomeScreen(viewModel = LearningViewModel()) { }
    }
    composeRule.onNodeWithText("Grade 1").assertExists()
}
```

## Build & Deployment

### React
- Build: `npm run build`
- Deploy: Firebase Hosting, Vercel, Netlify
- Distribution: Web URL

### Android
- Build: `./gradlew assembleRelease`
- Deploy: Google Play Store or APK distribution
- Distribution: App stores or direct download

## Feature Parity Checklist

- ✅ Grade selection (K-5)
- ✅ Avatar customization
- ✅ Subject selection (Math, Reading, Science)
- ✅ Quiz with multiple-choice questions
- ✅ Star-based reward system
- ✅ Progress tracking
- ✅ Explanation feedback
- ✅ Session scoring
- ⬜ Backend API integration
- ⬜ Cloud save/sync
- ⬜ Leaderboards
- ⬜ Badges system

## Migration Checklist

- [x] Convert TypeScript types to Kotlin data classes
- [x] Migrate React components to Compose screens
- [x] Implement ViewModel for state management
- [x] Set up Navigation Compose
- [x] Convert styling to Material Design 3
- [x] Adapt question repository
- [x] Create project structure
- [x] Add build configuration
- [ ] Add unit tests
- [ ] Add UI tests
- [ ] Performance optimization
- [ ] Deploy to Play Store

## Differences in Behavior

| Feature | React | Android | Notes |
|---------|-------|---------|-------|
| State Persistence | Manual (localStorage) | ViewModel (survives config changes) | Android handles orientation changes |
| Navigation | SPA routing | Activity/Fragment-based | Android native pattern |
| Styling | CSS cascade | Compose declarative | Type-safe styling |
| Async ops | Promises/async-await | Coroutines | More efficient |

## Troubleshooting Migration

### Issue: State not updating UI
**Solution**: Ensure using `MutableStateFlow` with `.value = newState`

### Issue: Navigation not working
**Solution**: Check NavHost setup and route names in composable()

### Issue: Styling doesn't match React version
**Solution**: Verify Material Design 3 color scheme matches original

## Future Enhancements

1. **Firebase Backend**
   - Replace local data with cloud database
   - User authentication
   - Cross-device sync

2. **Advanced Features**
   - Offline mode with local database
   - Push notifications
   - Gamification (badges, achievements)

3. **Content Management**
   - Dynamic question loading
   - Teacher dashboard
   - Custom content creation

---

**Conversion Date**: January 2026  
**Original Framework**: React + TypeScript  
**New Framework**: Android (Kotlin + Jetpack Compose)
