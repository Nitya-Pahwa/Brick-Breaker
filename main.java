package Main;
import gameplay.Gameplay;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import gameplay.Gameplay;

public class main {
	public static void main(String[] arr) {
		
		JFrame obj = new JFrame();
		Gameplay gamePlay = new Gameplay();

		obj.setBounds(10, 10, 705, 630);
		obj.setTitle("BrickBreaker");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gamePlay);
		
		 playBackgroundMusic("src/sounds/background.wav");
	}




public static void playBackgroundMusic(String filePath) {
    try {
        File musicPath = new File(filePath);
        if (musicPath.exists()) {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.loop(Clip.LOOP_CONTINUOUSLY);  // Play the sound in a loop
            clip.start();  // Start the clip
        } else {
            System.out.println("Can't find the file");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
