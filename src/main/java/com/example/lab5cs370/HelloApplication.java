package com.example.lab5cs370;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class HelloApplication extends Application {
    ArrayList<String> cards = new ArrayList<>();
    GridPane grid = new GridPane();
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
        grid.setHgap(5);
        grid.setVgap(5);
        showCards();
        Button shuffleButton = new Button("Shuffle");
        shuffleButton.setOnAction(event -> {
            Collections.shuffle(cards);
            showCards();
        });
        VBox root = new VBox(10);
        root.getChildren().addAll(grid, shuffleButton);
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("52 Card Shuffle");
        stage.setScene(scene);
        stage.show();
    }
    void showCards() {
        grid.getChildren().clear();
        for (int i = 0; i < cards.size(); i++) {
            String fileName = "/cards/" + cards.get(i);
            InputStream stream =
                    getClass().getResourceAsStream(fileName);
            if (stream == null) {
                System.out.println("Missing image: " + fileName);
                continue;
            }
            Image image = new Image(stream);
            ImageView card = new ImageView(image);
            card.setFitWidth(60);
            card.setPreserveRatio(true);
            int column = i % 13;
            int row = i / 13;
            grid.add(card, column, row);
        }
    }
    public static void main(String[] args) {
        launch();
    }
}