/**
 * Class implementing all the game functionality
 */
import java.util.Scanner;

public class TicTacToe {
    private static char[][] matrix = new char[3][3];
    private static Scanner scanner = new Scanner(System.in);

    public static char[][] getMatrix() {
        return matrix;
    }

    public static void initGame() {
        /**
         * This method is (re) initializing the game and provides functionality
         * for the actual game mechanism
         */
        for (int i = 0; i < getMatrix().length; i++) {
            for (int j = 0; j < getMatrix().length; j++) {
                getMatrix()[i][j] = ' ';
            }
        }
        displayList();
        int row, column;
        char player = 'x';
        boolean flag = true;
        while (flag) {
            if (!isFull()) {
                System.out.println("Player '" + player + "', enter your move (row[1-3] column[1-3]):");
                row = askInputInt(1, 3);
                column = askInputInt(1, 3);
                if (checkIfValidMove(row - 1, column - 1, player)) {
                    displayList();
                    if (checkIfWin(player)) {
                        System.out.println("Player '" + player + "' won !");
                        if (askUserForMore()) {
                            flag = false;
                            initGame();
                        } else {
                            flag = false;
                        }
                    } else {
                        if (player == 'x') {
                            player = 'o';
                        } else {
                            player = 'x';
                        }
                    }
                } else {
                    System.out.println("This move at (" + row + "," + column + ") is not valid. Try again...");
                }
            } else {
                System.out.println("Draw match !");
                if (askUserForMore()) {
                    flag = false;
                    initGame();
                } else {
                    flag = false;
                }
            }
        }

    }

    private static void displayList() {
        /**
         * This method is displaying the game board(matrix) each time it is invoked
         */
        for (int i = 0; i < getMatrix().length; i++) {
            for (int j = 0; j < getMatrix().length; j++) {
                if (j != 0) {
                    System.out.print(" | " +getMatrix()[i][j] + " ");
                } else {
                    System.out.print(getMatrix()[i][j] + " ");
                }
                if (j == 2) {
                    System.out.println();
                }

            }
            System.out.println("-----------");
        }
    }

    public static boolean checkIfValidMove(int row, int column, char player) {

        /**
         * A method to check if the matrix has empty slot in the specified position.
         * Inserting char 'x' or 'o' (player) if slot is free.
         *
         * @param row specifies the coordinate for the row.
         * @param column specifies the coordinate for the column.
         * @param player can be 'x' or 'o'.
         * @return Returns true if the move is valid, false otherwise
         */

        if (getMatrix()[row][column] == ' ') {
            getMatrix()[row][column] = player;
            return true;
        } else {
            return false;
        }

    }

    public static boolean checkIfWin(char c) {
        /**
         * A method to check all rows, columns and diagonals for the winning pattern.
         * @param c can be 'x' or 'o'. Type of symbol to lookup for.
         * @return Returns true if any win pattern is satisfied, false otherwise
         */
        char[][] m = getMatrix();
        if (m[0][0] == c) {
            if (m[1][1] == c && m[2][2] == c) {
                return true;
            }
            if (m[0][1] == c && m[0][2] == c) {
                return true;
            }
            if (m[1][0] == c && m[2][0] == c) {
                return true;
            }
        }
        if (m[1][0] == c && m[1][1] == c && m[1][2] == c) {
            return true;
        }
        if (m[2][0] == c) {
            if (m[1][1] == c && m[0][2] == c) {
                return true;
            }
            if (m[2][1] == c && m[2][2] == c) {
                return true;
            }
        }
        if (m[0][1] == c && m[1][1] == c && m[2][1] == c) {
            return true;
        }
        if (m[0][2] == c && m[1][2] == c && m[2][2] == c) {
            return true;
        }
        return false;
    }

    public static boolean isFull() {
        /**
         * A method to check if the game has reached to the end(9 moves).
         * @return Returns true if game board(matrix) is full with 'x' or '0', false otherwise.
         */
        int counter = 0;
        for (int i = 0; i < getMatrix().length; i++) {
            for (int j = 0; j < getMatrix().length; j++) {
                if (getMatrix()[i][j] == 'x' || getMatrix()[i][j] == 'o') {
                    counter++;
                }
            }
        }
        return counter == 9;
    }

    public static boolean askUserForMore () {
        /**
         * A method to check if the players wishes to start a new game.
         * @return Returns true for positive answer, false otherwise.
         */
        System.out.println("Play again ? press '1' for another game or '0' to quit");
        int answer = askInputInt(0, 1);
        return answer == 1;
    }

    public static int askInputInt(int lowerLimit, int higherLimit){
        /**
         * A method used for repeatedly asking the user for input until
         * the input is valid. If condition is used,
         * input is measured against it.
         * @param lowerLimit lowest limit accepted for input.
         * @param higherLimit highest limit accepted for input.
         * @return Returns the final value of the accepted input, as an integer.
         */
        Boolean error;
        String userInp;
        do {
            userInp = scanner.next();
            if (!isType(userInp, "int")) {
                error = true;
                System.err.println("Not valid: must be a whole number, in the range " + lowerLimit + " - " + higherLimit);
            } else {
                if (Integer.parseInt(userInp) < lowerLimit || Integer.parseInt(userInp) > higherLimit) {
                    System.err.println("Not valid: must be between " + lowerLimit + " and " + higherLimit);
                    error = true;
                } else {
                    error = false;
                }
            }
        } while (error);
        return Integer.parseInt(userInp);
    }

    public static Boolean isType (String testStr, String type){
        /**
         * Tests if a specific input can be converted to a specific type.
         * @param testStr The input to test. Accepts String, int or double.
         * @param type    Which type to test against. Accepts 'int'.
         * @return Boolean    True if can be transformed to requested type. False otherwise.
         */
        try {
            if (type.equalsIgnoreCase("int")) {
                Integer.parseInt(testStr);
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
