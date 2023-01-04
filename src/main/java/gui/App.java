package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;

import game_objects.Racket;
import gamemodes.Gamemode;
import model.Court;
import model.Ressources;
import model.Settings;

public class App extends Application {
	@Override
	public void start(Stage primaryStage) {
		var gameRoot = new Pane();
		startMenu(primaryStage, gameRoot, new Settings(gameRoot));
	}

	public void startMenu(Stage primaryStage, Pane gameRoot, Settings settings) {
		var root = new BorderPane();
		var box = new VBox();

		box.setSpacing(20);

		var retour = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				startMenu(primaryStage, gameRoot, settings);
			}
		};

		// PLAY BUTTON
		var play_button = new Button();
		play_button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				startGame(primaryStage, gameRoot, settings);
			}
		});
		play_button.setPrefSize(180, 40);
		Image play_image = Ressources.get_image("play");
		ImageView play_view = new ImageView(play_image);
		play_button.setGraphic(play_view);

		// SETTINGS BUTTON
		var settings_button = new Button("Settings");
		settings_button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				new SettingsView(primaryStage, settings, retour);
			}
		});


		box.getChildren().addAll(play_button, settings_button);
		
		root.setCenter(box);
		box.setAlignment(Pos.CENTER);

		var menuScene = new Scene(root, 1000, 600);
		primaryStage.setScene(menuScene);
		primaryStage.show();
		
	}

	public void startGame(Stage primaryStage, Pane root, Settings settings) {
		var gameScene = new Scene(root);
		var player_a = new Racket(root, settings.left_up,  settings.left_down,   105, 200, 10, 100, Ressources.get_image("racket_l"));
		var player_b = new Racket(root, settings.right_up, settings.right_down, -105, 200, 10, 100, Ressources.get_image("racket_r"));

		for (Gamemode g : settings.gamemodes) {
			g.render();
		}
		var court = new Court(root, player_a, player_b, 1000, 600, settings.gamemodes, settings.pauseKey);
		var gameView = new GameView(court, root, 1.0);
		gameScene.setOnKeyPressed(ev -> {
			court.on_key_pressed(ev.getCode());
		});
		gameScene.setOnKeyReleased(ev -> {
			court.on_key_released(ev.getCode());
		});
		gameScene.widthProperty().addListener((obs, oldVal, newVal) -> {
			court.set_width((double) newVal);
		});
		gameScene.heightProperty().addListener((obs, oldVal, newVal) -> {
			court.set_height((double) newVal);
		});
		primaryStage.setScene(gameScene);
		primaryStage.show();
		gameView.animate();
	}
}
