package model;

import javafx.scene.image.Image;

public abstract class Ressources {
	public static Image get_image(String file) {
		Image img = new Image("file:ressources/custom/" + file + ".png");
		if (!img.isError()) {
			return img;
		} else {
			return new Image("file:ressources/default/" + file + ".png");
		}
	}
}
