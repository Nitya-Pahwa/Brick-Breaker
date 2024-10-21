package gameplay;


 import Util.GameUtility;
 import java.awt.Color;
 import java.awt.Font;
 import java.awt.Graphics;
 import java.awt.Graphics2D;
 import java.awt.Rectangle;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 import java.awt.event.KeyEvent;
 import java.awt.event.KeyListener;
 import javax.swing.JPanel;
 import javax.swing.Timer;

import MapGenerator.MapGenerator;


interface Play{
	public void paint(Graphics g);
	public void actionPerformed(ActionEvent e);
	public void keyTyped(KeyEvent e);
	public void keyReleased(KeyEvent e);
	public void keyPressed(KeyEvent e);
	public void moveRight();
	public void moveLeft();	
}
 public class Gameplay extends JPanel implements KeyListener, ActionListener, Play{
     public boolean play = false;
     public int score = 0;
     public int totalBricks = 21;
     public Timer timer;
     public int delay = 8;
     public int playerX = 310;
     public int ballposX = 120;
     public int ballposY = 350;
     public int ballXdir = -1;
     public int ballYdir = -2;
     GameUtility gu=new GameUtility();

     public MapGenerator map;

     public Gameplay() {
         map = new MapGenerator(3, 7);
         addKeyListener(this);
         setFocusable(true);
         setFocusTraversalKeysEnabled(false);
         timer = new Timer(delay, this);
         timer.start();
     }

     public void paint(Graphics g) {
    	 
         gu.paintGame(this, g, map, score, totalBricks, playerX, ballposX, ballposY, play);
          
     }

     @Override
     public void actionPerformed(ActionEvent e) {
         timer.start();

         if (play) {
             if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                 ballYdir = -ballYdir;
             }

             gu.handleBrickCollision(map, this);

             ballposX += ballXdir;
             ballposY += ballYdir;

             if (ballposX < 0) {
                 ballXdir = -ballXdir;
             }

             if (ballposY < 0) {
                 ballYdir = -ballYdir;
             }

             if (ballposX > 670) {
                 ballXdir = -ballXdir;
             }
         }
         repaint();
     }

     @Override
     public void keyTyped(KeyEvent e) {}

     @Override
     public void keyReleased(KeyEvent e) {}

     @Override
     public void keyPressed(KeyEvent e) {
         if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
             if (playerX >= 600) {
                 playerX = 600;
             } else {
                 moveRight();
             }
         }

         if (e.getKeyCode() == KeyEvent.VK_LEFT) {
             if (playerX < 10) {
                 playerX = 10;
             } else {
                 moveLeft();
             }
         }

         if (e.getKeyCode() == KeyEvent.VK_ENTER) {
             if (!play) {
                 play = true;
                 ballposX = 120;
                 ballposY = 350;
                 ballXdir = -1;
                 ballYdir = -2;
                 playerX = 310;
                 score = 0;
                 totalBricks = 21;
                 map = new MapGenerator(3, 7);

                 repaint();
             }
         }
     }

     public void moveRight() {
         play = true;
         playerX += 20;
     }

     public void moveLeft() {
         play = true;
         playerX -= 20;
     }
 }
