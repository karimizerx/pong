package gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Court;
import model.Settings;

import game_objects.Racket;
import gamemodes.*;

public class SettingsView {
	private Settings settings;

	public SettingsView(Stage  primaryStage, Settings s, EventHandler<ActionEvent> retour) {
		settings = s;

		var root = new BorderPane();
		var box = new VBox();
		box.setSpacing(10);

		for (Gamemode gamemode : settings.possible_gamemodes) {
			var mode_checkbox = new CheckBox(gamemode.getName());

			mode_checkbox.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					CheckBox selected = (CheckBox) e.getSource();
					if (selected.isSelected()) {
						settings.gamemodes.add(gamemode);
					} else {
						settings.gamemodes.remove(gamemode);
					}
				}
			});

			box.getChildren().add(mode_checkbox);
		}

		var retour_button = new Button("OK");
		retour_button.setOnAction(retour);
		box.getChildren().add(retour_button);

		root.setCenter(box);
		box.setAlignment(Pos.CENTER);

		var scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
