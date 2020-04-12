package sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val game = Game()
        setValues(game.getBoard())
        board.getSwipeDirection(
            left = {
                game.moveLeft()
                setValues(game.getBoard())
            },
            right = {
                game.moveRight()
                setValues(game.getBoard())
            },
            top = {
                game.moveTop()
                setValues(game.getBoard())
            },
            bottom = {
                game.moveBottom()
                setValues(game.getBoard())
            }
        )
    }

    private fun setValues(board: List<String>) {
        board.forEachIndexed { index, item ->
            findViewById<TextView>(boardItems[index]).text = item
        }
    }

    private fun View.getSwipeDirection(
        top: () -> Unit,
        bottom: () -> Unit,
        left: () -> Unit,
        right: () -> Unit
    ) {
        var startX = 0f
        var endX = 0f
        var startY = 0f
        var endY = 0f
        val reflect = {
            when {
                (endX - startX > 0 && endX - startX > endY - startY) -> right()
                (endY - startY > 0 && endX - startX < endY - startY) -> bottom()
                (endX - startX < 0 && abs(endX - startX) > abs(endY - startY)) -> left()
                (endY - startY < 0 && abs(endX - startX) < abs(endY - startY)) -> top()
            }
        }
        setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                startX = event.rawX - v.x
                startY = event.rawY - v.y
            }
            if (event.action == MotionEvent.ACTION_UP) {
                endX = event.rawX - v.x
                endY = event.rawY - v.y
                reflect()
            }
            true
        }

    }

    private val boardItems = arrayOf(
        R.id.tv1,
        R.id.tv2,
        R.id.tv3,
        R.id.tv4,
        R.id.tv5,
        R.id.tv6,
        R.id.tv7,
        R.id.tv8,
        R.id.tv9,
        R.id.tv10,
        R.id.tv11,
        R.id.tv12,
        R.id.tv13,
        R.id.tv14,
        R.id.tv15,
        R.id.tv16
    )

}