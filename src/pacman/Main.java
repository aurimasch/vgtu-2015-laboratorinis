package pacman;

import java.io.IOException;
import java.util.LinkedList;

/***
 * Dėmesio!
 * 
 * Kaip teisingai sukonfigūruoti Eclipsę kad ši programa veiktų, skaitykite faile "README"
 * 
 */
public class Main {
    
    public static void main(String[] args) throws IOException {

        GameMap map = new GameMap();
        
        Pacman pacman = new Pacman(10, 10);

        LinkedList<Ghost> ghosts = new LinkedList<Ghost>();
        ghosts.add(new Ghost(5, 5));
        ghosts.add(new Ghost(6, 5));
        
        GameRules rules = new GameRules(map, pacman, ghosts);

        Renderer renderer = new Renderer();

        renderer.drawMap(map);
        
        while (!rules.isGameOver()) {
            int n = System.in.read();
            
            
            renderer.clearPacman(pacman);
            
            rules.processUserInput(n);
            
            renderer.drawPacman(pacman);
            
            
            rules.checkIsPacmanEaten();
            
            
            renderer.clearGhosts(ghosts);
            
            rules.moveGhosts();
            
            renderer.drawGhosts(ghosts);
        }
    }


}
