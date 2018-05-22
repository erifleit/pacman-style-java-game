import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.Timer;
import javax.swing.JPanel;
/* Panel class where most of the game is created*/
public class PacManPanel extends JPanel{
	/*declaring variables*/
	static final int INTERVAL = 10;//interval for the timer
	static final int NUMBER_OF_ENEMIES = 7; // total number of enemies predator will eat
	static public ArrayList<Creature> creatures;//Array List of creatures
	static int count=0; //counter
	static int count2;  //another counter
	static final int SAFE_DISTANCE = 40; //safe distance
	static String sCount = ""; //String that holds the time count value
	static int score = 10000; //variable that contains the score value
	static double decreaser; //determines by how much the score decreases 
	static String scored="0"; //String holds the score value
	static int f1 = 0; //variable for time in seconds
	static int f2 = 0; //variable for time in decimals
	static int countDead=0; //counts dead creatures
	static Scanner scan = new Scanner(System.in); //Scanner
	static boolean play = false; // boolean that defines whether the game will run
	//instructions
	static String instructions1 = "Use arrow keys OR letters WASD to control the direction of the predator (BLACK)";
	static String instructions2 = "Red ones move fast, Green ones are slow, Gold one teleports if you're not fast enough";
	static String instructions3 = "Press SPACEBAR to BEGIN and PAUSE";
	
