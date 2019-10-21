package miniTenis;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Ball {
	private static final int DIAMETER = 20;
	
	int x = 0;
	int y = 0;
	int xa = 1;
	int ya = 1;
	private MiniTenis game;

	public Ball(MiniTenis game) {
		this.game = game;
	}

	void move() {
		if (x + xa < 0)
			xa = 1;
		else if (x + xa > game.getWidth() - DIAMETER)
			xa = -1;
		else if (y + ya < 0)
			ya = 1;
		else if (y + ya > game.getHeight() - DIAMETER)
			{
				game.gameOver();
			}
		else if (collision()){
			Sound.BALL.play();
			ya = -game.speed;
			y = game.racquet.getTopY() - DIAMETER;
			game.speed++;
		}
		x = x + xa;
		y = y + ya;
	}

	private boolean collision() {
		return game.racquet.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
		//------------------------
		Toolkit t = Toolkit.getDefaultToolkit ();
        Image imagen = t.getImage ("ball.png");
        g.drawImage(imagen, x, y, game);
        
		//g.fillOval(x, y, DIAMETER, DIAMETER);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}