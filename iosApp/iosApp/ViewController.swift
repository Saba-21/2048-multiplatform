import UIKit
import app

class ViewController: UIViewController {
    
    @IBOutlet weak var tv1: UILabel!
    @IBOutlet weak var tv2: UILabel!
    @IBOutlet weak var tv3: UILabel!
    @IBOutlet weak var tv4: UILabel!
    @IBOutlet weak var tv5: UILabel!
    @IBOutlet weak var tv6: UILabel!
    @IBOutlet weak var tv7: UILabel!
    @IBOutlet weak var tv8: UILabel!
    @IBOutlet weak var tv9: UILabel!
    @IBOutlet weak var tv10: UILabel!
    @IBOutlet weak var tv11: UILabel!
    @IBOutlet weak var tv12: UILabel!
    @IBOutlet weak var tv13: UILabel!
    @IBOutlet weak var tv14: UILabel!
    @IBOutlet weak var tv15: UILabel!
    @IBOutlet weak var tv16: UILabel!
    @IBOutlet weak var vBackground: UIImageView!
    
    let game = Game()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setValuesToBoard(values: game.getBoard())
        setGestureRecognizers()
    }
    
    @objc func handleSwipe(_ sender:UISwipeGestureRecognizer){
        if sender.direction == .left {
            game.moveLeft()
        }
        if sender.direction == .right {
            game.moveRight()
        }
        if sender.direction == .up {
            game.moveTop()
        }
        if sender.direction == .down {
            game.moveBottom()
        }
        setValuesToBoard(values: game.getBoard())
    }
    
    func setGestureRecognizers() {
        let leftSwipe = UISwipeGestureRecognizer(target: self, action: #selector(handleSwipe(_:)))
        leftSwipe.direction = .left
        let rightSwipe = UISwipeGestureRecognizer(target: self, action: #selector(handleSwipe(_:)))
        rightSwipe.direction = .right
        let topSwipe = UISwipeGestureRecognizer(target: self, action: #selector(handleSwipe(_:)))
        topSwipe.direction = .up
        let bottomSwipe = UISwipeGestureRecognizer(target: self, action: #selector(handleSwipe(_:)))
        bottomSwipe.direction = .down
        vBackground.isUserInteractionEnabled = true
        vBackground.addGestureRecognizer(leftSwipe)
        vBackground.addGestureRecognizer(rightSwipe)
        vBackground.addGestureRecognizer(topSwipe)
        vBackground.addGestureRecognizer(bottomSwipe)
    }
    
    func setValuesToBoard(values:Array<String>){
        let boardViews = [tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10, tv11, tv12, tv13, tv14, tv15, tv16]
        for(index,item) in values.enumerated(){
            boardViews[index]?.text = item
        }
    }
    
}
