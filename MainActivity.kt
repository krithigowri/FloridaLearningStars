package com.floridalearning.stars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.floridalearning.stars.data.QuestionsRepository
import com.floridalearning.stars.ui.LearningViewModel
import com.floridalearning.stars.ui.screens.HomeScreen
import com.floridalearning.stars.ui.screens.AvatarSelectionScreen
import com.floridalearning.stars.ui.screens.QuizScreen
import com.floridalearning.stars.ui.theme.FloridaLearningStarsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FloridaLearningStarsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FloridaLearningApp()
                }
            }
        }
    }
}

@Composable
fun FloridaLearningApp() {
    val navController = rememberNavController()
    val viewModel: LearningViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                onGradeSelected = { grade ->
                    viewModel.initializeGrade(grade)
                    navController.navigate("avatar_selection")
                }
            )
        }

        composable("avatar_selection") {
            AvatarSelectionScreen(
                avatars = QuestionsRepository.AVATARS,
                onAvatarSelected = { avatar ->
                    viewModel.selectAvatar(avatar)
                    navController.navigate("subject_selection")
                }
            )
        }

        composable("subject_selection") {
            SubjectSelectionScreen(
                onSubjectSelected = { subject ->
                    viewModel.selectSubject(subject)
                    navController.navigate("quiz")
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable("quiz") {
            val uiState = viewModel.uiState.value
            if (uiState.currentQuestion != null) {
                QuizScreen(
                    viewModel = viewModel,
                    question = uiState.currentQuestion,
                    onSubmitAnswer = { viewModel.submitAnswer() },
                    onNextQuestion = {
                        if (uiState.questionIndex < uiState.totalQuestions) {
                            viewModel.loadNextQuestion()
                        } else {
                            navController.navigate("completion") {
                                popUpTo("quiz") { inclusive = true }
                            }
                        }
                    },
                    onBackPressed = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable("completion") {
            CompletionScreen(
                correctAnswers = viewModel.uiState.value.correctAnswersCount,
                totalQuestions = viewModel.uiState.value.totalQuestions,
                starsEarned = viewModel.uiState.value.sessionStars,
                totalStars = viewModel.uiState.value.currentUser.totalStars,
                onBackHome = {
                    viewModel.resetSession()
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
    }
}

@Composable
fun SubjectSelectionScreen(
    onSubjectSelected: (String) -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = onBack) {
            Text("Back")
        }

        Text("Select a Subject", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        listOf("Math", "Reading", "Science").forEach { subject ->
            Button(
                onClick = { onSubjectSelected(subject.lowercase()) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(subject)
            }
        }
    }
}

@Composable
fun CompletionScreen(
    correctAnswers: Int,
    totalQuestions: Int,
    starsEarned: Int,
    totalStars: Int,
    onBackHome: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Quiz Complete!", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(24.dp))

        Text("$correctAnswers / $totalQuestions Correct", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Text("⭐ Stars Earned: $starsEarned", style = MaterialTheme.typography.titleLarge)
        Text("Total Stars: $totalStars", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onBackHome,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Back to Home")
        }
    }
}

private val Modifier.height: Unit
    get() = Unit

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
