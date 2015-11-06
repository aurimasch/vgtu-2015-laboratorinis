package pacman;

import java.util.List;

/***
 *
 *  Encompasses game rules for movement of ghosts and pacman
 *
 */
public class GameRules {

    private boolean gameOver=false;
    
    private GameMap map;
    private Pacman pacman;
    private List<Ghost> ghosts;

    public GameRules(GameMap map, Pacman pacman, List<Ghost> ghosts){
        this.map = map;
        this.pacman = pacman;
        this.ghosts = ghosts;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }

    public void checkIsPacmanEaten(){
        for (Ghost ghost: ghosts){
            if (isGhostOnPacman(ghost)){
                gameOver = true;
                return;
            }
        }
    }
    
    public void processUserInput(int n) {
        switch (n) {
            case 'q':
                gameOver = true;
                return;
            case 'a': 
                tryMovePackman(-1, 0);
                break;
            case 'd':
                tryMovePackman(1, 0);
                break;
            case 's':
                tryMovePackman(0, 1);
                break;
            case 'w':
                tryMovePackman(0, -1);
                break;
            }
        
        map.eat(pacman.getX(), pacman.getY());
    }

    public void moveGhosts(){
        for (Ghost ghost: ghosts)
            moveGhost(ghost);
    }

    private boolean isGhostOnPacman(Ghost ghost) {
        return ghost.getX() == pacman.getX() && ghost.getY() == pacman.getY();
    }
    
    private void tryMovePackman(int dx, int dy) {
        if (!map.isWall(pacman.getX() + dx, pacman.getY() + dy)){
            pacman.setX(pacman.getX() + dx);
            pacman.setY(pacman.getY() + dy);
        }
    }
    
    private void moveGhost(Ghost ghost) {
        if (!map.isWall(ghost.getNextX(), ghost.getNextY()))
            ghost.move();
        else
            ghost.changeDirection();
    }

}
