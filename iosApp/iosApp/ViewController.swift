import UIKit
import app

class ViewController: UIViewController {
    
    let game = Game()
        
    override func viewDidLoad() {
        super.viewDidLoad()
        game.getBoard()
        printBoard(board: game.getBoard())
        game.moveTop()
        printBoard(board: game.getBoard())
    }
    
    func printBoard(board:Array<String>) {
        var string = ""
        for (index, item) in board.enumerated(){
            if (index == 4 || index == 8 || index == 12){
                string.append("\n")
            }
            if item == "" {
                string.append("0")
            }else{
                string.append(item)
            }
        }
        print(string)
    }

}
