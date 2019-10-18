package miniTenis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MiniTenis extends JPanel {

	Ball ball = new Ball(this);
	Racquet racquet = new Racquet(this);
	JFrame frame;
	int speed = 1;
	boolean enable;

	private int getScore() {
		return speed - 1;
	}

	public MiniTenis() {
		frame = new JFrame();
		enable = true;
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
			}

			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
			}
		});
		setFocusable(true);
	}

	private void move() {
		ball.move();
		racquet.move();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		ImageIcon background = new ImageIcon(MiniTenis.class.getResource("couds.gif"));
		g.drawImage(background.getImage(), 0, 0,300 , 400, null);
		setOpaque(false);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
		racquet.paint(g2d);
		g2d.setColor(Color.GRAY);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		g2d.drawString(String.valueOf(getScore()), 10, 30);
	}

	public void gameOver() {
		Sound.GAMEOVER.play();
		JOptionPane.showMessageDialog(this.frame, "your score is: " + getScore(),"Game over",JOptionPane.YES_NO_OPTION);
		
		this.frame.dispose();
		this.enable=false;
		
	}

	public void iniciarMiniTenis() throws InterruptedException {
		frame.add(this);
		
		frame.setSize(300, 400);
		frame.setVisible(true);
		
		while (enable) {
			this.move();
			this.repaint();
			Thread.sleep(10);
		}
	}
}