	/* Constructor of PacManPanel 
	 * Most of the game operations are made in this method
	 */ 
	public PacManPanel(){
		creatures = new ArrayList<Creature>(); // initialized
		Random r = new Random(); //creates new random
		setFocusable(true); //allows arrow keys to be used
		
		//creates seven creatues and a predator
		for (int c = 0; c < 3 ; c++){
			creatures.add(new FastPrey(r.nextInt(PacManFrame.FRAME_WIDTH-20)+1, r.nextInt(PacManFrame.FRAME_HEIGHT-40)+1));
			creatures.add(new SlowPrey(r.nextInt(PacManFrame.FRAME_WIDTH-20)+1, r.nextInt(PacManFrame.FRAME_HEIGHT-40)+1));
		}
		creatures.add(new GoldenPrey(r.nextInt(PacManFrame.FRAME_WIDTH-20)+1, r.nextInt(PacManFrame.FRAME_HEIGHT-40)+1));
		creatures.add(new Predator((PacManFrame.FRAME_WIDTH-20)/2, (PacManFrame.FRAME_HEIGHT-40)/2));
		
		/*
		 * creates Actionlistener that extends KeyAdapter for controlling the predator by using the arrow keys OR WASD
		 */
		class ActListener extends KeyAdapter{
			/*Class method*/
			public void keyPressed(KeyEvent event) {
				int keycode = event.getKeyCode(); // holds the value of the key pressed
				for(Creature cre : creatures){ // loops through all creatures
					if (cre.isPred()){ //if the creature is predator
						
						//turn upwards if Arrow key UP or W is pressed
						if (keycode == KeyEvent.VK_UP || keycode == KeyEvent.VK_W) {
					    	cre.kTurnUp();
					    }
						
						//turn downwards if Arrow key DOWN or S is pressed
						else if (keycode == KeyEvent.VK_DOWN || keycode == KeyEvent.VK_S) {
					    	cre.kTurnDown();
					    }
						
						//turn left if Arrow key LEFT or A is pressed
						else if (keycode ==KeyEvent.VK_LEFT || keycode == KeyEvent.VK_A) {
					    	cre.kTurnLeft();
					    }
						
						//turn right if Arrow key RIGHT or D is pressed
						else if (keycode == KeyEvent.VK_RIGHT || keycode == KeyEvent.VK_D) {
					    	cre.kTurnRight();
					    }
						
						else if(keycode == KeyEvent.VK_SPACE){play = !play;}
						
						else{}
					}
					else{}
				}
			}
		}	
		/* inner class implements ActionListener so the following operations happen every INTERVAL*/
		class MyListener implements ActionListener {
			//class method
			public void actionPerformed(ActionEvent event){
				if (play && countDead <= creatures.size()){
					count++; // increments count
					f2++; // increments the second/100 place counter
					if(f2 > 99){ //if second/100 reach 100, starts again from zero
						f2=0;
					}
					if(count % 100 == 0){ //if count is a multiple of a hundred, then the seconds variable increments
						f1++;
					}
					/*
					 * NOTE: the reason I chose to use two ints to hold the time is the fact that at random occasions, using doubles would still
					 * print several decimals places regardless of what formatting code I used trying to make it print only 2 decimal places.
					 * Therefore, using two ints was much more easy and efficient at this task.
					 */
					sCount = "TIME: "+f1+"."+f2; //formats sCount to hold the time
					
					decreaser = score*0.001; //sets value for decreaser
					score = (int)(score - decreaser); //score decreases accordingly 
					scored = "SCORE: "+score; //String scored holds the score value
				}
				//counting the number of dead creatures and checking the score			
				if(countDead == NUMBER_OF_ENEMIES|| score <= 0){// if game is over
					for(Creature c : creatures){
							c.kills(); // if all enemies are dead, kills the predator as well, since game is over
					}
				}
				//else the game is still running
				else{
					for(Creature creat1 : creatures){	//loop through all creatures
						Random rand = new Random(); // creates new random number
						//if creature is alive, move
						if (creat1.isAlive() && play){
							creat1.move();
						}
						
						//if creature is close to frame on left and is moving left, reverse direction
						if(creat1.getDisFrameX(creat1)<=creat1.getDiam() && creat1.closestEdgeX()==1 && creat1.getSpeedX()<0){
							creat1.reverse();
						}
						//if creature is close to frame on right and is moving right, reverse direction
						else if(creat1.getDisFrameX(creat1)<=creat1.getDiam() && creat1.closestEdgeX()==0 && creat1.getSpeedX()>0){
							creat1.reverse();
						}
						//if creature is close to frame on top and is moving up, reverse direction
						else if(creat1.getDisFrameY(creat1)<=creat1.getDiam() && creat1.closestEdgeY()==1 && creat1.getSpeedY()<0){
							creat1.reverse();
						}
						//if creature is close to frame on bottom and is moving down, reverse direction
						else if(creat1.getDisFrameY(creat1)<=creat1.getDiam() && creat1.closestEdgeY()==0 && creat1.getSpeedY()>0){
							creat1.reverse();
						}
						//else do nothing
						else {}
						//loop through all creatures again
						for (Creature creat2 : creatures){
							//if second creature is predator and distance to first creature is smaller than safe distance
							if (creat2.isPred() && creat1.getDisCreature(creat1, creat2)<SAFE_DISTANCE){
								//start incrementing counter2
								count2++;
								//if counter2 is greater than 150
								if(count2 > 150){
									//if first creature is Golden
									if(creat1.isGolden()){
										//Creature is afraid and will teleport
										creat1.teleport();
										//resets count2
										count2 = 0;
									}
								}
							}
							//else do nothing
							else{}
						}
						//if two randoms are the same
						if(rand.nextInt(10)==rand.nextInt(10)){
							//creature will turn to a random direction at a random time	
							creat1.turnRandom();
						}
						//loop through all creatures again
						for (Creature creat2 : creatures){
							//if creature 1 and 2 are different, and they collide
							if (creat1 != creat2 && creat1.collide(creat2)){
								//if neither is Predator
								if(!creat1.isPred() && !creat2.isPred()){
									//reverse both
									creat1.reverse();
									creat2.reverse();
								}
								//if one of them is predator
								else if (creat1.isPred()){
									//increments score
									score+=creat2.getValue();
									//kills the other creature
									creat2.kills();
									//increments the counter of deaths
									countDead++;
								}
							}
							//else do nothing
							else{}
						}
					}
					//don't forget to repaint
					repaint();	
				}
			}
		}
		//action listeners
		this.addKeyListener(new ActListener());
		ActionListener actListener = new MyListener();
		Timer t = new Timer(INTERVAL, actListener);
		t.start();
	}
	/*
	 * Paints all creatures
	 * @Graphics g to paint the creatures and text in the frame
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawString(sCount, PacManFrame.FRAME_WIDTH/2-30, 50);
		g2.drawString(scored, PacManFrame.FRAME_WIDTH/2-30, 80);
		for(Creature creature : creatures){
			creature.draw(g2);
		}
		if(!play){
			g2.setColor(Color.BLACK);
			g2.drawString(instructions1, 10, PacManFrame.FRAME_HEIGHT-100);
			g2.drawString(instructions2,10, PacManFrame.FRAME_HEIGHT-80);
			g2.drawString(instructions3,10, PacManFrame.FRAME_HEIGHT-150);
		}
	}
}