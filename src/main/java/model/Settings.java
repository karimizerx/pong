package model;

import java.util.LinkedList;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

import gamemodes.*;

public class Settings {
	public KeyCode left_up;
	public KeyCode left_down;
	public KeyCode right_up;
	public KeyCode right_down;
	public KeyCode stop;
	public KeyCode turbo;
	public KeyCode pauseKey;
	public Color forground_color;
	public Color background_color;
	public LinkedList<Gamemode> gamemodes;
	public LinkedList<Gamemode> bm_gamemodes;
	public LinkedList<Gamemode> possible_gamemodes;
	public LinkedList<Gamemode> possible_bm_gamemodes;
	public gamemodes.Bonus_Malus gamemode_bonus_malus;

	public Settings(Pane root) {
		left_up = KeyCode.A;
		left_down = KeyCode.Q;
		right_up = KeyCode.UP;
		right_down = KeyCode.DOWN;
		stop = KeyCode.ESCAPE;
		turbo = KeyCode.SPACE;
		pauseKey = KeyCode.P;

		forground_color = Color.BLACK;
		background_color = Color.WHITE;

		bm_gamemodes = new java.util.LinkedList<gamemodes.Gamemode>();

		possible_bm_gamemodes = new LinkedList<Gamemode>();
		possible_bm_gamemodes.add(new gamemodes.RacketLength(2));
		possible_bm_gamemodes.add(new gamemodes.RackWTF());
		possible_bm_gamemodes.add(new gamemodes.AccelRacket(1));
		possible_bm_gamemodes.add(new gamemodes.InversionToucheGauche());
		possible_bm_gamemodes.add(new gamemodes.InversionToucheDroite());

		possible_gamemodes = new LinkedList<Gamemode>();
		possible_gamemodes.add(new gamemodes.Scoreboard_versus(root));
		possible_gamemodes.add(new gamemodes.Infini(root));
		possible_gamemodes.add(new gamemodes.RacketLength(2));
		possible_gamemodes.add(new gamemodes.RackWTF());
		possible_gamemodes.add(new gamemodes.Acceleration());
		possible_gamemodes.add(new gamemodes.Ia(3, true));
		possible_gamemodes.add(new gamemodes.Ia(3, false));
		gamemode_bonus_malus = new gamemodes.Bonus_Malus(root, bm_gamemodes);
		possible_gamemodes.add(gamemode_bonus_malus);
		possible_gamemodes.add(new gamemodes.Vent(root));
		possible_gamemodes.add(new gamemodes.CasseBrique(root));

		gamemodes = new LinkedList<>();
		for (Gamemode g : possible_gamemodes) {
			g.no_render();
		}
		for (Gamemode g : possible_bm_gamemodes) {
			g.no_render();
		}
	}

	public void reset_bonus_malus() {
		bm_gamemodes = new LinkedList<Gamemode>();
	}
}
