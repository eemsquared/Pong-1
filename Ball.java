import java.awt.*;

public class Ball {
   private static final int DIAMETER = 30;

   int x = 0;
   int y = 30;
   int xa = 1;
   int ya = 1;
   private Game game;
   boolean rac1Point;
   boolean rac2Point;

   public Ball(Game game) {
      this.game = game;
   }

   void move() {
      boolean changeDirection = true;

      if (x + xa < 0) { // bounce to right
         xa = game.speed;
      } else if (x + xa > game.getWidth() - DIAMETER) { // bounce to left
         xa = -game.speed;
      } else if (y + ya < 0) {   // player 2 misses
         rac1Point = true;
         game.gameOver();
      } else if (y + ya > game.getHeight() - DIAMETER) {    // player 1 misses
         rac2Point = true;
         game.gameOver();
      } else if (collision()) {
         if (y <= 50) {     // bounce off player 2's racquet
            ya = game.speed;
            y = game.racquet1.getTopY(y);
         } else {    // bounce off player 1's racquet
            ya = -game.speed;
            y = game.racquet1.getTopY(y) - DIAMETER;
         }

         game.speed++;
      } else {
         changeDirection = false;
      }

      if (changeDirection) {
         Sound.BALL.play();   // play "collision" sound effect
      }

      x = x + xa;
      y = y + ya;
   }

   private boolean collision() {
      return game.racquet1.getBounds().intersects(getBounds()) || game.racquet2.getBounds().intersects(getBounds());
   }

   public void paint(Graphics2D g) {
      g.fillOval(x, y, DIAMETER, DIAMETER);
   }

   public Rectangle getBounds() {
      return new Rectangle(x, y, DIAMETER, DIAMETER);
   }
}