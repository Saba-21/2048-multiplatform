package sample

class Game {

    private var orientation: Direction? = null
    private val allBoard = 16
    private var board: Array<String> = arrayOf()
    private val outOfBoard = allBoard
    private var randPos = outOfBoard
    private var gameStarted = false
    private var emptyBoxes: BooleanArray = booleanArrayOf()
    private val startingValue = "2"
    private var score = 0
    private var changedBoxes: IntArray = intArrayOf()

    init {
        initView()
        initBoard()
    }

    fun getBoard() = board.toList()

    fun moveBottom() {
        orientation = Direction.BOTTOM
        moveValues()
        addToRandomPosition()
    }

    fun moveTop() {
        orientation = Direction.TOP
        moveValues()
        addToRandomPosition()
    }

    fun moveRight() {
        orientation = Direction.RIGHT
        moveValues()
        addToRandomPosition()
    }

    fun moveLeft() {
        orientation = Direction.LEFT
        moveValues()
        addToRandomPosition()
    }

    private fun getColumn(i: Int): Int {
        if ((i + 4) % 4 == 0) return 1
        if ((i + 3) % 4 == 0) return 2
        if ((i + 2) % 4 == 0) return 3
        return if ((i + 1) % 4 == 0) 4 else 0
    }

    private fun getRow(i: Int): Int {
        if (i > -1 && i < 4) return 1
        if (i in 4..7) return 2
        if (i in 8..11) return 3
        return if (i in 12..15) 4 else 0
    }

    private fun moveValues() {
        getEmptyBoxes()
        var middle: String
        when (orientation) {
            Direction.RIGHT -> {
                var i = allBoard - 1
                while (i > -1) {
                    if (board[i].isNotEmpty()) {
                        val ii = leastRight(getColumn(i), i)
                        if (board[i] == board[ii] && i != ii) {
                            val x = board[i].toInt() * 2
                            score += x
                            middle = x.toString()
                            changedBoxes[ii] = ii
                        } else middle = board[i]
                        board[i] = ""
                        board[ii] = middle
                        getEmptyBoxes()
                    }
                    i--
                }
            }
            Direction.LEFT -> {
                var i = 0
                while (i < allBoard) {
                    if (board[i].isNotEmpty()) {
                        val ii = leastLeft(getColumn(i), i)
                        if (board[i] == board[ii] && i != ii) {
                            val x = board[i].toInt() * 2
                            score += x
                            middle = x.toString()
                            changedBoxes[ii] = ii
                        } else middle = board[i]
                        board[i] = ""
                        board[ii] = middle
                        getEmptyBoxes()
                    }
                    i++
                }
            }
            Direction.TOP -> {
                var i = 0
                while (i < allBoard) {
                    if (board[i].isNotEmpty()) {
                        val ii = leastUp(getRow(i), i)
                        if (board[i] == board[ii] && i != ii) {
                            val x = board[i].toInt() * 2
                            score += x
                            middle = x.toString()
                            changedBoxes[ii] = ii
                        } else middle = board[i]
                        board[i] = ""
                        board[ii] = middle
                        getEmptyBoxes()
                    }
                    i++
                }
            }
            Direction.BOTTOM -> {
                var i = allBoard - 1
                while (i > -1) {
                    if (board[i].isNotEmpty()) {
                        val ii = leastDown(getRow(i), i)
                        if (board[i] == board[ii] && i != ii) {
                            val x = board[i].toInt() * 2
                            score += x
                            middle = x.toString()
                            changedBoxes[ii] = ii
                        } else middle = board[i]
                        board[i] = ""
                        board[ii] = middle
                        getEmptyBoxes()
                    }
                    i--
                }
            }
        }
    }

