package flappyBird;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public static boolean GameOver = false;
	public static int score = 0;
	
	public static final int WIDTH = 480;
	public static final int HEIGHT = 640;
	
	private int xCoordinate = 0;
	private BufferedImage img;
	BirdImage bi = new BirdImage();
	WallImage wi = new WallImage(GamePanel.WIDTH);
	WallImage wi2 = new WallImage(GamePanel.WIDTH+(GamePanel.WIDTH/2));
	WallImage wi3 = new WallImage(2*GamePanel.WIDTH);
	
	public GamePanel() {
		LoadImage();
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				bi.goUpwards();
			}
		});
	}

	private void LoadImage() {
		try {
			img = ImageIO.read(new File("C:/Users/Konrad/Desktop/java/FlappyBird/Images/gamePanel.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, xCoordinate, 0, null);
		g.drawImage(img, xCoordinate+1920, 0, null);
		bi.drawBird(g);
		wi.drawWall(g);
		wi2.drawWall(g);
		wi3.drawWall(g);
		g.setFont(new Font("Tahoma", Font.BOLD, 32));
		g.drawString("Score: " + score, WIDTH/2, 80);
	}
	
	public void Move() {
		bi.birdMovement();
		wi.wallMovement();
		wi2.wallMovement();
		wi3.wallMovement();
		if(GameOver == true) {
			wi.X = GamePanel.WIDTH;
			wi2.X = GamePanel.WIDTH+(GamePanel.WIDTH/2);
			wi3.X = 2*GamePanel.WIDTH;
			GameOver = false;
		}
		
		xCoordinate += WallImage.speed;
		if(xCoordinate == -1920) {
			xCoordinate = 0;
		}
		
		if(wi.X == BirdImage.x-2 || wi2.X == BirdImage.x-2 || wi3.X == BirdImage.x-2) { //-2
			score++;
		}
	}
	
	public static boolean popUpMessage() {
		Sound.stopGameMusic();
		int result = JOptionPane.showConfirmDialog(null, "Game Over, your score is " + score + "\nDo yout want to restart the game?", "Game Over", JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION) {
			return true;
		} else {
			return false;
		}
	}
}
