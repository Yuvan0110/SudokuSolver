# Sudoku Solver with GUI

## Overview
This is a simple **Sudoku Solver** application with a graphical user interface (GUI) that allows users to solve Sudoku puzzles. The application features manual number input, random puzzle generation, a solver using a backtracking algorithm, and a reset function to restore the puzzle to its initial state.

The project uses **Java Swing** for the user interface and a backtracking algorithm for solving the Sudoku puzzle programmatically.

## Features
- **Manual Input**: Users can manually solve the puzzle by entering numbers.
- **Random Puzzle Generation**: A new Sudoku puzzle is generated each time the program starts.
- **Auto Solver**: The puzzle can be solved automatically using the backtracking algorithm.
- **Reset Button**: Restores the puzzle to its original state after being solved or modified.
- **Validation**: Ensures numbers comply with Sudoku rules (no duplicates in rows, columns, or 3x3 grids).

## Project Structure

```bash
SudokuSolver/
├── src/
│   ├── SudokuGUI.java        # Handles the graphical user interface and user interactions
│   └── SudokuSolver.java     # Contains the backtracking algorithm to solve the puzzle
└── README.md
```

## Installation

### Clone the Repository
To get a copy of the project, clone the repository using:
```bash
git clone https://github.com/Yuvan0110/SudokuSolver.git
```
## Compilation
Navigate to the src/ directory and compile the Java files using the following commands:
```bash
cd SudokuSolver/src
javac SudokuGUI.java SudokuSolver.java
```
## Running the Application
After compiling, run the SudokuGUI class to launch the GUI:
```bash
java SudokuGUI
```

## How to Use
### Random Puzzle Generation
- A new random Sudoku puzzle is generated when the application starts.
- Users can solve it manually by clicking on empty cells and entering numbers.
### Manual Input
- Click any empty cell and enter a number between 1 and 9.
- The program automatically validates the input according to Sudoku rules (no duplicates in rows, columns, or grids).
### Solve Button
- Click the Solve button to solve the current puzzle using the backtracking algorithm.
### Reset Button
- Click the Reset button to revert the puzzle to its initial state (before any numbers were added or changed).
### Backtracking Algorithm
- The SudokuSolver class uses a backtracking algorithm to solve the Sudoku puzzle:

- It iterates over empty cells, trying each number from 1 to 9.
- If a number violates the Sudoku constraints (row, column, or grid), it backtracks and tries the next number.
- Once a solution is found, the grid is filled with the correct numbers.
## Future Enhancements
- Puzzle Difficulty Levels: Add easy, medium, and hard modes for generating puzzles with varying difficulty.
- Hints Feature: Add the ability to provide hints for the next possible move.
- Save/Load Functionality: Allow users to save and load their progress.
- Timer: Include a timer to track the time taken to solve a puzzle.
