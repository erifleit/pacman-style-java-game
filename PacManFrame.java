import javax.swing.JFrame;
import javax.swing.JPanel ;
import java.awt.BorderLayout ;
import java.awt.Color;
/* this Class constructs the frame by extending JFrame */
public class PacManFrame extends JFrame {
	public static final int FRAME_WIDTH  = 600;//Width of the frame
    public static final int FRAME_HEIGHT = 600;//Height of the frame
    
    /*
     * Constructs the frame and creates the panel
     */
    public PacManFrame()
    {
    createPanel();
	setSize(FRAME_WIDTH, FRAME_HEIGHT);
	setBackground(Color.BLACK);
    }

    public void createPanel()
    {
    	JPanel panel = new PacManPanel() ;
    	add(panel, BorderLayout.CENTER) ;
    }
}