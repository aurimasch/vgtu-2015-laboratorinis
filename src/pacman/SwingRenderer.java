package pacman;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * responsible for correct rendering of game objects (map, pacman, ghosts, ...) 
 *
 */
public class SwingRenderer {

    private GameMap map;
    private Pacman pacman;
    
    private Image wallImage;
    private Image pacmanImage;
    private List<Ghost> ghosts;
    private Image ghostImage;

    public SwingRenderer(GameMap map, Pacman pacman, List<Ghost> ghosts) throws IOException{
        this.map = map;
        this.pacman = pacman;
        this.ghosts = ghosts;
        
        loadImageResources();
    }
    
    public void render(Graphics g){
        drawMap(g);
        drawPacman(g);
        drawGhosts(g);
    }
    
    public void drawMap(Graphics g){
        for (int y=0; y<map.getHeight(); y++){
            for (int x= 0; x<map.getWidth(); x++){
                drawMapElementAt(g, x, y);
            }
        }
    }

    private void drawMapElementAt(Graphics g, int x, int y) {
        if (map.isWall(x, y))
            drawWall(g, y, x);
        else
        if (map.isCookie(x, y))
            drawCookie(g, y, x);
    }

    private void drawCookie(Graphics g, int y, int x) {
        g.drawOval(x*32+16,  y*32+25+16, 4, 4);
    }

    private void drawWall(Graphics g, int y, int x) {
        g.drawImage(wallImage, x*32, y*32+25, null);
    }
    
    public void drawPacman(Graphics g){
        g.drawImage(pacmanImage, pacman.getX()*32, pacman.getY()*32+25, null);
    }
    
    public void drawGhosts(Graphics g){
        for (Ghost ghost: ghosts)
            drawGhost(g, ghost);
    }
    
    public void drawGhost(Graphics g, Ghost ghost){
        g.drawImage(ghostImage, ghost.getX()*32, ghost.getY()*32+25, null);
    }
    
    private void loadImageResources() throws IOException {
        this.wallImage = ImageIO.read(new File("wall.png"));
        this.pacmanImage = ImageIO.read(new File("pacman-open.png"));
        this.ghostImage = ImageIO.read(new File("ghost.png"));
    }

}
