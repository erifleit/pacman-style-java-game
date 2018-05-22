import java.awt.Color;
/* Predator Creature class */
public class Predator extends Creature {
	static final int PREDATOR_SPEED = 3; // constant for predator speed Predator
	static int speedX = 0; // initial speed in the X axis
	static int speedY = -PREDATOR_SPEED; // initial speed in the Y axis
	public static boolean pred = true; // is Predator
	static boolean golden = false; // not Golden
	static int value = 0; // value (points) of Predator (zero)
	
	/* 
	 * Constructor 
	 */
	public Predator(){
		super();
	}
	/*
	 * Constructs a Predator
	 * @param px position X
	 * @param py position y
	 */
	public Predator(int px, int py){
		super(speedX, speedY, px, py, value, pred ,golden, Color.BLACK, Color.WHITE);
	}	
}