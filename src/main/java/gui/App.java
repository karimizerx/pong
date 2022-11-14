package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Court;
import model.Settings;

import game_objects.Racket;

public class App extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		var root = new Pane();
		var settings = new Settings(root);

		var play_button = new Button("Jouer");
		play_button.setLayoutX(200);
		play_button.setLayoutY(200);
		play_button.setOnAction(
			new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					startGame(primaryStage, settings);
				}
			}
		);

		root.getChildren().add(play_button);

		var menuScene = new Scene(root);
		primaryStage.setScene(menuScene);
		primaryStage.show();
		
	}

	public void startGame(Stage primaryStage, Settings settings) {
		
		var root = new Pane();
		var gameScene = new Scene(root);
		var player_a = new Racket(root, settings.left_up,  settings.left_down,   105, 200, 10, 100);
		var player_b = new Racket(root, settings.right_up, settings.right_down, -105, 200, 10, 100);
		var court = new Court(root, player_a, player_b, 1000, 600, settings.gamemodes, settings.forground_color, settings.background_color);
		var gameView = new GameView(court, root, 1.0);
		gameScene.setOnKeyPressed(ev -> {
			court.on_key_pressed(ev.getCode());
		});
		gameScene.setOnKeyReleased(ev -> {
			court.on_key_released(ev.getCode());
		});
		gameScene.widthProperty().addListener((obs, oldVal, newVal) -> {
			court.set_width((double)newVal);
		});
		gameScene.heightProperty().addListener((obs, oldVal, newVal) -> {
			court.set_height((double)newVal);
		});
		gameScene.setFill(court.get_secondaire());
		primaryStage.setScene(gameScene);
		primaryStage.show();
		gameView.animate();
	}
}
