package miniTenis;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class Racquet {
	private static final int Y = 300;
	private static final int WITH = 35;
	private static final int HEIGHT = 50;
	int x = 0;
	int xa = 0;
	private MiniTenis game;

	public Racquet(MiniTenis game) {
		this.game = game;
	}

	public void move() {
		if (x + xa > 0 && x + xa < game.getWidth() - WITH)
			x = x + xa;
	}

	public void paint(Graphics2D g) {
		Toolkit t = Toolkit.getDefaultToolkit ();
        Image imagen = t.getImage ("tubo.png");
        g.drawImage(imagen,x,Y, game);
	}

	public void keyReleased(KeyEvent e) {
		xa = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xa = -game.speed;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = game.speed;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, Y, WITH, HEIGHT);
	}

	public int getTopY() {
		return Y - HEIGHT-50;
	}
}
