package gamemodes;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import model.Court;
import gui.GameView;
import game_objects.GameObject;

import java.util.Random;
import java.util.LinkedList;

public class Bonus_Malus extends GameObject implements Gamemode {
	private Circle circle;
	private boolean est_apparu;
	private LinkedList<gamemodes.Gamemode> list;
	private boolean [] active_gamemodes_id;
	private int decompte;

	public Bonus_Malus(Pane root, LinkedList<gamemodes.Gamemode> gamemode_list) {
		super(0,0,7,7);
		list = gamemode_list;
		active_gamemodes_id = new boolean[list.size()];
		circle = new Circle();
		root.getChildren().add(circle);
		reset();
		decompte = 200;
	}


	public void reset() {
		super.set_x(1);
		super.set_y(1);
		est_apparu = false;
		decompte = 2000;
	        for(int i = 0;i<active_gamemodes_id.length;i++){
		    active_gamemodes_id[i]=false;
		}
		super.set_width(0);
	}

	public void on_key_pressed(KeyCode key) {}
	public void on_key_released(KeyCode key) {}
	public void on_ball_touched_racket(model.Court court, boolean left) {}
	public void on_ball_left_terrain(model.Court court, boolean left) {}

	private static int un_ou_moins_un() {
		Random r = new Random();
		int n = r.nextInt(2);
		if (n==0) { return -1; }
		return 1;
	}

	public void add_gamemode() {
		Random r = new Random();
		if (list.size() == 0) { return; }
		int n = r.nextInt(list.size());
		System.out.println(n);
		active_gamemodes_id[n] = !active_gamemodes_id[n];
	}

	public void update(model.Court c, double deltaT) {
		if (decompte>=0) { decompte--; }

		for(int i=0;i<active_gamemodes_id.length;i++){
		    if(active_gamemodes_id[i]){
			list.get(i).update(c, deltaT);
		    }
		}

		//if(active_gamemodes.size()==list.size()){ return; }

		Random r = new Random();

		if (!est_apparu && decompte==0) {
			est_apparu = true;
			super.set_width(7);
			super.set_vel((50 + (r.nextInt(30)*un_ou_moins_un()))*un_ou_moins_un(),-50 + r.nextInt(30));
			super.set_x(c.get_width() / 2);
			super.set_y(0);
		}
		if(!est_apparu){ return; }

		super.change_x(super.get_dx() * deltaT);
		super.change_y(super.get_dy() * deltaT);

		if (super.get_middle_y() < 0) {
			super.set_y(-super.get_middle_y());
			super.set_vel(super.get_dx(),Math.abs(super.get_dy()));
		} else if (super.get_middle_y() > c.get_height()) {
			super.set_y(c.get_height() - (super.get_middle_y() - c.get_height()));
			super.set_vel(super.get_dx(),-Math.abs(super.get_dy()));
		}
		if(super.get_middle_x()<0){
			super.set_x(-super.get_middle_x());
			super.set_vel(Math.abs(super.get_dx()),super.get_dy());
		}
		else if(super.get_middle_x() > c.get_width()){
			super.set_x(c.get_width() - (super.get_middle_x() - c.get_width()));
			super.set_vel(-Math.abs(super.get_dx()),super.get_dy());
		}
		if (super.collides(c.get_player_a(),deltaT) || super.collides(c.get_player_b(),0)) {
			add_gamemode();
			decompte = 200+r.nextInt(50);
			super.set_width(0);
			est_apparu = false;
			
		}
	}

	public void render(gui.GameView view, model.Court court) {
		circle.setRadius(super.get_width());
		circle.setFill(Color.BLACK);
		circle.setCenterX(super.get_middle_x() * view.get_scale());
		circle.setCenterY(super.get_middle_y() * view.get_scale());
	}
}
