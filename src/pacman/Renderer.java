package pacman;

import java.io.IOException;
import java.util.LinkedList;

import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.ansi.UnixTerminal;

public class Renderer {
    
    private Terminal terminal;
    
    public Renderer() throws IOException{
        this.terminal = new UnixTerminal();
    }
    
    void clearScreen() throws IOException {
        terminal.clearScreen();
        terminal.setCursorVisible(false);
    }


    public void drawPacman(Pacman pacman) throws IOException {
        terminal.setCursorPosition(pacman.getX(), pacman.getY());
        terminal.putCharacter('C');
    }


    public void clearPacman(Pacman pacman) throws IOException {
        terminal.setCursorPosition(pacman.getX(), pacman.getY());
        terminal.putCharacter(' ');
    }


    public void drawMap(GameMap map) throws IOException {
        for (int y=0; y<map.getHeight(); y++){
            for (int x= 0; x<map.getWidth(); x++){
                if (map.isWall(x, y))
                {
                    terminal.setCursorPosition(x, y);
                    terminal.putCharacter('#');
                }
            }
        }
    }

    public void drawGhosts(LinkedList<Ghost> ghosts) throws IOException {
        for (Ghost ghost: ghosts){
            drawGhost(ghost);
        }
    }

    private void drawGhost(Ghost ghost) throws IOException {
        terminal.setCursorPosition(ghost.getX(), ghost.getY());
        terminal.putCharacter('G');
    }

    public void clearGhosts(LinkedList<Ghost> ghosts) throws IOException {
        for (Ghost ghost: ghosts){
            clearGhost(ghost);
        }
    }

    private void clearGhost(Ghost ghost) throws IOException {
        terminal.setCursorPosition(ghost.getX(), ghost.getY());
        terminal.putCharacter(' ');
    }
}
