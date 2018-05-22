import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;
/* Class Creature implements the interface MoveableShape creates the Creature objects */
public class Creature implements MoveableShape{
	Random r = new Random(); //new Random
	int speedX, speedY, posX, posY, value; // variables that hold the value for speed on the x and y axis, position on x and y axis, and the value(score) of a creature
	Color color, eyeColor; // variable for color of creature's body, and eyes
	Ellipse2D.Double circle, eye1, eye2; // circle objects for body and eyes
	final int DIAM= 30; // constant for Diameter 
	final int EYE_DIAM = 8; // constant for eye diameter
	boolean pred, live, golden; // boolean that identifies wheather the creature is predator, golden, and/or alive
	int[] posEye1 = {0, 0};//coordinates for eye1
	int[] posEye2 = {0,0};//coordinates for eye2
	/*standard constructor*/
	public Creature(){}
	/*
	 * Creature Class constructor
	 * @param speedx value of speed in the x axis 
	 * @param speedy value of speed in the y axis
	 * @param posx value of x coordinate
	 * @param posy value of y coordinate
	 * @param valu Value (points) of creature
	 * @param preda if true, creature is predator
	 * @param gold if true, creature is Golden
	 * @param colour Color of body
	 * @param colour2 Color of eyes
	 */
	public Creature(int speedx, int speedy, int posx, int posy, int valu, boolean preda , boolean gold,Color colour, Color colour2){
		speedX = speedx;
		speedY = speedy;
		posX = posx;
		posY = posy;
		color = colour;
		eyeColor = colour2;
		pred = preda;
		circle = new Ellipse2D.Double(posX, posY, DIAM, DIAM);
		live = true;
		golden = gold;
		value = valu;
		setDirEyes();
		eye1 = new Ellipse2D.Double(posEye1[0], posEye1[1], EYE_DIAM, EYE_DIAM );
		eye2 = new Ellipse2D.Double(posEye2[0], posEye2[1], EYE_DIAM, EYE_DIAM );
	}
	/*
	 * Draws the shapes of the creatures body
	 * @param g draws the shapes
	 */
	public void draw(Graphics2D g){
		g.setColor(color);
		g.fill(circle);
		g.setColor(eyeColor);
		g.fill(eye1);
		g.fill(eye2);
	}
	/*
	 * Sets the direction the eyes should be facing (according to they're speed direction) 
	 */
	public void setDirEyes(){
		if(speedX>0){
			posEye1[0] = posX +18;
			posEye1[1] = posY +5;
			posEye2[0] = posX +18;
			posEye2[1] = posY +17;
		}
		else if(speedX<0){
			posEye1[0] = posX +4;
			posEye1[1] = posY +5;
			posEye2[0] = posX +4;
			posEye2[1] = posY +17;
		}
		else if(speedY > 0 ){
			posEye1[0] = posX +4;
			posEye1[1] = posY +17;
			posEye2[0] = posX +18;
			posEye2[1] = posY +17;
		}
		else if(speedY < 0){
			posEye1[0] = posX +4;
			posEye1[1] = posY +5;
			posEye2[0] = posX+18;
			posEye2[1] = posY +5;
		}
	}
	/*
	 * Moves the shapes by reassigning it to a shape that contains the position of the previous shape PLUS the speed
	 */
	public void move(){
		setDirEyes();
		posX += speedX;
		posY += speedY;
		circle = new Ellipse2D.Double(posX, posY, DIAM, DIAM);	
		eye1 = new Ellipse2D.Double(posEye1[0], posEye1[1], EYE_DIAM, EYE_DIAM);
		eye2 = new Ellipse2D.Double(posEye2[0], posEye2[1], EYE_DIAM, EYE_DIAM);
	}
	/*
	 * Checks if two creatures have collided
	 * @return true if they are too close
	 */
	public boolean collide(MoveableShape other) {
		return (getDisCreature(this, (Creature) other) <= DIAM);
	}
	/*
	 * Calculates the distance between two creatures
	 * @param first first creature
	 * @param second second creature
	 * @return int distance between the two creature objects
	 */
	public int getDisCreature(Creature first, Creature second){
		int disx = creatureCenterX(first) - creatureCenterX(second);
		int disy = creatureCenterY(first) - creatureCenterY(second);
		return (int) Math.sqrt((disx*disx)+(disy*disy));
	}
	/*
	 * Calculates the x coordinate of the creatures circle
	 * @param aa creature of which the center is obtained
	 * @return int x coordinate of circle's center
	 */
	public int creatureCenterX(Creature aa){
		return (int)aa.circle.getCenterX();
	}
	/*
	 * Calculates the y coordinate of the creatures circle
	 * @param aa creature of which the center is obtained
	 * @return int y coordinate of circle's center
	 */
	public int creatureCenterY(Creature aa){
		return (int)aa.circle.getCenterY();
	}
	/*
	 * Calculates the distance in the x axis between the creature and a point
	 * @param c the creature
	 * @param b the point
	 * @return the distance between creature and point b 
	 */
	public int getDistanceX(Creature c, int b){
		return Math.abs((creatureCenterX(c) - b)) ;
	}
	/* Calculates the distance in the y axis between the creature and a point
	 * @param c the creature
	 * @param b the point
	 * @return the distance between creature and point b 
	 */
	public int getDistanceY(Creature c, int b){
		return Math.abs((creatureCenterY(c) - b)) ;
	}
	/*
	 * Calculates the distance from closest limit of the frame on the X axis
	 * @param creature
	 * @return the distance between the closest limit of the frame and the creature
	 */
	public int getDisFrameX(Creature creat){
		int creatCenter = creatureCenterX(creat);
		int frameA = 0;
		int frameB = PacManFrame.FRAME_WIDTH-10;
		int distanceA = creatCenter - frameA;
		int distanceB = frameB - creatCenter;
		if (distanceA < distanceB) return distanceA;
		else return distanceB;
	}
	/*
	 * Calculates the distance from closest limit of the frame on the Y axis
	 * @param creature
	 * @return the distance between the closest limit of the frame and the creature
	 */
	public int getDisFrameY(Creature creat){
		int creatCenter = creatureCenterY(creat);
		int frameA = 0;
		int frameB = PacManFrame.FRAME_HEIGHT-35;
		int distanceA = creatCenter - frameA;
		int distanceB = frameB - creatCenter;
		if (distanceA < distanceB) return distanceA;
		else return distanceB;
	}
	/* Finds which side of the frame the creature is closest too on the X axis
	 * @return int 1 if the closest one is the right one
	 * @return int 2 if the closest one is the left one
	 */
	public int closestEdgeX(){
		if(getDistanceX(this, 0) < getDistanceX(this, PacManFrame.FRAME_WIDTH)){
			return 1;
		}
		else{
			return 0;
		}
	}
	/* Finds which side of the frame the creature is closest too on the Y axis
	 * @return int 1 if the closest one is the top one
	 * @return int 2 if the closest one is the bottom one
	 */
	public int closestEdgeY(){
		if(getDistanceY(this, 0) < getDistanceY(this, PacManFrame.FRAME_HEIGHT)){
			return 1;
		}
		else{
			return 0;
		}
	}
	/*
	 * Calculates the diameter of creatures body
	 * @return diameter of circle
	 */
	public int getDiam(){
		return DIAM;
	}
	/* Calculates the speed on X axis
	 * @returns speedX the speed on X axis
	 */
	public int getSpeedX(){
		return speedX;
	}
	/* Calculates the speed on Y axis
	 * @returns speedY the speed on Y axis
	 */
	public int getSpeedY(){
		return speedY;
	}
	/*
	 * Reverses the direction by reversing the sign of all speeds
	 */
	public void reverse(){
		speedX = -speedX;
		speedY = -speedY;
	}
	/*
	 * Will turn 50% of the time to a side chosen randomly
	 */
	public void turnRandom(){
		if(!this.pred){
			boolean boo = r.nextBoolean();
			if(boo){
				if(speedX != 0){
					boolean bool = r.nextBoolean();
					if(bool){
						speedY = speedX;
						speedX = 0;
					}
					else{
						speedY = -speedX;
						speedX = 0;
					}
				}
				else if(speedY != 0){
					boolean bool = r.nextBoolean();
					if(bool){
						speedX = speedY;
						speedY = 0;
					}
					else{
						speedX = -speedY;
						speedY = 0;
					}
				}
			}
			else{}
		}
		else {}
	}
	/*
	 * Indentifies if the creature is a predator
	 * @return boolean pred if true the creature is a predator
	 */
	public boolean isPred(){
		return this.pred;
	}
	/*
	 * Turns to the left, checks for all cases
	 */
	public void turnLeft(){
		if(speedX > 0){
			speedY = -speedX;
			speedX = 0;
		}
		else if (speedX < 0){
			speedY = -speedX;
			speedX = 0;
		}
		else if (speedY > 0){
			speedX = speedY;
			speedY = 0;
		}
		else {
			speedX = speedY;
			speedY = 0;
		}
	}
	/*
	 * Turns to the right, checks for all cases
	 */
	public void turnRight(){
		if(speedX > 0){
			speedY = +speedX;
			speedX = 0;
		}
		else if (speedX < 0){
			speedY = +speedX;
			speedX = 0;
		}
		else if (speedY > 0){
			speedX = -speedY;
			speedY = 0;
		}
		else {
			speedX = -speedY;
			speedY = 0;
		}
	}
	/*
	 * Checks if a creature is alive
	 * @return live if true the creature is alive
	 */
	public boolean isAlive(){
		return live;
	}
	/*
	 * Kills creature, making it into a circle of zero size with no eyes at position 0,0
	 * live boolean becomes false
	 */
	public void kills(){
		live = false;
		circle = eye1 = eye2 = new Ellipse2D.Double(0,0,0,0);
		
	}
	/*
	 * Turns upwards by setting the speed Y to -PREDATOR_SPEED
	 */
	public void kTurnUp(){
		speedY = -Predator.PREDATOR_SPEED;
		speedX = 0;
	}
	/*
	 * Turns downwards by setting the speed Y to PREDATOR_SPEED
	 */
	public void kTurnDown(){
		speedY = Predator.PREDATOR_SPEED;
		speedX = 0;
	}
	/*
	 * Turns left by setting the speed X to -PREDATOR_SPEED
	 */
	public void kTurnLeft(){
		speedX = -Predator.PREDATOR_SPEED;
		speedY = 0;
	}
	/*
	 * Turns right by setting the speed X to -REDATOR_SPEED
	 */
	public void kTurnRight(){
		speedX = Predator.PREDATOR_SPEED;
		speedY = 0;
	}
	/*
	 * Checks if the creature is a golden one
	 * @return golden if true, creature is golden
	 */
	public boolean isGolden(){
		return golden;
	}
	/*
	 * If the creature is golden, it teleports to a random point in the frame
	 */
	public void teleport(){
		if(golden){
			posX = r.nextInt(PacManFrame.FRAME_WIDTH-20)+1;
			posY = r.nextInt(PacManFrame.FRAME_HEIGHT-40)+1;
			circle = new Ellipse2D.Double(posX, posY, DIAM, DIAM);}
		else{}
	}
	/*
	 * Returns the value of the creature in points
	 * @return value a number of points
	 */
	public int getValue(){
		return value;
	}
}