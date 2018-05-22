import java.awt.Color;

/* Slow Prey Constructor */
public class SlowPrey extends Creature{
	static int xSpeed = 1; // constant for predator speed Predator
	static int ySpeed = 0; // initial speed in the X axis
	static boolean pred = false;// not a predator
	static boolean golden = false; // is Golden
	static int value = 5000; //value is 5000 points
	
	/* Constructor */
	public SlowPrey(){
		super();
	}
	/*
	 * Constructs a Slow Prey
	 * @param px position X
	 * @param py position y
	 */
	public SlowPrey(int pX, int pY){
		super(xSpeed, ySpeed, pX, pY, value, pred , golden,Color.GREEN, Color.BLACK);
	}
}