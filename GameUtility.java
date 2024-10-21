package Util;

import gameplay.Gameplay;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import MapGenerator.MapGenerator;

interface Game{
    public  void paintGame(Gameplay game, Graphics g, MapGenerator map, int score, int totalBricks, int playerX, int ballposX, int ballposY, boolean play);
    public  void handleBrickCollision(MapGenerator map, Gameplay game);
}
public class GameUtility implements Game{
	

    private static Image backgroundImage;

    // Static block to load the image when the class is loaded
    static {
        try {
            backgroundImage = ImageIO.read(new File("src/images/Final.jpg"));  // Provide the correct path
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	 

    public void paintGame(Gameplay game, Graphics g, MapGenerator map, int score, int totalBricks, int playerX, int ballposX, int ballposY, boolean play) {
        // background 
    	 if (backgroundImage != null) {
             g.drawImage(backgroundImage, 0, 0, 692, 592, null);  // Adjust size to fit the game panel
         } else {
             // Fallback background color if image is not found
             g.setColor(Color.DARK_GRAY);
             g.fillRect(1, 1, 692, 592);
         }
    
      

        // drawing map
        map.draw((Graphics2D) g);

        // borders
        g.setColor(Color.black);
        g.fillRect(0, 0, 3, 600);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(686, 0, 3, 600);
        g.fillRect(0, 588, 686, 3);


        // scores
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score, 590, 30);

        // paddle
        g.setColor(Color.black);
        g.fillRect(playerX, 550, 100, 8);

        // the ball
        g.setColor(Color.yellow);
        g.fillOval(ballposX, ballposY, 20, 20);

         
        if (!play && totalBricks > 0 && game.ballposY < 570) {
            // Display message to start the game
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press Left/Right Arrow Key to Start Game", 90, 350);
        }
        
        if (totalBricks <= 0) {
            play = false;
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won! ", 260, 300);
            game.timer.stop();
            g.dispose();
        }

        if (ballposY > 570) {
            play = false;
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Scores: " + score, 190, 300);
            g.dispose();
        }

    }

    public void handleBrickCollision(MapGenerator map, Gameplay game) {
        for (int i = 0; i < map.map.length; i++) {
            for (int j = 0; j < map.map[0].length; j++) {
                if (map.map[i][j] > 0) {
                    int brickX = j * map.brickwidth + 80;
                    int brickY = i * map.brickheight + 50;
                    int brickWidth = map.brickwidth;
                    int brickHeight = map.brickheight;

                    Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                    Rectangle ballRect = new Rectangle(game.ballposX, game.ballposY, 20, 20);

                    if (ballRect.intersects(rect)) {
                        map.setBrickValue(0, i, j);
                        game.totalBricks--;
                        game.score += 5;

                        if (game.ballposX + 19 <= rect.x || game.ballposX + 1 >= rect.x + rect.width) {
                            game.ballXdir = -game.ballXdir;
                        } else {
                            game.ballYdir = -game.ballYdir;
                        }
                    }
                }
            }
        }
    }
}
