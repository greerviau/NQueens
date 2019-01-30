# NQueens
## Decription
The N Queen is the problem of placing N chess queens on an N×N chessboard so that no two queens attack each other.
![nqueens-player](https://user-images.githubusercontent.com/36581610/51954532-90439100-240e-11e9-88da-095cba8c795e.gif)

## Backtracking Algorithm
This algorithm starts from the leftmost column and attempts to place a queen in the first row. If it can it moves to the next column and attempts the place a queen in the first row, if it can't then it tries in the next row up. If there are no spots to place a queen in a column, then it backtracks and places the queen of the previous column in the next row up. It repeats this algorithm until it places N queens on the board
![nqueens-ai](https://user-images.githubusercontent.com/36581610/51954543-9fc2da00-240e-11e9-98df-1c8a227d6040.gif)
