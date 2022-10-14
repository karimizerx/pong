package gamemodes;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

import javafx.scene.input.KeyCode;
import game_objects.Racket;
import game_objects.Ball;
import model.Court;

public class Ia implements Gamemode{
    private double difficulty;
	private boolean gauche;
	public Court court1;
	public Ia(double diff, boolean g){
		difficulty=diff;
		gauche =g;
	}



	public void on_key_pressed(KeyCode key){}

	public void on_key_released(KeyCode key){}

    public void update(model.Court court, double deltaT) {
		if(gauche){
		if(difficulty ==1){
			if(court.getBall().getY()>court.getPlayerA().get_up())court.getPlayerA().setY(court.getPlayerA().get_up()+0.20);
			else court.getPlayerA().setY(court.getPlayerA().get_up()-0.20);
		}
		if(difficulty==2){
			if(court.getBall().getY()>court.getPlayerA().get_up())court.getPlayerA().setY(court.getPlayerA().get_up()+0.5);
			else court.getPlayerA().setY(court.getPlayerA().get_up()-0.5);
		}
		if(difficulty==3){
			if(court.getBall().getY()>court.getPlayerA().get_up())court.getPlayerA().setY(court.getPlayerA().get_up()+2.50);
			else court.getPlayerA().setY(court.getPlayerA().get_up()-2.5);;
		}
		if(difficulty==4){
		    court.getPlayerA().setY(court.getBall().getY()-	court.getPlayerA().get_height()/2);
		}
		}
		else{
		if(difficulty ==1){
			if(court.getBall().getY()>court.getPlayerB().get_up())court.getPlayerB().setY(court.getPlayerB().get_up()+0.20);
			else court.getPlayerB().setY(court.getPlayerB().get_up()-0.20);
		}
		if(difficulty==2){
			if(court.getBall().getY()>court.getPlayerB().get_up())court.getPlayerB().setY(court.getPlayerB().get_up()+0.5);
			else court.getPlayerB().setY(court.getPlayerB().get_up()-0.5);
		}
		if(difficulty==3){
			if(court.getBall().getY()>court.getPlayerB().get_up())court.getPlayerB().setY(court.getPlayerB().get_up()+2.50);
			else court.getPlayerB().setY(court.getPlayerB().get_up()-2.5);;
		}
		if(difficulty==4){
		    court.getPlayerB().setY(court.getBall().getY()-	court.getPlayerB().get_height()/2);
		}
		}
		this.court1=court;
}


	public void reset() {
		if(this.court1!=null){
			court1.getPlayerA().setY( (this.court1.getHeight() - court1.getPlayerA().get_height()) / 2);
			//this.court1.reSetRacketSpeed();
		}
	}



	public void render(gui.GameView view, Court court) {}
}
		
		/*court.getPlayerA().getRect() .setHeight(court.getPlayerA().get_height() * view.getScale());
		court.getPlayerA().getRect().setWidth(court.getPlayerA().get_left()  * view.getScale());

		if (is_left_racket) {
			court.getPlayerA().get_left() = -court.getPlayerA().get_width();
			court.getPlayerA().getRect().setX((court.getPlayerA().get_left()  + view.getXMargin()) * view.getScale());
		} else {
			x = court.getWidth();
			rectangle.setX((court.getPlayerA().get_left()  + view.getXMargin()) * view.getScale());
		}
		rectangle.setY(court.getPlayerA().get_up()  * view.getScale());
	}
}
*/