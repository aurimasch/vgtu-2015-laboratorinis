package pacman;

public class Ghost {

    private int x;
    private int y;
    
    private int xVector = 1;
    private int yVector = 0;
    
    public Ghost(int x, int y){
        this.setX(x);
        this.setY(y);
    }
    
    public int getNextX(){
        return this.getX() + xVector;
    }
    
    public int getNextY(){
        return this.getY() + yVector;
    }
    
    public void move(){
        this.setX( getNextX() );
        this.setY( getNextY() );
    }

    public void changeDirection() {
        if (Math.random() * 10 < 5){
            bounceBack();
        }else
        {
            swapXYVectors();
        }
    }

    private void swapXYVectors() {
        int tmp = xVector;
        xVector = yVector;
        yVector = tmp;
    }

    private void bounceBack() {
        this.xVector = -1* xVector;
        this.yVector = -1* yVector;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
