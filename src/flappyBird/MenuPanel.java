package flappyBird;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage img;
	public static boolean startingPoint = false;
	
	public MenuPanel() {
		LoadImage();
		this.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				startingPoint = true;
			}
		});
	}

	private void LoadImage() {
		try {
			img = ImageIO.read(new File("C:/Users/Konrad/Desktop/java/FlappyBird/Images/menuPanel.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 0, 0, null);
	}
}
