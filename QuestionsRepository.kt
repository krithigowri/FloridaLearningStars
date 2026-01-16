package com.floridalearning.stars.data

object QuestionsRepository {

    val TOPICS = mapOf(
        0 to mapOf(
            "math" to listOf(
                Topic("k_counting", "Counting to 20", "MAFS.K.CC.1.1"),
                Topic("k_addition", "Addition within 10", "MAFS.K.OA.1.1"),
                Topic("k_shapes", "Shapes", "MAFS.K.G.1.2")
            ),
            "reading" to listOf(
                Topic("k_letters", "Letter Recognition", "LAFS.K.RF.1.1"),
                Topic("k_rhyming", "Rhyming Words", "LAFS.K.RF.2.2a")
            ),
            "science" to listOf(
                Topic("k_living", "Living Things", "SC.K.L.14.1"),
                Topic("k_senses", "Five Senses", "SC.K.N.1.1")
            )
        ),
        1 to mapOf(
            "math" to listOf(
                Topic("1_addition", "Addition within 20", "MAFS.1.OA.1.1"),
                Topic("1_subtraction", "Subtraction within 20", "MAFS.1.OA.1.1"),
                Topic("1_place_value", "Place Value", "MAFS.1.NBT.2.2")
            ),
            "reading" to listOf(
                Topic("1_phonics", "Phonics Patterns", "LAFS.1.RF.3.3"),
                Topic("1_story", "Story Elements", "LAFS.1.RL.1.3")
            ),
            "science" to listOf(
                Topic("1_matter", "Properties of Matter", "SC.1.P.8.1"),
                Topic("1_motion", "Forces and Motion", "SC.1.P.12.1")
            )
        ),
        2 to mapOf(
            "math" to listOf(
                Topic("2_addition", "Addition within 100", "MAFS.2.OA.2.2"),
                Topic("2_subtraction", "Subtraction within 100", "MAFS.2.OA.2.2"),
                Topic("2_money", "Money and Coins", "MAFS.2.MD.3.8")
            ),
            "reading" to listOf(
                Topic("2_character", "Character Analysis", "LAFS.2.RL.1.3"),
                Topic("2_cause_effect", "Cause and Effect", "LAFS.2.RI.1.3")
            ),
            "science" to listOf(
                Topic("2_energy", "Forms of Energy", "SC.2.P.10.1"),
                Topic("2_life_cycles", "Life Cycles", "SC.2.L.16.1")
            )
        ),
        3 to mapOf(
            "math" to listOf(
                Topic("3_multiplication", "Multiplication", "MAFS.3.OA.3.7"),
                Topic("3_division", "Division", "MAFS.3.OA.3.7"),
                Topic("3_fractions", "Fractions", "MAFS.3.NF.1.1")
            ),
            "reading" to listOf(
                Topic("3_theme", "Theme", "LAFS.3.RL.1.2"),
                Topic("3_main_idea", "Main Idea", "LAFS.3.RI.1.2")
            ),
            "science" to listOf(
                Topic("3_forces", "Forces and Motion", "SC.3.P.10.1"),
                Topic("3_magnets", "Magnets", "SC.3.P.10.3")
            )
        ),
        4 to mapOf(
            "math" to listOf(
                Topic("4_multi_digit", "Multi-Digit Operations", "MAFS.4.NBT.2.5"),
                Topic("4_fractions", "Fraction Operations", "MAFS.4.NF.2.3")
            ),
            "reading" to listOf(
                Topic("4_theme", "Theme Analysis", "LAFS.4.RL.1.2"),
                Topic("4_inferences", "Making Inferences", "LAFS.4.RL.1.1")
            ),
            "science" to listOf(
                Topic("4_energy", "Energy Transfer", "SC.4.P.10.1")
            )
        ),
        5 to mapOf(
            "math" to listOf(
                Topic("5_decimals", "Decimal Operations", "MAFS.5.NBT.2.7"),
                Topic("5_fractions", "Fraction Operations", "MAFS.5.NF.1.1")
            ),
            "reading" to listOf(
                Topic("5_theme", "Theme Comparison", "LAFS.5.RL.1.2"),
                Topic("5_point_of_view", "Point of View", "LAFS.5.RL.2.6")
            ),
            "science" to listOf(
                Topic("5_matter", "Properties of Matter", "SC.5.P.8.1")
            )
        )
    )

