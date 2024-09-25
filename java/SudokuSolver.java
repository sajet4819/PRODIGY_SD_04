public class SudokuSolver {

    // Define the size of the Sudoku grid
    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {
        // Define an unsolved Sudoku puzzle (0 represents an empty cell)
        int[][] board = {
            {7, 0, 2, 0, 5, 0, 6, 0, 0},
            {0, 0, 0, 0, 0, 3, 0, 0, 0},
            {1, 0, 0, 0, 0, 9, 5, 0, 0},
            {8, 0, 0, 0, 0, 0, 0, 9, 0},
            {0, 4, 3, 0, 0, 0, 7, 5, 0},
            {0, 9, 0, 0, 0, 0, 0, 0, 8},
            {0, 0, 9, 7, 0, 0, 0, 0, 5},
            {0, 0, 0, 2, 0, 0, 0, 0, 0},
            {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };

        if (solveBoard(board)) {
            System.out.println("Sudoku solved successfully!");
        } else {
            System.out.println("This Sudoku puzzle cannot be solved.");
        }

        printBoard(board);
    }

    // Function to solve the Sudoku puzzle using backtracking
    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                // Find an empty cell (denoted by 0)
                if (board[row][col] == 0) {
                    // Try placing numbers from 1 to 9
                    for (int num = 1; num <= GRID_SIZE; num++) {
                        if (isValidPlacement(board, num, row, col)) {
                            board[row][col] = num;

                            // Recursively attempt to solve the rest of the board
                            if (solveBoard(board)) {
                                return true;
                            }

                            // If placing num doesn't lead to a solution, backtrack
                            board[row][col] = 0;
                        }
                    }

                    // If no valid number can be placed, return false
                    return false;
                }
            }
        }

        // If no empty cell is found, the puzzle is solved
        return true;
    }

    // Function to check if placing num in board[row][col] is valid
    private static boolean isValidPlacement(int[][] board, int num, int row, int col) {
        // Check if the number exists in the same row
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        // Check if the number exists in the same column
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // Check if the number exists in the 3x3 sub-grid
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;
        for (int i = boxRowStart; i < boxRowStart + 3; i++) {
            for (int j = boxColStart; j < boxColStart + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // Function to print the Sudoku board
    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }

            for (int col = 0; col < GRID_SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}
