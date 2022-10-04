package model;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

import game_objects.Racket;
import game_objects.Ball;
import gui.GameView;

public class Court {
    // instance parameters
    private final Racket playerA, playerB;
    private final double width, height; // m
    private double racketSpeed = 300.0; // m/s
    private final Ball ball;

		private Scoreboard scoreboard;

    public Court(Pane root, Racket playerA, Racket playerB, double width, double height) {
        this.playerA = playerA;
        this.playerB = playerB;
        this.ball = new Ball(root);
        this.width = width;
        this.height = height;
	this.scoreboard = new Scoreboard(root, 2);
        reset();
    }

    public Ball getBall(){return ball;  }
    public Racket getPlayerA() {
        return playerA;
    }
    public Racket getPlayerB() {
        return playerB;
    }

    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }

    public void setRacketSpeed(double s){
	racketSpeed = s;
    }

    public void reSetRacketSpeed(){
	racketSpeed = 300;
    }
    
    public double getRacketSpeed() {
        return racketSpeed;
    
    }	
		public Scoreboard getScoreboard(){
			return scoreboard;
		}

    public void update(double deltaT) {
        playerA.update(this, deltaT);
        playerB.update(this, deltaT);
        if (ball.update(this, deltaT)) {
            reset();
        }
    }

    public void render(GameView view) {
        ball.render(view, this);
        playerA.render(view, this);
        playerB.render(view, this);
		}

    void reset() {
        this.playerA.reset(this);
        this.playerB.reset(this);
        this.ball.reset(this);
    }
}
