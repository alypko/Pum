package pl.edu.uwr.pum

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class CheatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        val cheatData = intent.getParcelableExtra<CheatQuestion>("cheat_answer")
        val questionText: TextView by lazy { findViewById(R.id.textQuestion)}
        val questionAnswer: TextView by lazy { findViewById(R.id.textQuestionAnswer) }
        if(cheatData != null){
            questionText.text = cheatData.question
            if (cheatData.anwser){
                questionAnswer.text = "Tak"
            }
            else
                questionAnswer.text = "Nie"
        }
    }
    fun returnMessage(view: View){
        val cheatData = intent.getParcelableExtra<CheatQuestion>("cheat_answer")
        setResult(
            RESULT_OK,
            Intent().apply {
                putExtra("cheat_reply",
                    cheatData!!.position+1)
            })
        finish()
    }
}