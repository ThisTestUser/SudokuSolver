# SudokuSolver
A solver for many types of Sudoku. This project was made to be run in your IDE, so no compilied binaries will be provided.
## How To Use
After importing the code to your IDE, edit the Sudoku grid in SudokuSolver.java to fit your problem. If your Sudoku has custom constraints, head over to SudokuConstraints.java to see which ones you would like.

Then, go to setupConstraints() in SudokuSolver.java to set them.

## Features
* Solve regular Sudokus and variants of sudokus

* Guaranteed to solve, if the Sudoku has a solution

* Deterministic - For any input, will always output the same solution, even if there are multiple solutions

## Downsides
* Takes a long time to input custom rules

* Can't tell the user if there are multiple solutions

* Not the best algorithm - This program uses brute force to solve the Sudoku. This works well in normal Sudokus, but gets problematic when there are less than 10 clues in the Sudoku. In Killer Sudokus and Greater Than Sudokus, this might lead to the program taking hours to complete. Try to make sure at least 8 cells are filled in to minimize running time.

For example, this Sudoku below (Greater Than) took 3 minutes to solve with 9 clues provided.
![greaterthan](https://user-images.githubusercontent.com/15678918/60406990-b10f3e80-9b86-11e9-8b33-dd5d0a3260f2.png)

