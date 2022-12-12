package pl.edu.uwr.pum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class StatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stat)
        val points: TextView by lazy { findViewById(R.id.textPoints) }
        val correct: TextView by lazy { findViewById(R.id.textCorrect) }
        val cheat: TextView by lazy { findViewById(R.id.textCheat)}
        val quizStat = intent.getParcelableExtra<StatOverview>("stat_data")
        if(quizStat != null){
            points.text = "Points granted: ${quizStat.points}"
            correct.text = "Correct answers: ${quizStat.correctAnswers}/6"
            cheat.text = "Amount of cheats: ${quizStat.cheatAmount}"
        }
    }
}