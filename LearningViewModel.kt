package com.floridalearning.stars.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.floridalearning.stars.data.Question
import com.floridalearning.stars.data.Topic
import com.floridalearning.stars.data.UserProgress
import com.floridalearning.stars.data.QuestionsRepository

data class LearningUiState(
    val currentUser: UserProgress = UserProgress(),
    val currentQuestion: Question? = null,
    val currentTopics: Map<String, List<Topic>> = emptyMap(),
    val selectedSubject: String = "math",
    val questionIndex: Int = 0,
    val totalQuestions: Int = 0,
    val userAnswer: Int? = null,
    val isAnswerSubmitted: Boolean = false,
    val correctAnswersCount: Int = 0,
    val sessionStars: Int = 0,
    val isLoading: Boolean = false
)

class LearningViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LearningUiState())
    val uiState: StateFlow<LearningUiState> = _uiState

    private var currentQuestionList: List<Question> = emptyList()

    fun initializeGrade(grade: Int) {
        val topics = QuestionsRepository.getTopicsForGrade(grade)
        _uiState.value = _uiState.value.copy(
            currentUser = _uiState.value.currentUser.copy(gradeLevel = grade),
            currentTopics = topics
        )
        loadNextQuestion()
    }

    fun selectAvatar(avatar: String) {
        _uiState.value = _uiState.value.copy(
            currentUser = _uiState.value.currentUser.copy(selectedAvatar = avatar)
        )
    }

    fun selectSubject(subject: String) {
        _uiState.value = _uiState.value.copy(selectedSubject = subject)
        loadQuestionsForSubject(subject)
    }

    private fun loadQuestionsForSubject(subject: String) {
        val grade = _uiState.value.currentUser.gradeLevel
        currentQuestionList = QuestionsRepository.getQuestionsForGrade(grade)
            .filter { it.subject == subject }
        _uiState.value = _uiState.value.copy(
            questionIndex = 0,
            totalQuestions = currentQuestionList.size,
            userAnswer = null,
            isAnswerSubmitted = false,
            correctAnswersCount = 0,
            sessionStars = 0
        )
        loadNextQuestion()
    }

    fun loadNextQuestion() {
        if (currentQuestionList.isEmpty()) {
            val grade = _uiState.value.currentUser.gradeLevel
            currentQuestionList = QuestionsRepository.getQuestionsForGrade(grade)
            if (currentQuestionList.isEmpty()) {
                _uiState.value = _uiState.value.copy(currentQuestion = null)
                return
            }
        }

        val index = _uiState.value.questionIndex
        if (index < currentQuestionList.size) {
            _uiState.value = _uiState.value.copy(
                currentQuestion = currentQuestionList[index],
                questionIndex = index + 1,
                totalQuestions = currentQuestionList.size,
                userAnswer = null,
                isAnswerSubmitted = false
            )
        }
    }

    fun selectAnswer(answerIndex: Int) {
        _uiState.value = _uiState.value.copy(userAnswer = answerIndex)
    }

    fun submitAnswer() {
        val currentQuestion = _uiState.value.currentQuestion ?: return
        val userAnswer = _uiState.value.userAnswer ?: return

        val isCorrect = userAnswer == currentQuestion.correct
        val starsEarned = if (isCorrect) currentQuestion.stars else 0

        var newCorrectAnswers = _uiState.value.correctAnswersCount
        var newSessionStars = _uiState.value.sessionStars

        if (isCorrect) {
            newCorrectAnswers++
            newSessionStars += starsEarned
        }

        _uiState.value = _uiState.value.copy(
            isAnswerSubmitted = true,
            correctAnswersCount = newCorrectAnswers,
            sessionStars = newSessionStars
        )
    }

    fun completeSession() {
        val currentUser = _uiState.value.currentUser
        val newTotalStars = currentUser.totalStars + _uiState.value.sessionStars
        val newCompletedTopics = currentUser.completedTopics.toMutableSet().apply {
            _uiState.value.currentTopics.values.flatMap { it.map { topic -> topic.id } }
                .forEach { add(it) }
        }

        _uiState.value = _uiState.value.copy(
            currentUser = currentUser.copy(
                totalStars = newTotalStars,
                completedTopics = newCompletedTopics
            )
        )
    }

    fun resetSession() {
        _uiState.value = LearningUiState()
    }
}
