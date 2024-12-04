import java.util.Scanner;

public class TicTacToe {
    private static int boardLength = 3;
    private static String [][] board =  new String[boardLength][boardLength];
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("welcome to tictactoe");
        boolean goAgain;
        String playerOne = "X";
        String playerTwo = "O";
        String player;
        int row;
        int collum;
        int moveCount = 0;
        clearBoard();
        do {
            do {
                displayBoard();
                if (moveCount % 2 == 0){
                    player = playerOne;

                } else {
                    player = playerTwo;
                }

                do {
                    row = InputHelper.getRangedInt(scan, "Which row would you like to place your " + player + "[1-" + boardLength + "]",1,boardLength)-1;
                    collum = InputHelper.getRangedInt(scan, "Which collum would you like to place your " + player + "[1-" + boardLength + "]",1,boardLength)-1;

                } while (!isValidMove(row,collum));
                    board[row][collum] = player;
                    if(isWin(player)){
                        displayBoard();
                        System.out.println("Player " + player + " wins");
                        break;
                    }

                moveCount++;
            } while (!isTie());
            if (isTie()){
                displayBoard();
                System.out.println("You guys tied");
            }
            clearBoard();
            moveCount = 0;
            goAgain = InputHelper.getYNConfirm(scan,"Would you like to play again");
        } while (goAgain);

    }

    private static void  clearBoard(){
        for(int r = 0; r < board.length; r++){
            for (int c = 0; c < board[0].length; c++){
                board[r][c] = "-";
            }
        }
    }

    private  static void displayBoard(){
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[0].length; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isValidMove(int row, int col){
        if(board[row][col].equalsIgnoreCase("-")){
            return true;
        } else {
            return false;
        }
    }

    private static boolean isWin(String player){
        if(isColWin(player) || isRowWin(player) || isDiagonalWin(player)){
            return true;
        } else {
            return false;
        }
    }

    private static boolean isColWin(String player){
        boolean matches = true;
        for (int c = 0; c < board[0].length; c++){
            matches = true;
            for(int r = 0; r < board.length; r++){
                if(!player.equals(board[r][c])){
                    matches = false;
                    break;
                }
            }
            if (matches){
                break;
            }
        }
        return matches;
    }

    private static boolean isRowWin(String player){
        boolean matches = true;
        for (int r = 0; r < board.length; r++){
            matches = true;
            for(int c = 0; c < board[0].length; c++){
                if(!player.equals(board[r][c])){
                    matches = false;
                    break;
                }
            }
            if (matches){
                break;
            }

        }
        return matches;
    }

    private static boolean isDiagonalWin(String player){
        boolean diagOne = true;
        boolean diagTwo = true;
        boolean matches = false;
        for (int r = 0; r < board.length; r++){
                if(!player.equals(board[r][r])) {
                    diagOne = false;
                }
                if(!player.equals(board[r][board.length-(r+1)])){
                    diagTwo = false;
                }
        }

        if(diagOne || diagTwo){
            matches = true;

        }
        return matches;

    }

    private static boolean isTie(){
        boolean tie = false;
        boolean full = true;
        for (int r = 0; r < board.length; r++){
            for (int c = 0; c < board[0].length; c++){
                if(board[r][c].equals("-")) {
                    full = false;
                    break;
                }
            }
        }
        if(full && !isWin("X") || !isWin("O") && full){
            tie = true;
        }
        return tie;
    }

}