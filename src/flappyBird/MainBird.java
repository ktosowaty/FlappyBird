package flappyBird;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

public class MainBird {

	private static JFrame window;
	public static Timer timer;
	private MainBird() {
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(GamePanel.WIDTH, GamePanel.HEIGHT);
		window.setLocationRelativeTo(null);
		window.setTitle("FlappyBird");
		window.setResizable(false);
	}
	
	private void rendering() {
		MenuPanel mp = new MenuPanel();
		GamePanel gp = new GamePanel();
		timer = new Timer(20, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gp.repaint();
				gp.Move();				
			}
		});
		Sound.startMenuMusic();
		window.add(mp);
		window.setVisible(true);		
		
		while(MenuPanel.startingPoint == false) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		window.remove(mp);
		Sound.stopMenuMusic();
		Sound.startGameMusic();
		window.add(gp);
		gp.setVisible(true);
		window.revalidate();
		
		timer.start();
		
	}
	
	public static JFrame getWindow() {
		return window;
	}
	
	public static void main(String[] args) {
		MainBird bird = new MainBird();
		bird.rendering();
		
	}

}