    val QUESTIONS = listOf(
        // Kindergarten - Counting (20)
        Question("k_c1", "math", 0, "k_counting", "How many stars? ⭐⭐⭐", listOf("2", "3", "4", "5"), 1, "Count: 1, 2, 3. There are 3 stars!", 1),
        Question("k_c2", "math", 0, "k_counting", "Count: 🍎🍎🍎🍎🍎", listOf("4", "5", "6", "7"), 1, "There are 5 apples!", 1),
        Question("k_c3", "math", 0, "k_counting", "What comes after 7?", listOf("6", "7", "8", "9"), 2, "After 7 comes 8!", 2),
        Question("k_c4", "math", 0, "k_counting", "Count: 🐶🐶🐶🐶🐶🐶", listOf("5", "6", "7", "8"), 1, "There are 6 dogs!", 1),
        Question("k_c5", "math", 0, "k_counting", "Which is more: 3 or 5?", listOf("3", "5", "Same", "None"), 1, "5 is more than 3!", 2),
        Question("k_c6", "math", 0, "k_counting", "Count: 🌟🌟🌟🌟", listOf("3", "4", "5", "6"), 1, "There are 4 stars!", 1),
        Question("k_c7", "math", 0, "k_counting", "What comes before 10?", listOf("8", "9", "10", "11"), 1, "9 comes before 10!", 2),
        Question("k_c8", "math", 0, "k_counting", "Count: 🎈🎈🎈🎈🎈🎈🎈🎈", listOf("6", "7", "8", "9"), 2, "There are 8 balloons!", 2),
        Question("k_c9", "math", 0, "k_counting", "Which is less: 2 or 6?", listOf("2", "6", "Same", "None"), 0, "2 is less than 6!", 2),
        Question("k_c10", "math", 0, "k_counting", "How many? 🐱🐱", listOf("1", "2", "3", "4"), 1, "There are 2 cats!", 1),

        // Kindergarten - Addition (5)
        Question("k_a1", "math", 0, "k_addition", "1 + 1 = ?", listOf("1", "2", "3", "4"), 1, "1 plus 1 equals 2!", 1),
        Question("k_a2", "math", 0, "k_addition", "2 + 3 = ?", listOf("3", "4", "5", "6"), 2, "2 + 3 = 5!", 2),
        Question("k_a3", "math", 0, "k_addition", "2 + 2 = ?", listOf("2", "3", "4", "5"), 2, "2 plus 2 equals 4!", 1),
        Question("k_a4", "math", 0, "k_addition", "3 + 1 = ?", listOf("2", "3", "4", "5"), 2, "3 plus 1 equals 4!", 1),
        Question("k_a5", "math", 0, "k_addition", "4 + 2 = ?", listOf("4", "5", "6", "7"), 2, "4 + 2 = 6!", 2),

        // Kindergarten - Shapes (5)
        Question("k_s1", "math", 0, "k_shapes", "How many sides does a triangle have?", listOf("2", "3", "4", "5"), 1, "A triangle has 3 sides!", 1),
        Question("k_s2", "math", 0, "k_shapes", "Which shape is round?", listOf("Square", "Triangle", "Circle", "Rectangle"), 2, "A circle is round!", 1),
        Question("k_s3", "math", 0, "k_shapes", "How many sides does a square have?", listOf("2", "3", "4", "5"), 2, "A square has 4 sides!", 1),
        Question("k_s4", "math", 0, "k_shapes", "Which shape has no corners?", listOf("Square", "Triangle", "Circle", "Rectangle"), 2, "A circle has no corners!", 2),
        Question("k_s5", "math", 0, "k_shapes", "How many corners does a square have?", listOf("2", "3", "4", "5"), 2, "A square has 4 corners!", 2),

        // Kindergarten - Letters (5)
        Question("k_l1", "reading", 0, "k_letters", "Which letter is this? A", listOf("A", "B", "C", "D"), 0, "This is letter A!", 1),
        Question("k_l2", "reading", 0, "k_letters", "Which letter is this? B", listOf("A", "B", "C", "D"), 1, "This is letter B!", 1),
        Question("k_l3", "reading", 0, "k_letters", "Which letter is this? M", listOf("N", "M", "W", "V"), 1, "This is letter M!", 1),
        Question("k_l4", "reading", 0, "k_letters", "Which is lowercase?", listOf("a", "A", "Both", "None"), 0, "Lowercase a is smaller!", 2),
        Question("k_l5", "reading", 0, "k_letters", "First letter of alphabet?", listOf("A", "B", "C", "Z"), 0, "A is the first letter!", 2)
    )

    val BADGES = listOf(
        Badge("first_star", "First Star", "Earn your first star!", "⭐", "stars", 1),
        Badge("star_collector", "Star Collector", "Collect 100 stars", "✨", "stars", 100),
        Badge("quiz_master", "Quiz Master", "Answer 50 questions", "🎯", "questions", 50),
        Badge("mathematician", "Mathematician", "Complete all math topics", "🔢", "streak", 5),
        Badge("reader", "Reader", "Complete all reading topics", "📚", "streak", 5),
        Badge("scientist", "Scientist", "Complete all science topics", "🔬", "streak", 5)
    )

    val AVATARS = listOf("🐶", "🐱", "🦊", "🐻", "🐼", "🦁", "🐯", "🐸", "🦄", "🐉", "🦖", "🦕", "🚀", "⭐", "🌈", "🎨")

    fun getTopicsForGrade(grade: Int): Map<String, List<Topic>> {
        return TOPICS[grade] ?: emptyMap()
    }

    fun getQuestionsForTopic(topicId: String): List<Question> {
        return QUESTIONS.filter { it.topicId == topicId }
    }

    fun getQuestionsForGrade(grade: Int): List<Question> {
        return QUESTIONS.filter { it.grade == grade }
    }

    fun getRandomQuestion(grade: Int, subject: String? = null): Question? {
        val filtered = if (subject != null) {
            getQuestionsForGrade(grade).filter { it.subject == subject }
        } else {
            getQuestionsForGrade(grade)
        }
        return filtered.randomOrNull()
    }
}
