package com.floridalearning.stars.data

import kotlinx.serialization.Serializable

@Serializable
data class Topic(
    val id: String,
    val name: String,
    val standard: String
)

@Serializable
data class Subject(
    val math: List<Topic> = emptyList(),
    val reading: List<Topic> = emptyList(),
    val science: List<Topic> = emptyList()
)

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

@Serializable
data class UserProgress(
    val gradeLevel: Int = 0,
    val selectedAvatar: String = "🐶",
    val totalStars: Int = 0,
    val completedTopics: Set<String> = emptySet(),
    val answeredQuestions: Map<String, Boolean> = emptyMap()
)

@Serializable
data class Badge(
    val id: String,
    val name: String,
    val description: String,
    val emoji: String,
    val requirementType: String, // "stars", "questions", "streak"
    val requirementValue: Int
)
