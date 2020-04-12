package sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val main = Game()
        print(main)
        findViewById<TextView>(R.id.main_text).setOnClickListener {
            main.moveTop()
            print(main)
        }
    }

    private fun print(game: Game) {
        val string = StringBuffer()
        string.append("\n")
        game.getBoard().forEachIndexed { index, s ->
            if (index == 4 || index == 8 || index == 12)
                string.append("\n")
            string.append(if (s.isEmpty()) "0" else s)
        }
        Log.e("value", string.toString())
    }

}