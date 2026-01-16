package com.floridalearning.stars.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.floridalearning.stars.data.Question
import com.floridalearning.stars.ui.LearningViewModel

@Composable
fun QuizScreen(
    viewModel: LearningViewModel,
    question: Question,
    onSubmitAnswer: () -> Unit,
    onNextQuestion: () -> Unit,
    onBackPressed: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = androidx.compose.material3.MaterialTheme.colorScheme.background
            )
    ) {
        // Header
        QuizHeader(
            currentQuestion = uiState.questionIndex,
            totalQuestions = uiState.totalQuestions,
            currentStars = uiState.sessionStars,
            onBack = onBackPressed
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                // Question Text
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = androidx.compose.material3.MaterialTheme.colorScheme.primaryContainer
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Text(
                        text = question.question,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    )
                }
            }

            item {
                // Answer Options
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    question.options.forEachIndexed { index, option ->
                        AnswerOptionButton(
                            option = option,
                            index = index,
                            isSelected = uiState.userAnswer == index,
                            isSubmitted = uiState.isAnswerSubmitted,
                            isCorrect = index == question.correct,
                            onSelect = { viewModel.selectAnswer(index) }
                        )
                    }
                }
            }

            item {
                if (!uiState.isAnswerSubmitted) {
                    Button(
                        onClick = {
                            if (uiState.userAnswer != null) {
                                onSubmitAnswer()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        enabled = uiState.userAnswer != null,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Submit Answer", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    }
                } else {
                    // Show explanation and next button
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (uiState.userAnswer == question.correct) {
                                    Color(0xFFE8F5E9)
                                } else {
                                    Color(0xFFFFEBEE)
                                }
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = if (uiState.userAnswer == question.correct) "Correct! 🎉" else "Try Again!",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (uiState.userAnswer == question.correct) {
                                        Color(0xFF2E7D32)
                                    } else {
                                        Color(0xFFC62828)
                                    }
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = question.explanation,
                                    fontSize = 14.sp,
                                    color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground
                                )

                                if (uiState.userAnswer == question.correct) {
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "You earned ${question.stars} ⭐",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color(0xFF2E7D32)
                                    )
                                }
                            }
                        }

                        Button(
                            onClick = onNextQuestion,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                "Next Question",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuizHeader(
    currentQuestion: Int,
    totalQuestions: Int,
    currentStars: Int,
    onBack: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = androidx.compose.material3.MaterialTheme.colorScheme.primary
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = androidx.compose.material3.MaterialTheme.colorScheme.onPrimary
            )
        }

        Text(
            text = "Question $currentQuestion/$totalQuestions",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onPrimary
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Text(
                text = "⭐ $currentStars",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Yellow
            )
        }
    }
}

@Composable
fun AnswerOptionButton(
    option: String,
    index: Int,
    isSelected: Boolean,
    isSubmitted: Boolean,
    isCorrect: Boolean,
    onSelect: () -> Unit
) {
    val backgroundColor = when {
        isSubmitted && isCorrect -> Color(0xFF4CAF50)
        isSubmitted && isSelected && !isCorrect -> Color(0xFFF44336)
        isSelected -> androidx.compose.material3.MaterialTheme.colorScheme.primary
        else -> androidx.compose.material3.MaterialTheme.colorScheme.surface
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = !isSubmitted) { onSelect() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 8.dp else 2.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "${(index + 1)}. $option",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (isSelected || (isSubmitted && isCorrect)) {
                        Color.White
                    } else {
                        androidx.compose.material3.MaterialTheme.colorScheme.onSurface
                    }
                )
            }
        }
    }
}
