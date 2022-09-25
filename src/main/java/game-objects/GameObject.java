package game_objects;

public interface GameObject {
	public double get_left();
	public double get_right();
	public double get_up();
	public double get_down();

	default public boolean collides(GameObject o) {
		return   get_up()   < o.get_down()
		    && o.get_up()   <   get_down()
		    &&   get_left() < o.get_right()
		    && o.get_left() <   get_right();
	}
}
