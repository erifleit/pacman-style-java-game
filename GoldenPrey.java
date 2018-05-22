import java.awt.Color;

public class GoldenPrey extends Creature{
	
	static int speedX=3; //initial speed on X axis
	static int speedY=0; //initial speed on Y axis
	static boolean pred = false; // not a predator
	static boolean golden = true; // is Golden
	static int value = 50000; // value is 50,000
	
	/*
	 * Constructor
	 */
	public GoldenPrey(){
		super();
	}
	/*
	 * Constructs a GoldenPrey creature
	 * @param pX position of creature on the X axis
	 * @param pY position of creature on the Y axis
	 */
	public GoldenPrey(int pX, int pY){
		super(speedX, speedY, pX, pY, value, pred,golden, Color.ORANGE, Color.BLACK);
	}
}