    private fun leastUp(i: Int, a: Int): Int {
        var least = a
        when {
            (i == 4 && (board[a - 12] == board[a] && emptyBoxes[a - 4] && emptyBoxes[a - 8] || emptyBoxes[a - 12])) -> {
                least = a - 12
            }
            (i == 4 && (board[a - 8] == board[a] && emptyBoxes[a - 4] || emptyBoxes[a - 8])) -> {
                least = a - 8
            }
            (i == 4 && (board[a - 4] == board[a] || emptyBoxes[a - 4])) -> {
                least = a - 4
            }
            (i == 4 && (board[a - 8] == board[a] && emptyBoxes[a - 4] || emptyBoxes[a - 8])) -> {
                least = a - 8
            }
            (i == 4 && (board[a - 4] == board[a] || emptyBoxes[a - 4])) -> {
                least = a - 4
            }
            (i == 4 && (board[a - 4] == board[a] || emptyBoxes[a - 4])) -> {
                least = a - 4
            }
            (i == 3 && (board[a - 8] == board[a] && emptyBoxes[a - 4] || emptyBoxes[a - 8])) -> {
                least = a - 8
            }
            (i == 3 && (board[a - 4] == board[a] || emptyBoxes[a - 4])) -> {
                least = a - 4
            }
            (i == 3 && (board[a - 4] == board[a] || emptyBoxes[a - 4])) -> {
                least = a - 4
            }
            (i == 2 && (board[a - 4] == board[a] || emptyBoxes[a - 4])) -> {
                least = a - 4
            }
        }
        return least
    }

    private fun leastDown(i: Int, a: Int): Int {
        var least = a
        when {
            (i == 1 && (board[a + 12] == board[a] && emptyBoxes[a + 4] && emptyBoxes[a + 8] || emptyBoxes[a + 12])) -> {
                least = a + 12
            }
            (i == 1 && (board[a + 8] == board[a] && emptyBoxes[a + 4] || emptyBoxes[a + 8])) -> {
                least = a + 8
            }
            (i == 1 && (board[a + 4] == board[a] || emptyBoxes[a + 4])) -> {
                least = a + 4
            }
            (i == 1 && (board[a + 8] == board[a] && emptyBoxes[a + 4] || emptyBoxes[a + 8])) -> {
                least = a + 8
            }
            (i == 1 && (board[a + 4] == board[a] || emptyBoxes[a + 4])) -> {
                least = a + 4
            }
            (i == 1 && (board[a + 4] == board[a] || emptyBoxes[a + 4])) -> {
                least = a + 4
            }
            (i == 2 && (board[a + 8] == board[a] && emptyBoxes[a + 4] || emptyBoxes[a + 8])) -> {
                least = a + 8
            }
            (i == 2 && (board[a + 4] == board[a] || emptyBoxes[a + 4])) -> {
                least = a + 4
            }
            (i == 2 && (board[a + 4] == board[a] || emptyBoxes[a + 4])) -> {
                least = a + 4
            }
            (i == 3 && (board[a + 4] == board[a] || emptyBoxes[a + 4])) -> {
                least = a + 4
            }
        }
        return least
    }

    private fun leastLeft(i: Int, a: Int): Int {
        var least = a
        when {
            i == 4 && (board[a - 3] == board[a] && emptyBoxes[a - 2] && emptyBoxes[a - 1] || emptyBoxes[a - 3]) -> {
                least = a - 3
            }
            i == 4 && (board[a - 2] == board[a] && emptyBoxes[a - 1] || emptyBoxes[a - 2]) -> {
                least = a - 2
            }
            i == 4 && (board[a - 1] == board[a] || emptyBoxes[a - 1]) -> {
                least = a - 1
            }
            i == 4 && (board[a - 2] == board[a] && emptyBoxes[a - 1] || emptyBoxes[a - 2]) -> {
                least = a - 2
            }
            i == 4 && (board[a - 1] == board[a] || emptyBoxes[a - 1]) -> {
                least = a - 1
            }
            i == 4 && (board[a - 1] == board[a] || emptyBoxes[a - 1]) -> {
                least = a - 1
            }
            i == 3 && (board[a - 2] == board[a] && emptyBoxes[a - 1] || emptyBoxes[a - 2]) -> {
                least = a - 2
            }
            i == 3 && (board[a - 1] == board[a] || emptyBoxes[a - 1]) -> {
                least = a - 1
            }
            i == 3 && (board[a - 1] == board[a] || emptyBoxes[a - 1]) -> {
                least = a - 1
            }
            i == 2 && (board[a - 1] == board[a] || emptyBoxes[a - 1]) -> {
                least = a - 1
            }
        }
        return least
    }

