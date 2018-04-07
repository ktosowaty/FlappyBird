package flappyBird;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class WallImage {
	
	private Random rand = new Random();
	public int X;
	public int Y = rand.nextInt(GamePanel.HEIGHT-320)+240; //+300 +200
	private int width_wall = 36;
	private int height = GamePanel.HEIGHT-Y;
	private int gap = 160;
	private BufferedImage img;
	
	public static int speed = -6;
	
	public WallImage(int X) {
		this.X = X;
		LoadImage();
	}

	private void LoadImage() {
		try {
			img = ImageIO.read(new File("C:/Users/Konrad/Desktop/java/FlappyBird/Images/Wall.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void drawWall(Graphics g) {
		g.drawImage(img, X, Y, null); //bottom
		g.drawImage(img, X, (-GamePanel.HEIGHT)+(Y-gap), null); //upper
	}
	
	public void wallMovement() {
		X += speed;
		if(X<=-width_wall) {
			X = GamePanel.WIDTH+(GamePanel.WIDTH/2)-width_wall;
			Y = rand.nextInt(GamePanel.HEIGHT-320)+240;
			height = GamePanel.HEIGHT-Y;
		}
		Rectangle lowerRect = new Rectangle(X, Y, width_wall, height);
		Rectangle upperRect = new Rectangle(X, 0, width_wall, GamePanel.HEIGHT-(height+gap));
		
		if(lowerRect.intersects(BirdImage.getBirdRect()) || upperRect.intersects(BirdImage.getBirdRect())) {
			boolean option = GamePanel.popUpMessage();
			if(option == true) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				BirdImage.reset();
				wallReset();
			} else {
				JFrame frame = MainBird.getWindow();
				frame.dispose();
				MainBird.timer.stop();
			} 	
		}
	}

	private void wallReset() {
		Y = rand.nextInt(GamePanel.HEIGHT-320)+240;
		height = GamePanel.HEIGHT-Y;
		GamePanel.GameOver = true;
	}	
}
