package gamemodes;

import javafx.scene.input.KeyCode;
import librairies.OpenSimplexNoise;
import java.util.Random;
import java.lang.Math;

public class Vent implements Gamemode {
	Random random = new Random();
	double x=random.nextInt(100000000);
	double x1=random.nextInt(100000000);
	double x2=random.nextInt(100000000);
	int time=0;
	int aleatoireTemps1=1000;
	int aleatoireTemps2=2000;

	public void reset() {
		time=0;
			
	}

	public void on_key_pressed(KeyCode key) {}
	public void on_key_released(KeyCode key) {}
	public void on_ball_touched_racket(model.Court court, boolean left){}
	public void on_ball_left_terrain(model.Court court, boolean left){}
	public int generer(int min, int max){
 		Random random = new Random();
 		int nb;
 		nb = min+random.nextInt(max-min);
 		return nb;
	}

	public void update(model.Court court, double dt) {
		if(time>aleatoireTemps1){
		OpenSimplexNoise noise = new OpenSimplexNoise();		 					
				double value = noise.eval(x,x1,x2);	
				System.out.println(value);
				x+=0.003;
				x1+=0.003;
				x2+=0.003;
				court.get_ball().set_x((court.get_ball().get_middle_x())+value*8	);
				if(time>=aleatoireTemps2){time=0;aleatoireTemps1=generer(800,1200);aleatoireTemps2=generer(1800,2400);}
		}
		time+=1;
	}

	public void render(gui.GameView view, model.Court court) {

	}
}
