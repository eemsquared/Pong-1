import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Game extends JPanel {

   Ball ball = new Ball(this);
   Racquet racquet1 = new Racquet(this, 340); // ubos
   Racquet racquet2 = new Racquet(this, 10); // taas
   int speed = 1;
   public int rac1Score = 0;
   public int rac2Score = 0;

   public Game() {
      addKeyListener(new KeyListener() {
         @Override
         public void keyTyped(KeyEvent e) {
         }

         @Override
         public void keyReleased(KeyEvent e) {
            racquet1.keyReleased(e);
            racquet2.keyReleased(e);
         }

         @Override
         public void keyPressed(KeyEvent e) {
            racquet1.keyPressed(e);
            racquet2.keyPressed(e);
         }
      });

      setFocusable(true);
      Sound.BACK.loop();
   }

   private void move() {
      ball.move();
      racquet1.move();
      racquet2.move();
   }

   @Override
   public void paint(Graphics g) {
      super.paint(g);   // calls paint method in JPanel

      Graphics2D bal = (Graphics2D) g;
      bal.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      bal.setColor(Color.GRAY);
      ball.paint(bal);

      Graphics2D rac1 = (Graphics2D) g;
      rac1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      rac1.setColor(Color.BLUE);    // colors for distinction
      rac1.setFont(new Font("Verdana", Font.BOLD, 30));     // individual score display
      rac1.drawString(String.valueOf(rac1Score), 10, 330);
      racquet1.paint(rac1);

      Graphics2D rac2 = (Graphics2D) g;
      rac2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      rac2.setColor(Color.RED);     // colors for distinction
      rac2.setFont(new Font("Verdana", Font.BOLD, 30));     // individual score display
      rac2.drawString(String.valueOf(rac2Score), 10, 50);
      racquet2.paint(rac2);
   }

   public void gameOver() {   // Game over + scoring
      if (ball.rac1Point) {   // a bit of time-out for scoring
         rac1Score++;
         JOptionPane.showMessageDialog(null, "Point to Player 1!\nPress ENTER to continue", "", JOptionPane.YES_NO_OPTION);
      } else {
         rac2Score++;
         JOptionPane.showMessageDialog(null, "Point to Player 2!\nPress ENTER to continue", "", JOptionPane.YES_NO_OPTION);
      }

      speed = 1;     // restart everytime naay makapoint???
      ball = new Ball(this);

      if (rac1Score == 3 || rac2Score == 3) {   // mana ang race to 3 pts
         Sound.BACK.stop();
         Sound.GAMEOVER.play();

         String winner;
         if (rac1Score == 3) {
            winner = "Player 1 won the game!\nScore: " + rac1Score;
         } else {
            winner = "Player 2 won the game!\nScore: " + rac2Score;
         }

         JOptionPane.showMessageDialog(this, winner, "Game Over", JOptionPane.YES_NO_OPTION);   // game over, there's a winner
         int replay = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "", JOptionPane.YES_NO_OPTION);    // dialog box to ask if user wants to replay

         if (replay == 1) {   // if no, closes
            System.exit(ABORT);
         } else {    // replay, resets values
            speed = 1;
            rac1Score = 0;
            rac2Score = 0;
            Sound.BACK.loop();
         }
      }
   }

   public static void main(String[] args) throws InterruptedException {
      JFrame frame = new JFrame("Mini Tennis");
      JOptionPane.showMessageDialog(frame, "\bPONG\b\n\nThe mechanics of the game will be similar to an actual ping-pong game.\n" +
          "The player will get points if their opponent misses the ball.\n" +
          "Player 1 will be the blue one at the bottom while Player 2 will the red one at the top.\n" +
          "The game will be a race to whoever gets three points first\n\n" +
          "Controls:\nPlayer 1\nLeft Arrow Key - moves your racquet to the left\nRight Arrow Key - moves your racquet to the right\n" +
          "Player 2\nA - moves your racquet to the left\nD - moves your racquet to the right\n\n" +
          "Ready to play? Press the button below to start.", "Game Instructions", JOptionPane.YES_NO_OPTION);     // para ma-ready sad ang players haha
      Game game = new Game();
      frame.add(game);
      frame.setSize(300, 400);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      while (true) {
         game.move();
         game.repaint();
         Thread.sleep(10);
      }
   }
}