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
		var sub_box = new VBox();
		box.setSpacing(10);
		sub_box.setSpacing(5);

		var retour_button = new Button("OK");
		retour_button.setOnAction(retour);
		box.getChildren().add(retour_button);

		// construction de la liste des modes de bonus malus
		for(Gamemode bm_gamemode : settings.possible_bm_gamemodes) {
			var sub_mode_checkbox = new CheckBox(bm_gamemode.getName() + " (Bonus-Malus)");

			sub_mode_checkbox.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					CheckBox sub_selected = (CheckBox) e.getSource();
					if (sub_selected.isSelected()) {
						settings.bm_gamemodes.add(bm_gamemode);
					} else {
						settings.bm_gamemodes.remove(bm_gamemode);
					}
				}
			});

			sub_box.getChildren().add(sub_mode_checkbox);
		}
		sub_box.setAlignment(Pos.CENTER);

		// construction de la liste des modes
		for (Gamemode gamemode : settings.possible_gamemodes) {
			var mode_checkbox = new CheckBox(gamemode.getName());

			mode_checkbox.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					CheckBox selected = (CheckBox) e.getSource();
					if (selected.isSelected()) {
						settings.gamemodes.add(gamemode);

						if (gamemode instanceof Bonus_Malus) {
							box.getChildren().add(sub_box);
						}

					} else {
						settings.gamemodes.remove(gamemode);

						if (gamemode instanceof Bonus_Malus) {
							box.getChildren().remove(sub_box);
							settings.reset_bonus_malus();
						}
					}
				}
			});

			box.getChildren().add(mode_checkbox);
		}


		root.setCenter(box);
		box.setAlignment(Pos.CENTER);

		var scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
