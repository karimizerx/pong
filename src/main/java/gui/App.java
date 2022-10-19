package gui;


import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Court;
import game_objects.Racket;

public class App extends Application {
	@Override
	public void start(Stage primaryStage) {
		var root = new Pane();
		var gameScene = new Scene(root);
		var playerA = new Racket(root, KeyCode.A, KeyCode.Q, 100, 200, 10, 100);
		var playerB = new Racket(root, KeyCode.UP, KeyCode.DOWN, -110, 200, 10, 100);
		var gamemodes = new java.util.LinkedList<gamemodes.Gamemode>();
		// gamemodes.add(new gamemodes.RackWTF());
		gamemodes.add(new gamemodes.RacketLength(2));
		gamemodes.add(new gamemodes.Ia(3,false));
		gamemodes.add(new gamemodes.Ia(4,true));
		gamemodes.add(new gamemodes.Acceleration());
		var court = new Court(root, playerA, playerB, 1000, 600, gamemodes);
		var gameView = new GameView(court, root, 1.0);
		gameScene.setOnKeyPressed(ev -> {
			court.on_key_pressed(ev.getCode());
		});
		gameScene.setOnKeyReleased(ev -> {
			court.on_key_released(ev.getCode());
		});
		gameScene.widthProperty().addListener((obs, oldVal, newVal) -> {
			court.setWidth((double)newVal);
		});
		gameScene.heightProperty().addListener((obs, oldVal, newVal) -> {
			court.setHeight((double)newVal);
		});
		primaryStage.setScene(gameScene);
		primaryStage.show();
		gameView.animate();
	}
}
