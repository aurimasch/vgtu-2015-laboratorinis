package pacman;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class SwingMain extends JFrame   // in order to have a Swing application window 
                       implements KeyListener, // in order to receive keyboard events
                                  ActionListener{  // in order to receive Swing Timer events

    private GameMap map ;
    private Pacman pacman ;
    private List<Ghost> ghosts = new LinkedList<Ghost>();
    
    private GameRules rules ;
    
    private SwingRenderer renderer ;
    
    public SwingMain() throws IOException{
        initDialogWindow();
        
        map = new GameMap();
        
        pacman = new Pacman(10, 10);
        
        ghosts.add(new Ghost(5, 5));
        ghosts.add(new Ghost(5, 6));
        ghosts.add(new Ghost(5, 7));
        
        rules = new GameRules(map, pacman, ghosts);
        
        renderer = new SwingRenderer(map, pacman, ghosts);
        
        new Timer(300, this).start();
    }

    /***
     * timer event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        rules.moveGhosts();
        
        rules.checkIsPacmanEaten();
        
        if (rules.isGameOver())
        	gameOver();
        
        repaint();
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        
        renderer.render(g);
    }


    @Override
    public void keyReleased(KeyEvent e) {
        rules.processUserInput(e.getKeyChar());
        
        repaint();
        
        rules.checkIsPacmanEaten();
        
        if (rules.isGameOver())
            gameOver();
    }

	private void gameOver() {
		dispose();
		System.exit(0);
	}

    
    private void initDialogWindow() {
        setPreferredSize(new Dimension(500, 600));
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addKeyListener(this);
        
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new SwingMain();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

}
