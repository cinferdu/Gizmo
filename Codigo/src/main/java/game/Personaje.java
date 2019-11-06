package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import entornoGrafico.PanelJuego;
import entornoGrafico.VentanaJuego;

public class Personaje {
	//private String image;
	private String name;
	@SuppressWarnings("unused")
	private VentanaJuego game;
	private int x;
	private int y;
	private static final int WITH = 50;
	private static final int HEIGHT = 50;
	PanelJuego observer;
	//private Map<String,AudioClip> sounds;
	
	public Personaje(String name, VentanaJuego game) {
		this.name=name;
		this.game = game; //TODO para que se le envia la ventana de juego ?
	}
	
	public String getName() {
		return this.name;
	}
	
	public void move(int x, int y,PanelJuego observer) {
		this.x = x;
		this.y = y;
		this.observer = observer;
	}
	
	
	public void paint(Graphics g) {
		//------------------------
		Toolkit t = Toolkit.getDefaultToolkit ();
		Image imagen = t.getImage ("Personajes/"+this.getName()+"-body.png");
        g.drawImage(imagen,x,y-12,30,40,this.observer);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, WITH, HEIGHT);
	}
	
}
