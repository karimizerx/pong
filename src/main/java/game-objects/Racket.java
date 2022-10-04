package game_objects;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import model.Court;
import game_objects.Ball;
import gui.GameView;
import game_objects.GameObject;

public class Racket implements GameObject {
	private Pane proot;
	private KeyCode up_key;
	private KeyCode down_key;
	private int direction; // -1 is up, 1 is down, 0 is idle
	private double x;
	private double y;
	private double w;
	private double h;
	private Rectangle rectangle;
	private boolean is_left_racket;
	private double mode_de_jeu;
    private double acceleration = 0;
    /*mode de jeu :
0 := joueur
tier 1-3 :  si la balle est plus haute que la raquette, cette dernière monte, si elle est plus basse, elle descend.
1 := IA tier 1 : la raquette se déplace de 0.2
2 := IA tier 2 : la raquette se déplace de 0.25
3 := IA tier 3 : la raquette se déplace de 25
4 := IA tier 4 : associe la coordonnée en y de la raquette à celle de la balle
    */

	public Racket(Pane root, KeyCode up_key, KeyCode down_key, double w, double h, boolean is_left_racket, double mode_de_jeu) {
		this.up_key = up_key;
		this.down_key = down_key;
		direction = 0;
		y = 0;
		this.w = w;
		this.h = h;
		this.is_left_racket = is_left_racket;
		this.proot=root;
		this.mode_de_jeu=mode_de_jeu;
		rectangle = new Rectangle();
		rectangle.setFill(Color.BLACK);
		root.getChildren().add(rectangle);
	}

	public double get_left() {
		return x;
	}
	public double get_right() {
		return x + w;
	}
	public double get_up() {
		return y;
	}
	public double get_down() {
		return y + h;
	}

	public void on_key_pressed(KeyCode key) {
		if (key == up_key) {
			direction = -1;
		} else if (key == down_key) {
			direction = 1;
		}
	}

	public void on_key_released(KeyCode key) {
		if (key == up_key && direction == -1) {
			direction = 0;
		} else if (key == down_key && direction == 1) {
			direction = 0;
		}
	}

public void update(Court court, double deltaT) {
		if(mode_de_jeu ==1){
			if(court.getBall().getY()>this.y)y+=0.20;
			else y-=0.20;
		}
		if(mode_de_jeu==2){
			if(court.getBall().getY()>this.y)y+=0.5;
			else y-=0.5;
		}
		if(mode_de_jeu==3){
			if(court.getBall().getY()>this.y)y+=2.5;
			else y-=2.5;
		}
		if(mode_de_jeu==4){
		    y=court.getBall().getY()-h/2;
		}
		else{
		if (y < 0) {
			y = 0;
		}
		if (y + h > court.getHeight()) {
			y = court.getHeight() - h;
		}
		
		else{
		    y += direction * deltaT * court.getRacketSpeed() ;
		    court.setRacketSpeed(court.getRacketSpeed() + acceleration);
					if (y < 0) {
			y = 0;
		}
		if (y + h > court.getHeight()) {
			y = court.getHeight() - h;
		}
		}
		}
}

	

	public void reset(Court court) {
		y = (court.getHeight() - h) / 2;
		court.reSetRacketSpeed();
	}

	public void render(GameView view, Court court) {
		rectangle.setHeight(h * view.getScale());
		rectangle.setWidth(w * view.getScale());

		if (is_left_racket) {
			x = -w;
			rectangle.setX((x + view.getXMargin()) * view.getScale());
		} else {
			x = court.getWidth();
			rectangle.setX((x + view.getXMargin()) * view.getScale());
		}
		rectangle.setY(y * view.getScale());
	}
}
