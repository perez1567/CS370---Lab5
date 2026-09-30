package com.example.lab5cs370;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class game extends Application {
    ArrayList<String> cards = new ArrayList<>();
    ImageView cardView = new ImageView();
    Label scoreLabel = new Label("Score: 0");
    Label wrongLabel = new Label("Wrong: 0");
    Label resultLabel = new Label("Guess the card color");
    int score = 0;
    int wrong = 0;
    Button redButton = new Button("Red");
    Button blackButton = new Button("Black");
    @Override
    public void start(Stage stage) {
        String[] suits = {"hearts", "diamonds", "clubs", "spades"};
        String[] ranks = {
                "ace", "2", "3", "4", "5", "6", "7",
                "8", "9", "10", "jack", "queen", "king"
        };
        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(rank + "_of_" + suit + ".png");
            }
        }
        Collections.shuffle(cards);
        cardView.setFitWidth(140);
        cardView.setPreserveRatio(true);
        redButton.setOnAction(event -> drawCard("red"));
        blackButton.setOnAction(event -> drawCard("black"));
        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(redButton, blackButton);
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: green;");
        root.getChildren().addAll(
                resultLabel,
                cardView,
                buttons,
                scoreLabel,
                wrongLabel
        );
        Scene scene = new Scene(root, 600, 500);
        stage.setTitle("Guess");
        stage.setScene(scene);
        stage.show();
    }
    void drawCard(String guess) {
        if (cards.isEmpty()) {
            resultLabel.setText("No cards left");
            return;
        }
        String cardName = cards.remove(0);
        InputStream stream =
                getClass().getResourceAsStream("/cards/" + cardName);
        if (stream == null) {
            System.out.println("Missing image: " + cardName);
            return;
        }
        Image image = new Image(stream);
        cardView.setImage(image);
        boolean isRed =
                cardName.contains("hearts") ||
                        cardName.contains("diamonds");
        if ((guess.equals("red") && isRed) ||
                (guess.equals("black") && !isRed)) {
            score++;
            resultLabel.setText("Correct!");
        } else {
            wrong++;
            resultLabel.setText("Wrong!");
        }
        scoreLabel.setText("Score: " + score);
        wrongLabel.setText("Wrong: " + wrong);
        if (score == 5) {
            resultLabel.setText("You Win!");
            redButton.setDisable(true);
            blackButton.setDisable(true);
        }
        if (wrong == 3) {
            resultLabel.setText("Game Over!");
            redButton.setDisable(true);
            blackButton.setDisable(true);
        }
    }
    public static void main(String[] args) {
        launch();
    }
}