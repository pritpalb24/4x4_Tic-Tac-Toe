package cmpt213.asn4.tictactoe.game;

/**
 * GameLogic represents a 4x4 array of ints
 * it has all the logic for tictactoe
 */

public class GameLogic {
    private int[][] board;
    private int currentPlayer;

    /**
     * @return the currentPlayers int
     */
    
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Creates a new 4x4 board
     * Sets the current player to 1
     * initializes an empty board
     */

    public GameLogic(){
        board = new int[4][4];
        currentPlayer = 1;
        initialize();
    }

    /**
     * Creates a 4x4 board with all 0's
     */

    private void initialize(){
        for(int row = 0; row < 4; row++){
            for(int col = 0; col < 4; col++){
                board[row][col] = 0;
            }
        }
    }

    /**
     * Checks if a spot on the board is empty and places a piece accordingly
     * @param row the row coordinate
     * @param col the column coordinate
     * @return true if the piece was placed, false if the piece cannot be placed
     */

    public boolean playerTurn(int row, int col){
        if(board[row][col] == 0){
            board[row][col] = currentPlayer;
            return true;
        }

        return false;
    }

    /**
     * Switches the current player
     */

    public void switchPlayer(){
        if(currentPlayer == 1){
            currentPlayer = 2;
        }
        else{
            currentPlayer = 1;
        }
    }

    /**
     * Resets the board
     * Sets the player to 1
     */

    public void newGame(){
        initialize();
        currentPlayer = 1;
    }

    /**
     * Checks if 4 ints are equal to the currentPlayer
     * @return true if all ints are equals to currentPlayer
     */

    private boolean notEmpty(int a, int b, int c, int d){
        if((a == currentPlayer) && (a==b) && (a==c) && (a==d)){
            return true;
        }
        
        return false;
    }

    /**
     * Checks each row for a win
     * @return true if any of the rows are all the same int
     */

    private boolean checkRows(){
        for(int row = 0; row < 4; row++){
            if(notEmpty(board[row][0], board[row][1], board[row][2], board[row][3])){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks each column for a win
     * @return true if any of the columns are all the same int
     */

    private boolean checkCols(){
        for(int col = 0; col < 4; col++){
            if(notEmpty(board[0][col], board[1][col], board[2][col], board[3][col])){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks each diagonal for a win
     * @return true if any of the diagonals are all the same int
     */

    private boolean checkDiags(){
        if(notEmpty(board[0][0], board[1][1], board[2][2], board[3][3])){
            return true;
        }
        else if(notEmpty(board[0][3], board[1][2], board[2][1], board[3][0])){
            return true;
        }
        return false;
    }

    /**
     * Checks if the currentPlayer has won
     * @return true if currentPLayer has won
     */

    public boolean checkWinner(){
        return checkRows() || checkCols() || checkDiags();
    }

    /**
     * Checks the board for any empty spaces
     * @return false if it finds a zero in the array
     */

    public boolean isBoardFull(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(board[j][i] == 0){
                    return false;
                }
            }
        }
        return true;
    }
}
