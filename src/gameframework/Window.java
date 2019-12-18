package gameframework;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Creates frame and set its properties.
 * 
 * @author www.gametutorial.net
 */

public class Window extends JFrame{
        
    /**
	 * 
	 */
	private static final long serialVersionUID = -7171289084733442914L;

	public boolean fullscreen;
	private Window()
    {
        // Sets the title for this frame.
        this.setTitle("Game title");
        
        // Sets size of the frame.
        if(fullscreen) // Full screen mode
        {
            // Disables decorations for this frame.
            this.setUndecorated(true);
            // Puts the frame to full screen.
            this.setExtendedState(Window.MAXIMIZED_BOTH);
        }
        else // Window mode
        {
            // Size of the frame.
        	 this.setUndecorated(true);
        	Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize ();
            this.setSize(screensize.width,screensize.height);
            // Puts frame to center of the screen.
            this.setLocationRelativeTo(null);
            // So that frame cannot be resizable by the user.
            this.setResizable(false);
        }
        
        // Exit the application when user close frame.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Creates the instance of the Framework.java that extends the Canvas.java and puts it on the frame.
        this.setContentPane(new Framework());
        
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        // Use the event dispatch thread to build the UI for thread-safety.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });
    }
}
