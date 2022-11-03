package game_objects;

import model.Court;

public abstract class GameObject {
	private double x;
	private double y;
	private double dx;
	private double dy;
	private double w;
	private double h;

	public GameObject(double x, double y, double w, double h, double dx, double dy) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.w = w;
		this.h = h;
	}
	public GameObject(double x, double y, double w, double h) {
		this(x, y, w, h, 0, 0);
	}

	public final double get_width() {
		return w;
	}
	public final double get_height() {
		return h;
	}

	public final double get_middle_x() {
		return x;
	}
	public final double get_middle_y() {
		return y;
	}

	public final double get_dx() {
		return dx;
	}
	public final double get_dy() {
		return dy;
	}

	public final double get_left() {
		return x - w / 2;
	}
	public final double get_right() {
		return x + w / 2;
	}
	public final double get_up() {
		return y - h / 2;
	}
	public final double get_down() {
		return y + h / 2;
	}

	public void set_x(double v) {
		x = v;
	}
	public void set_y(double v) {
		y = v;
	}
	public void change_x(double v) {
		x += v;
	}
	public void change_y(double v) {
		y += v;
	}
	public void set_width(double v) {
		w = v;
	}
	public void set_height(double v) {
		h = v;
	}
	public boolean update(Court court, double dt) {
		x += dx * dt;
		y += dy * dt;
		return false;
	}
	public void scale_vel(double x, double y) {
		dx *= x;
		dy *= y;
	}
	public void set_vel(double x, double y) {
		dx = x;
		dy = y;
	}
	public void apply_force(double x, double y) {
		dx += x;
		dy += y;
	}
	public final boolean collides(GameObject o) {
		return   get_up()   < o.get_down()
		    && o.get_up()   <   get_down()
		    &&   get_left() < o.get_right()
		    && o.get_left() <   get_right();
	}
}
