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
	public void update(Court court, double dt) {
		x += dx * dt;
		y += dy * dt;
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
	/* Math for the collisions:
	 * u = up, d = down, l = left, r = right, x = dx, y = dy
	 * collision when: l1 + x1t < r2 + x2t ∧ l2 + x2t < r1 + x1t
	 *               ∧ u1 + y1t < d2 + y2t ∧ u2 + y2t < d1 + y1t
	 *             <=> l1 - r2 < (x2 - x1)t < r1 - l2
	 *               ∧ u1 - d2 < (y2 - y1)t < d1 - u2
	 *                 on nomme nos variables pour plus de clarté
	 *             <=> a < bt < c ∧ d < et < f
	 *
	 */
	public final boolean collides(GameObject o, double dt) {
		final double epsilon = 0.0000001;
		final double a = get_left() - o.get_right();
		final double b = o.get_dx() - get_dx();
		final double c = get_right() - o.get_left();
		final double d = get_up() - o.get_down();
		final double e = o.get_dy() - get_dy();
		final double f = get_down() - o.get_up();
		double min_t;
		double max_t;
		if (b < -epsilon) {
			if (a / b < c / b) {
				return false;
			}
			min_t = c / b;
			max_t = a / b;
		} else if (epsilon < b) {
			if (a / b > c / b) {
				return false;
			}
			min_t = a / b;
			max_t = c / b;
		} else { // b is around about 0.
			if (c < a || 0 < a || c < 0) {
				return false;
			}
			min_t = -100000;
			max_t =  100000;
		}
		if (e < -epsilon) {
			if (d / e < f / e) {
				return false;
			}
			min_t = Math.max(min_t, f / e);
			max_t = Math.min(max_t, d / e);
		} else if (epsilon < e) {
			if (d / e > f / e) {
				return false;
			}
			min_t = Math.max(min_t, d / e);
			max_t = Math.min(max_t, f / e);
		} else { // e is around about 0.
			if (f < d || 0 < d || f < 0) {
				return false;
			}
		}
		if (max_t < 0 || min_t > dt) {
			return false;
		}
		final double t = Math.max(0, min_t); // Unused for now, will be used later to calculate the collision point.
		return true;
	}
}
