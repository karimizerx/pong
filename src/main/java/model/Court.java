package model;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

import game_objects.Racket;
import gui.GameView;

public class Court {
    // instance parameters
    private final Racket playerA, playerB;
    private final double width, height; // m
    private final double racketSpeed = 300.0; // m/s
    private final double ballRadius = 10.0; // m
    // instance state
    private double ballX, ballY; // m
    private double ballSpeedX, ballSpeedY; // m

    private Circle ball;

		private Scoreboard scoreboard;

    public Court(Pane root, Racket playerA, Racket playerB, double width, double height) {
        this.playerA = playerA;
        this.playerB = playerB;
        this.width = width;
        this.height = height;
        reset();

        ball = new Circle();
    		this.scoreboard = new Scoreboard(2);
        root.getChildren().addAll(ball, scoreboard.getText());
		}

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getRacketSpeed() {
        return racketSpeed;
    }

    public double getBallX() {
        return ballX;
    }

    public double getBallY() {
        return ballY;
    }

    public void update(double deltaT) {
        playerA.update(this, deltaT);
        playerB.update(this, deltaT);
        if (updateBall(deltaT)) reset();
    }

    public void render(GameView view) {
        ball.setRadius(getBallRadius());
        ball.setFill(Color.BLACK);
        ball.setCenterX(ballX * view.getScale() + view.getXMargin());
        ball.setCenterY(ballY * view.getScale());

        playerA.render(view, this);
        playerB.render(view, this);
    }


    /**
     * @return true if a player lost
     */
    private boolean updateBall(double deltaT) {
        // first, compute possible next position if nothing stands in the way
        double nextBallX = ballX + deltaT * ballSpeedX;
        double nextBallY = ballY + deltaT * ballSpeedY;
        // next, see if the ball would meet some obstacle
        if (nextBallY < 0 || nextBallY > height) {
            ballSpeedY = -ballSpeedY;
            nextBallY = ballY + deltaT * ballSpeedY;
        }
        if ((nextBallX < 0 && playerA.collides(nextBallY)) || (nextBallX > width && playerB.collides(nextBallY))) {
            ballSpeedX = -ballSpeedX;
            nextBallX = ballX + deltaT * ballSpeedX;
        } else if (nextBallX < 0) {
          scoreboard.addPoint(1);  
					return true;
        } else if (nextBallX > width) {
          scoreboard.addPoint(0);  
					return true;
        }
        ballX = nextBallX;
        ballY = nextBallY;
        return false;
    }

    public double getBallRadius() {
        return ballRadius;
    }

    void reset() {
        this.playerA.reset(this);
        this.playerB.reset(this);
        this.ballSpeedX = 200.0;
        this.ballSpeedY = 200.0;
        this.ballX = width / 2;
        this.ballY = height / 2;
    }
}
