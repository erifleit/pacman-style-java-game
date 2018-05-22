import javax.swing.JFrame;
import java.awt.Color;

public class PacManViewer {
    public static void main(String[] args)
    {
	JFrame frame = new PacManFrame(); // Constructs the frame
	frame.setTitle("PacMan");	//sets Title
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exits when click close
	frame.setVisible(true) ;	//sets visibility to true
	frame.setResizable(false); //sets resizability to true
	frame.setBackground(Color.BLACK); // sets background color to black
    }
}
