package flappyBird;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class BirdImage {
	
	private BufferedImage img;
	private static int bird_width = 32;
	private static int bird_height = 28;
	public static int x = (GamePanel.WIDTH/2) - bird_width/2;
	public static int y = GamePanel.HEIGHT/2;
	private static int speed = 2;
	private int acceleration = 1;
	
	public BirdImage() {
		LoadImage();
	}

	private void LoadImage() {
		try {
			img = ImageIO.read(new File("C:/Users/Konrad/Desktop/java/FlappyBird/Images/Bird.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void drawBird(Graphics g) {
		g.drawImage(img, x, y, null);		
	}
	
	public void birdMovement() {
		if(y>=0 && y<=GamePanel.HEIGHT) {
			speed += acceleration;
			y += speed;
		} else {
			boolean option = GamePanel.popUpMessage();
			if(option == true) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				reset();
			} else {
				JFrame frame = MainBird.getWindow();
				frame.dispose();
				MainBird.timer.stop();
			}	
		} 
	}

	public static void reset() {
		speed = 2;
		y = GamePanel.HEIGHT/2;
		GamePanel.GameOver = true;
		GamePanel.score = 0;
		Sound.startGameMusic();
	}
	
	public void goUpwards() {
		speed = -14;
	}
	
	public static Rectangle getBirdRect() {
		Rectangle birdRect = new Rectangle(x, y, bird_width, bird_height);
		return birdRect;
	}	
}
