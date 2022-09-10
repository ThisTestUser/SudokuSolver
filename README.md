# SudokuSolver
A solver for many types of Sudokus.

## How To Use
After importing the code to your IDE, head over to Main.java to add your problem to ```SUDOKU_PROBLEMS```. You should create a new instance of ```SudokuProblem```, which consists of the board as a 2D array of integers, and an instance of ```SudokuConstraints```. That constructor accepts the Sudoku size and subsection dimensions. If there are any additional rules for the Sudoku, you will need to specify them also.

Alternatively, add this program as a library to your own Java program and run ```Main.main()```, after you've added the problem to ```SUDOKU_PROBLEMS``` in your program.

**Special Note:** If you have a SudokuXV and you have set sum5 and sum10, you should enable ```noSum5``` and ```noSum10```, as one of the rules in SudokuXV prohibits numbers next to each other from summing up to 5 or 10 if not marked. For consective sudokus, ```nonConsecutive``` should be enabled after ```consecutive``` is filled.

## Features
* Solve regular Sudokus and variants of Sudokus

* Guaranteed to solve, if the Sudoku has a solution

* Deterministic - For any input, will always output the same solution, even if there are multiple solutions

## Downsides
* Takes a long time to input custom rules

* Can't tell the user if there are multiple solutions

* Not the best algorithm - This program uses brute force to solve the Sudoku. This works well in normal Sudokus, but gets problematic when there are less than 10 clues in the Sudoku. In Killer Sudokus and Greater Than Sudokus, this might lead to the program taking hours to complete. Try to make sure at least 8 cells are filled in to minimize running time.

For example, this Sudoku below (Greater Than) took 3 minutes to solve with 9 clues provided.
![greaterthan](https://user-images.githubusercontent.com/15678918/60406990-b10f3e80-9b86-11e9-8b33-dd5d0a3260f2.png)

