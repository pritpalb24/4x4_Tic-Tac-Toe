package cmpt213.asn4.tictactoe.ui;

import cmpt213.asn4.tictactoe.game.GameLogic;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/**
 * BoardUI handles all the elements of the ui
 * Creates an empty board
 * Places pieces
 */
public class BoardUI extends Application {
    private GameLogic gameLogic = new GameLogic();
    private GridPane gridPane = new GridPane();
    private Label resultLabel = new Label();
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the game and creates the main stage
     */

    @Override
    public void start(Stage primaryStage) {
        runGame();

        Scene scene = new Scene(gridPane, 440, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Creates a new game button
     * Places it on the UI
     */

    private void newGameButton(){
        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(event -> handleNewGame());
        GridPane.setMargin(newGameButton, new Insets(10));
        gridPane.add(newGameButton, 0, 4, 4, 1);
    }

    /**
     * Starts a new game
     * Replaces the result label with an empty string
     * runs the game
     */

    private void handleNewGame() {
        gameLogic.newGame();
        resultLabel.setText(" ");
        runGame();
    }

    /**
     * Creates a 4x4 grid of black squares
     * Takes mouse input for player clicks
     */

    private void runGame(){
        ImageView[][] imageViews = new ImageView[4][4];

        Insets margin = new Insets(5);

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                Image image = new Image("file:src/images/blackSquare.png");
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);

                final int finalI = i;
                final int finalJ = j;

                imageView.setOnMouseClicked(event -> handleMove(finalI, finalJ, imageView));

                gridPane.add(imageView, j, i);
                GridPane.setMargin(imageView, margin);

                imageViews[i][j] = imageView;
            }
        }

        gridPane.add(resultLabel, 0, 5, 4, 1);

        newGameButton();
    }

    /**
     * Handles the player clicks and checks for winner or draw
     * Replaces the result label with the correct string
     * @param i coordinate point of the click
     * @param j coordinate point of the click
     * @param imageView image that will be replaced
     */

    private void handleMove(int i, int j, ImageView imageView) {
        if(gameLogic.checkWinner() || gameLogic.isBoardFull()){
            return;
        }
        
        if(gameLogic.playerTurn(i, j)){
            updateUI(i,j,imageView);
        }
        else{ //if player clicks a occupied spot, switch back to the player
            gameLogic.switchPlayer();
        }

        if(gameLogic.checkWinner()){
            if(gameLogic.getCurrentPlayer() == 1){
                resultLabel.setText("Red Player Wins!");
            }
            else{
                resultLabel.setText("Blue Player Wins!");
            }
        }
        else if(gameLogic.isBoardFull()){
            resultLabel.setText("It's a draw!");
        }
        else{
            gameLogic.switchPlayer();
        }
    }

    /**
     * Updates the coordinate with the correct image
     */

    private void updateUI(int i, int j, ImageView imageView){
        if(gameLogic.getCurrentPlayer() == 1){
            imageView.setImage(new Image("file:src/images/red.png"));
        }
        else{
            imageView.setImage(new Image("file:src/images/blue.png"));
        }
    }
}
    