import java.awt.*;
import java.awt.event.KeyEvent;

public class Racquet {
   private static final int WIDTH = 60;
   private static final int HEIGHT = 10;
   int x = 0;
   int xa = 0;
   int y;
   private Game game;

   public Racquet(Game game, int y) {
      this.game = game;
      this.y = y;
   }

   public void move() {
      if (x + xa > 0 && x + xa < game.getWidth() - WIDTH) {
         x = x + xa;
      }
   }

   public void paint(Graphics2D g) {
      g.fillRect(x, y, WIDTH, HEIGHT);
   }

   public void keyReleased(KeyEvent e) {
      xa = 0;
   }

   public void keyPressed(KeyEvent e) {
      int keyLeft = y == 10 ? KeyEvent.VK_A : KeyEvent.VK_LEFT;
      int keyRight = y == 10 ? KeyEvent.VK_D : KeyEvent.VK_RIGHT;

      if (e.getKeyCode() == keyLeft) {
         xa = -game.speed;
      } if (e.getKeyCode() == keyRight) {
         xa = game.speed;
      }
   }

   public Rectangle getBounds() {
      return new Rectangle(x, y, WIDTH, HEIGHT);
   }

   public int getTopY(int y) {
      if (y <= 100) {
         return 10 + HEIGHT;
      } else {
         return this.y - HEIGHT;
      }
   }
}