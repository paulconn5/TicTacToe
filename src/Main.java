import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String p1 = "X";
        String p2 = "O";
        int row;
        int column;
        int totalMoves;
        boolean done = false;
        boolean isActive = true;
        String[][] board = new String[3][3];
        Scanner in = new Scanner(System.in);

        //Main Game Loop
        do {
            resetBoard(board);
            totalMoves = 0;
            do {
                boolean p1Done = false;
                boolean p2Done = false;
                do {
                    //Player 1 turn
                    displayBoard(board);
                    row = getRangedInt(in, "\nPlayer X's turn! Enter the row ", 0, 2);
                    column = getRangedInt(in, "\nPlayer X's turn! Enter the column ", 0, 2);
                    if (isValidMove(board, row, column)) {
                        playerMove(board, p1, row, column);
                        p1Done = true;
                        totalMoves++;
                    } else {System.out.println("Position is occupied!");}
                } while (!p1Done);

                if (!checkBoard(board, totalMoves)) {
                    isActive = false;
                    break; }


                do {
                    //Player 2 turn
                    displayBoard(board);
                    row = getRangedInt(in, "\nPlayer O's turn! Enter the row ", 0, 2);
                    column = getRangedInt(in, "\nPlayer O's turn! Enter the column ", 0, 2);
                    if (isValidMove(board, row, column)) {
                        playerMove(board, p2, row, column);
                        p2Done = true;
                        totalMoves++;
                    } else {System.out.println("Position is occupied!");}
                } while(!p2Done);

                if (!checkBoard(board, totalMoves)) {
                    isActive = false;
                    break; }

            } while(isActive);
            done = getYNConfirm(in, "Would you like to keep playing? [Y/N]");
        } while(!done);

    }

    private static boolean checkBoard(String[][] board, int totalMoves){
        boolean isActive = true;
        if (checkConditions(board, "X")) {
            displayBoard(board);
            System.out.println("\nPlayer X wins!");
            isActive = false;
        }
        if (checkConditions(board, "O")) {
            displayBoard(board);
            System.out.println("\nPlayer O wins!");
            isActive = false;
        }
        //Check if the board is full
        if ( totalMoves >= 9) {
            displayBoard(board);
            System.out.println("\nIts a tie!");
            isActive = false;
        }
        return isActive;
    }
    //Player Input move
    private static void playerMove (String[][] board, String player, int row, int column) {
        if (player.equalsIgnoreCase("X")) {
            board[row][column] = "X";
        }
        if (player.equalsIgnoreCase("O")) {
            board[row][column] = "O";
        }
    }
    //Set board moves to blank
    private static void resetBoard (String[][] board) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                board[x][y] = " ";
            }
        }
    }
    //Display the board and players moves
    private static void displayBoard(String[][] board) {
        for (int x = 0; x<3; x++) {
            System.out.print("\n");
            for (int y = 0; y<3; y++) {
                System.out.print("|");
                System.out.print(" " + board[x][y] + " ");
            }
            System.out.print("|");
        }
    }

    //Check if a player can put a spot on the board
    private static boolean isValidMove(String[][] board, int x, int y) {
        return board[x][y].equalsIgnoreCase(" ");
    }


    private static boolean checkConditions(String[][] board, String player) {
        boolean won = false;
        //Vertical
        for (int i = 0; i<3; i++) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                won = true;
            }
        }

        //Horizontels
        for (int i = 0; i<3; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                won = true;
            }
        }

        //Diagnols
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            won = true;
        } else if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            won = true;
        }
        return won;
    }

    private static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        boolean done = false;
        int retInt = 0;
        do {
            System.out.print("\n" + prompt + low + "-" + high + ": "); // show prompt add space
            if(pipe.hasNextInt()) {
                retInt = pipe.nextInt();
                if (retInt >= low && retInt <= high) {
                    done = true;
                }
                else {
                    System.out.println("You must enter an integer between " + low + "-" + high);
                }
            } else {
                System.out.println("You must enter an integer.");
            }
            pipe.nextLine();
        }while(!done);
        return retInt;
    }

    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        boolean complete = false;
        boolean retBool = false;
        String retString;
        do {
            System.out.print("\n" + prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
            if (retString.equalsIgnoreCase("Y")) {
                complete = true;
            }
            else if (retString.equalsIgnoreCase("N")) {
                complete = true;
                retBool = true;
            }
            else {
                System.out.println("Enter [Y/N]");
            }
        }while(!complete);
        return retBool;
    }
}