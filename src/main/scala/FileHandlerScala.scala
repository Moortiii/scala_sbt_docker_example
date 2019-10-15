import java.io.FileOutputStream
import java.nio.file.{Files, Paths}

import neighbors.Result

object FileHandlerScala {
  def ParsePuzzles():List[Board] = {
    val neighbors = Result.parseFrom(Files.readAllBytes(Paths.get("puzzle_unsolved.bin")))
    var boards = List[Board]()

    for(p <- neighbors.puzzles) {
      val puzzle = Puzzle(p.size)

      var squares = List[Square]()

      for(sq <- p.squares) {
        var square = Square(sq.x, sq.y, puzzle, sq.values.toList)

        if(sq.neighborDown)
          square = square.addNeighbor(Direction.DOWN)

        if(sq.neighborLeft)
          square = square.addNeighbor(Direction.LEFT)

        if(sq.neighborRight)
          square = square.addNeighbor(Direction.RIGHT)

        if(sq.neighborUp)
          square = square.addNeighbor(Direction.UP)

        squares :+= square
      }

      boards :+= Board(squares, puzzle)
    }

    boards
  }
  
  def WriteOutput(boards:List[Board], filename:String):Unit = {
      var result = Result()
      
      for(board <- boards.reverse) {
        var puzzle = neighbors.Puzzle().withSize(board.puzzle.size)
        
        for(sq <- board.squares) {
          var square = neighbors.Square()
          
          if(sq.solved) {
            square = square.withValues(Seq(sq.values(0)))
          } else {
            square = square.withValues(Seq(0))
          }
          
          puzzle = puzzle.addSquares(square)
        }
        
        result = result.addPuzzles(puzzle)
      }
    
    result.writeTo(new FileOutputStream(filename))
  }
}