    private fun leastRight(i: Int, a: Int): Int {
        var least = a
        when {
            i == 1 && (board[a + 3] == board[a] && emptyBoxes[a + 2] && emptyBoxes[a + 1] || emptyBoxes[a + 3]) -> {
                least = a + 3
            }
            i == 1 && (board[a + 2] == board[a] && emptyBoxes[a + 1] || emptyBoxes[a + 2]) -> {
                least = a + 2
            }
            i == 1 && (board[a + 1] == board[a] || emptyBoxes[a + 1]) -> {
                least = a + 1
            }
            i == 1 && (board[a + 2] == board[a] && emptyBoxes[a + 1] || emptyBoxes[a + 2]) -> {
                least = a + 2
            }
            i == 1 && (board[a + 1] == board[a] || emptyBoxes[a + 1]) -> {
                least = a + 1
            }
            i == 1 && (board[a + 1] == board[a] || emptyBoxes[a + 1]) -> {
                least = a + 1
            }
            i == 2 && (board[a + 2] == board[a] && emptyBoxes[a + 1] || emptyBoxes[a + 2]) -> {
                least = a + 2
            }
            i == 2 && (board[a + 1] == board[a] || emptyBoxes[a + 1]) -> {
                least = a + 1
            }
            i == 2 && (board[a + 1] == board[a] || emptyBoxes[a + 1]) -> {
                least = a + 1
            }
            i == 3 && (board[a + 1] == board[a] || emptyBoxes[a + 1]) -> {
                least = a + 1
            }
        }
        return least
    }

    private fun initBoard() {
        if (!gameStarted) {
            randomPosition()
            board[randPos] = startingValue
            randomPosition()
            board[randPos] = startingValue
            gameStarted = true
        }
        setValuesToBoard()
    }

    private fun addToRandomPosition() {
        randomPosition()
        if (randPos != outOfBoard)
            board[randPos] = startingValue
        setValuesToBoard()
    }

    private fun getEmptyBoxes() {
        for (i in 0 until allBoard)
            emptyBoxes[i] = board[i] == ""
    }

    private fun randomPosition() {
        getEmptyBoxes()
        if (!emptyBoxesOver()) {
            if (emptyElements() == 1 && lastElement() != outOfBoard) {
                randPos = lastElement()
            } else {
                val rand = (0 until 16).random()
                if (emptyBoxes[rand])
                    randPos = rand
                else
                    randomPosition()
            }
        }
    }

    private fun emptyElements(): Int {
        var x = 0
        for (i in 0 until allBoard)
            if (emptyBoxes[i])
                x += 1
        return x
    }

    private fun lastElement(): Int {
        for (i in 0 until allBoard)
            if (emptyBoxes[i])
                return i
        return outOfBoard
    }

    private fun emptyBoxesOver(): Boolean {
        var x = 0
        for (i in 0 until allBoard)
            if (emptyBoxes[i])
                x += 1
        return x == 0
    }

    private fun setValuesToBoard() {
        for (i in 0 until allBoard) {
            getEmptyBoxes()
            if (i == changedBoxes[i] && board[i].isNotEmpty() && !emptyBoxes[i]) {
                changedBoxes[i] = outOfBoard
            }
            if (i == randPos) {
                randPos = outOfBoard
            }
        }
    }

    private enum class Direction {
        TOP, BOTTOM, LEFT, RIGHT
    }

    private fun initView() {
        emptyBoxes = BooleanArray(allBoard)
        changedBoxes = IntArray(allBoard)
        board =
            arrayOf("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
        for (i in 0 until allBoard) {
            changedBoxes[i] = outOfBoard
        }
    }

}