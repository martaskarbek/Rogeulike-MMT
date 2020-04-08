
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Game extends KeyAdapter {
    
    @Override
    public void keyPressed(KeyEvent event) {

        char ch = event.getKeyChar();

        System.out.println((int)ch);
        Player player = new Player();

        switch(ch) {
            case 'w':
                // Hero.moveUp()
                player.move(new Coordinates(-1, 0));
                break;
            case 's':
                // Hero.moveDown()
                player.move(new Coordinates(1, 0));
                break;
            case 'a':
                // Hero.moveLeft()
                player.move(new Coordinates(0, -1));
                break;
            case 'd':
                // Hero.moveRight()
                player.move(new Coordinates(0, 1));
                break;   
        }
    }
}