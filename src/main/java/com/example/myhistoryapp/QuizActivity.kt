package com.example.myhistoryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private val questions = listOf(
        "Who was the first President of the United States?",
        "In which year did World War II end?",
        "Which ancient civilization built the pyramids?",
        "What wall fell in 1989?",
        "Who wrote the Declaration of Independence?"
    )

    private val options = listOf(
        listOf("George Washington", "Abraham Lincoln", "Thomas Jefferson", "John Adams"),
        listOf("1942", "1945", "1939", "1950"),
        listOf("Romans", "Greeks", "Egyptians", "Aztecs"),
        listOf("The Great Wall", "Berlin Wall", "Hadrian's Wall", "Western Wall"),
        listOf("George Washington", "James Madison", "Benjamin Franklin", "Thomas Jefferson")
    )

    private val answers = listOf(
        "George Washington", "1945", "Egyptians", "Berlin Wall", "Thomas Jefferson"
    )

    private var index = 0
    private var score = 0

    private lateinit var questionText: TextView
    private lateinit var optionButtons: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionText = findViewById(R.id.questionText)
        optionButtons = listOf(
            findViewById(R.id.option1),
            findViewById(R.id.option2),
            findViewById(R.id.option3),
            findViewById(R.id.option4)
        )

        loadQuestion()

        optionButtons.forEachIndexed { i, button ->
            button.setOnClickListener {
                checkAnswer(button.text.toString())
            }
        }
    }

    private fun loadQuestion() {
        if (index < questions.size) {
            questionText.text = questions[index]
            optionButtons.forEachIndexed { i, button ->
                button.text = options[index][i]
            }
        } else {
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("score", score)
            intent.putExtra("total", questions.size)
            startActivity(intent)
            finish()
        }
    }

    private fun checkAnswer(selected: String) {
        if (selected == answers[index]) {
            score++
        }
        index++
        loadQuestion()
    }
}
