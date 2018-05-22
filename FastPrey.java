import java.awt.Color;
/* This Class constructs the FastPrey Creature it extends the creature class*/
public class FastPrey extends Creature{
	
	static int speedX=4; //x axis initial speed
	static int speedY=0; //y axis initial speed
	static boolean pred = false; // not a predator
	static boolean golden = false; // not golden
	static int value = 20000; //value is 20000 points
	/* 
	 * Constructor 
	 */
	public FastPrey(){
		super();
	}
	/*
	 * Constructor of Fast Prey
	 * @param px position X
	 * @param py position y
	 */
	public FastPrey(int pX, int pY){
		super(speedX, speedY, pX, pY, value, pred, golden, Color.RED, Color.WHITE);
	}
}