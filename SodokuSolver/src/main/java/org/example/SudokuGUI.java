package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SudokuGUI extends JFrame {
    private final JTextField[][] cells = new JTextField[9][9];
    private final int[][] originalBoard = new int[9][9];
    private final Random random = new Random();

    public SudokuGUI() {
        setTitle("Sudoku Solver");
        setSize(400, 400);
        setLayout(new GridLayout(9, 9));
        initializeGrid();
        addSolveButton();
        addResetButton();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeGrid() {
        // Generate a random Sudoku grid
        int[][] board = generateRandomSudoku();

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                cells[row][col].setFont(new Font("Arial", Font.BOLD, 20));

                // Fill some cells and leave others empty to create a playable puzzle
                if (board[row][col] != 0) {
                    cells[row][col].setText(String.valueOf(board[row][col]));
                    cells[row][col].setEditable(false); // Pre-filled cells are not editable
                    originalBoard[row][col] = board[row][col]; // Store original value
                } else {
                    cells[row][col].setText(""); // Empty cell
                    originalBoard[row][col] = 0; // No original value
                }
                add(cells[row][col]);
            }
        }
    }

    private int[][] generateRandomSudoku() {
        int[][] board = new int[9][9];

        // Fill the diagonal 3x3 grids
        for (int i = 0; i < 9; i += 3) {
            fillDiagonalGrid(board, i, i);
        }

        // Fill the remaining cells
        fillRemaining(board);

        // Remove some numbers to create a puzzle
        removeRandomNumbers(board, 40); // Adjust the number to control difficulty

        return board;
    }

    private void fillDiagonalGrid(int[][] board, int row, int col) {
        boolean[] used = new boolean[10]; // Used numbers from 1 to 9

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num;
                do {
                    num = random.nextInt(9) + 1; // Generate a number from 1 to 9
                } while (used[num]); // Ensure the number hasn't been used
                used[num] = true; // Mark the number as used
                board[row + i][col + j] = num; // Place the number in the grid
            }
        }
    }

    private boolean fillRemaining(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;
                            if (fillRemaining(board)) {
                                return true;
                            }
                            board[row][col] = 0; // Backtrack
                        }
                    }
                    return false; // No valid number found
                }
            }
        }
        return true; // Filled successfully
    }

    private boolean isSafe(int[][] board, int row, int col, int num) {
        // Check row and column
        for (int x = 0; x < 9; x++) {
            if (board[row][x] == num || board[x][col] == num) {
                return false;
            }
        }

        // Check 3x3 grid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private void removeRandomNumbers(int[][] board, int count) {
        while (count != 0) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);

            if (board[row][col] != 0) {
                board[row][col] = 0; // Remove the number
                count--;
            }
        }
    }

    private void addSolveButton() {
        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] board = new int[9][9];
                for (int row = 0; row < 9; row++) {
                    for (int col = 0; col < 9; col++) {
                        String text = cells[row][col].getText();
                        board[row][col] = text.isEmpty() ? 0 : Integer.parseInt(text);
                    }
                }

                SudokuSolver solver = new SudokuSolver();
                if (solver.solveSudoku(board)) {
                    for (int row = 0; row < 9; row++) {
                        for (int col = 0; col < 9; col++) {
                            cells[row][col].setText(String.valueOf(board[row][col]));
                            cells[row][col].setEditable(false); // Make solved cells non-editable
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(SudokuGUI.this, "No solution exists!");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(solveButton);
        add(panel);
    }

    private void addResetButton() {
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGrid();
            }
        });

        JPanel panel = new JPanel();
        panel.add(resetButton);
        add(panel);
    }

    private void resetGrid() {
        // Restore original values to the grid
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (originalBoard[row][col] != 0) {
                    cells[row][col].setText(String.valueOf(originalBoard[row][col]));
                } else {
                    cells[row][col].setText(""); // Clear user-modified cells
                }
                cells[row][col].setEditable(originalBoard[row][col] == 0); // Make empty cells editable
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SudokuGUI::new);
    }
}
