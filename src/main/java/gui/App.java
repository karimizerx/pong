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
		var playerA = new Racket(root, KeyCode.CONTROL, KeyCode.ALT, 10, 100, true);
		var playerB = new Racket(root, KeyCode.UP, KeyCode.DOWN, 10, 100, false);
		var court = new Court(root, playerA, playerB, 1000, 600);
		var gameView = new GameView(court, root, 1.0);
		gameScene.setOnKeyPressed(ev -> {
			playerA.on_key_pressed(ev.getCode());
			playerB.on_key_pressed(ev.getCode());
		});
		gameScene.setOnKeyReleased(ev -> {
			playerA.on_key_released(ev.getCode());
			playerB.on_key_released(ev.getCode());
		});
		gameScene.widthProperty().addListener((obs, oldVal, newVal) -> {
			court.setWidth((double)newVal - gameView.getXMargin() * 2);
		});
		gameScene.heightProperty().addListener((obs, oldVal, newVal) -> {
			court.setHeight((double)newVal);
		});
		primaryStage.setScene(gameScene);
		primaryStage.show();
		gameView.animate();
	}
}
