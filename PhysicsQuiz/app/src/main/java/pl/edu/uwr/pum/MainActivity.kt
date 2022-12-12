package pl.edu.uwr.pum

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.os.Parcelable
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.parcelize.Parcelize
import java.text.FieldPosition

@Parcelize
class CheatQuestion(val question: String, val anwser: Boolean, val position: Int) : Parcelable

@Parcelize
class StatOverview(var points: Int, var correctAnswers: Int, var cheatAmount: Int) : Parcelable

class MainActivity : AppCompatActivity() {

    class QuizStats(
        var points: Int,
        var correctAnswers: Int,
        var cheatAmount: Int
    )

    class Question(
        val question: String,
        val correctAnswer: Boolean
    )

    private val listOfQuestion = listOf(
        Question("Czy niuton w układzie SI jest jednostką siły?", true),
        Question("Czy resublimacja to przejście ze stanu gazowego w stały?", true),
        Question("Czy masa Księżyca jest większa od masy Ziemi?", false),
        Question("Czy najbliżsą gwiązda do Ziemi jest Słońce?", true),
        Question("Czy Amper to jednosta pojemności opornika?", false),
        Question("Czy siłę zapisujemy jako t?", false)
    )
    private val questionTextView: TextView by lazy { findViewById(R.id.textQuestionContent) }
    private val gameStats = QuizStats(0,0,0)
    private var currentPosition: Int = 1
    private var currentQuestion = listOfQuestion[currentPosition-1]

    private val secondActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        result ->
            if (result.resultCode == Activity.RESULT_OK)
                currentPosition = result.data!!.getIntExtra("cheat_reply", 0)
        setQuestion()

    }

    private fun setQuestion(){
        if(currentPosition > listOfQuestion.size){
            val statIntent = Intent(this, StatActivity::class.java).apply {
                putExtra("stat_data", StatOverview(gameStats.points, gameStats.correctAnswers, gameStats.cheatAmount))
            }
            startActivity(statIntent)
        }
        currentQuestion = listOfQuestion[currentPosition-1]
        questionTextView.text = currentQuestion.question

    }

    private fun checkAnswer(answer: Boolean){
        if(currentQuestion.correctAnswer == answer){
            gameStats.points += 10
            gameStats.correctAnswers += 1
        }
        currentPosition += 1
        if(currentPosition <= listOfQuestion.size){
            setQuestion()
        }
    }

    fun answerYes(view: View){
        checkAnswer(true)
        if(currentPosition > listOfQuestion.size){
            val statIntent = Intent(this, StatActivity::class.java).apply {
                putExtra("stat_data", StatOverview(gameStats.points, gameStats.correctAnswers, gameStats.cheatAmount))
            }
            startActivity(statIntent)
        }
    }

    fun answerNo(view: View){
        checkAnswer(false)
        if(currentPosition > listOfQuestion.size){
            val statIntent = Intent(this, StatActivity::class.java).apply {
                putExtra("stat_data", StatOverview(gameStats.points, gameStats.correctAnswers, gameStats.cheatAmount))
            }
            startActivity(statIntent)
        }
    }

    fun answerCheat(view: View){
        gameStats.points -= 15
        gameStats.cheatAmount += 1
        val cheatIntent = Intent(this, CheatActivity::class.java).apply {
            putExtra("cheat_answer", CheatQuestion(currentQuestion.question, currentQuestion.correctAnswer, currentPosition))
        }
        secondActivityResultLauncher.launch(cheatIntent)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState != null){
            currentPosition = savedInstanceState.getInt("game_state")
        }
        setQuestion()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("game_state", currentPosition)
    }
}