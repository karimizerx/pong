package gui;

import javafx.scene.text.*;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import model.Court;
import model.Scoreboard;

public class GameView {
	// class parameters
	private final Court court;
	private final Pane game_root; // main node of the game
	private final double scale;

	/**
	 * @param court le "modèle" de cette vue (le terrain de jeu de raquettes et tout
	 *              ce qu'il y a dessus)
	 * @param root  le nœud racine dans la scène JavaFX dans lequel le jeu sera
	 *              affiché
	 * @param scale le facteur d'échelle entre les distances du modèle et le nombre
	 *              de pixels correspondants dans la vue
	 */
	public GameView(Court court, Pane root, double scale) {
		this.court = court;
		this.game_root = root;
		this.scale = scale;

		root.setMinWidth(court.get_width() * scale);
		root.setMinHeight(court.get_height() * scale);
	}

	public double get_scale() {
		return scale;
	}

	public void animate() {
		GameView view = this;
		new AnimationTimer() {
			long last = 0;
			final double dt = 0.01; // update every 0.01s
			double acc = 0.0;

			@Override
			public void handle(long now) {
				if (last == 0) { // ignore the first tick, just compute the first dt
					last = now;
					return;
				}

				acc += (now - last) * 1.0e-9; // convert nanoseconds to seconds
				last = now;

				while(acc >= dt) {
					court.update(dt);
					acc -= dt;
				}

				court.render(view);
			}
		}.start();
	}
}
